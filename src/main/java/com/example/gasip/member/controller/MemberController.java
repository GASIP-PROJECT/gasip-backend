package com.example.gasip.member.controller;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.dto.*;
import com.example.gasip.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
