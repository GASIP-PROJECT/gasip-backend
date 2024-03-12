package com.example.gasip.member.controller;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.dto.*;
import com.example.gasip.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member Controller", description = "Member 관련 API입니다.")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid MemberSignUpRequest memberSignUpRequest) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiUtils.success(
                    memberService.signup(memberSignUpRequest)
                )
            );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid MemberLogInRequest memberLogInRequest) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.login(memberLogInRequest)
                )
            );
    }

    @GetMapping("/mypage")
    @Operation(summary = "사용자의 정보 요청", description = "로그인 된 사용자의 정보를 요청합니다.", tags = {"Member Controller"})
    public ResponseEntity<?> getMyPage(@AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.getMyPage(memberDetails.getId())
                )
            );
    }

    @GetMapping("/myboards")
    @Operation(summary = "사용자가 작성한 게시글 목록 요청", description = "로그인 된 사용자가 작성한 게시글 목록을 요청합니다.", tags = {"Member Controller"})
    public ResponseEntity<?> getMyBoards(@AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.getBoards(memberDetails.getId())
                )
            );
    }

    @GetMapping("/authcheck")
    public ResponseEntity<?> isAuthenticated(@RequestHeader(name = "Authorization") String Authorization) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.isAuthenticated(Authorization)
                )
            );
    }

}
