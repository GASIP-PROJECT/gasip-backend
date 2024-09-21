package com.example.gasip.memberBlock.controller;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.memberBlock.dto.MemberBlockRequest;
import com.example.gasip.memberBlock.dto.MemberBlockResponse;
import com.example.gasip.memberBlock.service.MemberBlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "차단 및 신고 기능", description = "차단, 신고와 관련된 API 입니다")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("users/block")
public class MemberBlockController {

    private final MemberBlockService memberBlockService;

    @PostMapping("")
    @Operation(summary = "유저 차단", description = "특정 유저를 차단할 수 있습니다. \n차단된 유저의 게시글은 필터링되어 노출되지 않습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                         content = {@Content(mediaType = "application/json",
                         schema = @Schema(implementation = MemberBlockResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<?> blockMember(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @RequestBody @Valid MemberBlockRequest memberBlockRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiUtils.success(
                                memberBlockService.blockMember(memberBlockRequest, memberDetails)
                        )
                );
    }

}
