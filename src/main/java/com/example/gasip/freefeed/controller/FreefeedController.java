package com.example.gasip.freefeed.controller;

import com.example.gasip.board.dto.BoardCreateRequest;
import com.example.gasip.freefeed.dto.FeedCreateRequest;
import com.example.gasip.freefeed.service.FreefeedService;
import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freefeed")
@RequiredArgsConstructor
public class FreefeedController {
    private final FreefeedService freefeedService;

    @PostMapping()
    public ResponseEntity<?> createFeed(
        @AuthenticationPrincipal MemberDetails memberDetails,
        @RequestBody @Valid FeedCreateRequest feedCreateRequest) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiUtils.success(
                    freefeedService.createFeed(feedCreateRequest, memberDetails)
                )
            );
    }
}
