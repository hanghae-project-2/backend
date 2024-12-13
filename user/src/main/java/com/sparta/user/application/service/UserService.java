package com.sparta.user.application.service;

import com.sparta.user.common.CustomException;
import com.sparta.user.domain.User;
import com.sparta.user.domain.UserRepository;
import com.sparta.user.domain.UserRole;
import com.sparta.user.infrastructure.security.JwtUtil;
import com.sparta.user.presentation.dto.request.UpdateUserRequest;
import com.sparta.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtBlacklistService jwtBlacklistService;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        User user = findUserById(id);
        return UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return userRepository.findAll(pageable).map(UserResponse::from);
    }

    @Transactional
    public void approveUser(UUID id, UserRole role, String updatedBy) {
        User user = findUserById(id);

        if (user.getIsApproved()) {
            throw new CustomException("사용자가 이미 승인되었습니다.", HttpStatus.BAD_REQUEST);
        }

        user.approve(role, updatedBy);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UUID id, UpdateUserRequest request, String updatedBy) {
        User user = findUserById(id);

        if (request.getCurrentPassword() != null && request.getNewPassword() != null) {
            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                throw new CustomException("현재 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
            }
            user.updatePassword(passwordEncoder.encode(request.getNewPassword()), updatedBy);
        }

        if (request.getSlackId() != null) {
            user.updateSlackId(request.getSlackId(), updatedBy);
        }

        userRepository.save(user);
    }

    @Transactional
    public void updateUserRole(UUID id, UserRole newRole, String updatedBy) {
        User user = findUserById(id);

        if (UserRole.MASTER.equals(user.getRole())) {
            throw new CustomException("MASTER 권한은 수정할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        user.updateRole(newRole, updatedBy);

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UUID id, String deletedBy) {
        User user = findUserById(id);

        if (user.getIsDelete()) {
            throw new CustomException("이미 삭제된 사용자입니다.", HttpStatus.BAD_REQUEST);
        }

        user.delete(deletedBy);
        userRepository.save(user);

        List<String> tokens = jwtUtil.getAllActiveTokens(user.getUsername());
        for (String redisKey : tokens) {
            String token = (String) redisTemplate.opsForValue().get(redisKey);
            if (token != null && jwtUtil.validateToken(token)) {
                String tokenType = jwtUtil.getTokenType(token);
                boolean isAccessToken = "access".equals(tokenType);
                jwtBlacklistService.addToBlacklist(token, jwtUtil.getRemainingExpiration(token), isAccessToken);
            }
        }
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("사용자를 찾을 수 없습니다. ID: " + id, HttpStatus.NOT_FOUND));
    }
}
