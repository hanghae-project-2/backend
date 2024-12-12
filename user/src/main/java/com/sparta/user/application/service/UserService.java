package com.sparta.user.application.service;

import com.sparta.user.common.CustomException;
import com.sparta.user.domain.User;
import com.sparta.user.domain.UserRepository;
import com.sparta.user.domain.UserRole;
import com.sparta.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("사용자를 찾을 수 없습니다. ID: " + id, HttpStatus.NOT_FOUND));
    }

}
