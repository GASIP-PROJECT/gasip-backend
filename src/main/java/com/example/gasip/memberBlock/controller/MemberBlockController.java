package com.example.gasip.memberBlock.controller;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.memberBlock.dto.MemberBlockRequest;
import com.example.gasip.memberBlock.service.MemberBlockService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("users/block")
public class MemberBlockController {

    private final MemberBlockService memberBlockService;

    @PostMapping("")
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
