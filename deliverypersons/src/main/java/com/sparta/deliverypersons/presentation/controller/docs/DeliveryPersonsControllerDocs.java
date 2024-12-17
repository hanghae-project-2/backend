package com.sparta.deliverypersons.presentation.controller.docs;

import com.sparta.deliverypersons.presentation.dto.request.UpdateDeliveryPersonRequest;
import com.sparta.deliverypersons.presentation.dto.response.CreateDeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.DeleteDeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.DeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.UpdateDeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "DeliveryPersons", description = "배송 담당자 API")
public abstract class DeliveryPersonsControllerDocs {

    @Operation(summary = "배송 담당자 생성", description = "배송 담당자를 생성하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "배송 담당자 생성 성공",
                            content = @Content(schema = @Schema(implementation = Response.class))
                    )
            }
    )
    @PostMapping
    public abstract Response<CreateDeliveryPersonResponse> createDeliveryPerson(
            @RequestParam UUID userId,
            @RequestParam(required = false) UUID hubId,
            @RequestParam String deliveryType
    );

    @Operation(summary = "배송 담당자 수정", description = "배송 담당자를 수정하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "배송 담당자 수정 성공",
                            content = @Content(schema = @Schema(implementation = Response.class))
                    )
            }
    )
    @PatchMapping("/{id}")
    public abstract Response<UpdateDeliveryPersonResponse> updateDeliveryPerson(
            @PathVariable UUID id,
            @RequestBody UpdateDeliveryPersonRequest request,
            @RequestHeader("Authorization") String authorizationHeader
    );

    @Operation(summary = "배송 담당자 조회", description = "특정 배송 담당자를 조회하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "배송 담당자 조회 성공",
                            content = @Content(schema = @Schema(implementation = Response.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "배송 담당자를 찾을 수 없습니다.",
                            content = @Content(schema = @Schema(implementation = Response.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public abstract Response<DeliveryPersonResponse> getDeliveryPerson(@PathVariable UUID id);

    @Operation(summary = "배송 담당자 목록 조회", description = "전체 배송 담당자 목록을 조회하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "배송 담당자 목록 조회 성공",
                            content = @Content(schema = @Schema(implementation = Response.class))
                    )
            }
    )
    @GetMapping
    public abstract Response<Page<DeliveryPersonResponse>> getAllDeliveryPersons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );

    @Operation(summary = "배송 담당자 삭제", description = "배송 담당자를 삭제하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "배송 담당자 삭제 성공",
                            content = @Content(schema = @Schema(implementation = Response.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "배송 담당자를 찾을 수 없습니다.",
                            content = @Content(schema = @Schema(implementation = Response.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public abstract Response<DeleteDeliveryPersonResponse> deleteDeliveryPerson(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorizationHeader
    );
}
