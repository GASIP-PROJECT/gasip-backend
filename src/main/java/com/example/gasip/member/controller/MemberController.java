package com.example.gasip.member.controller;

import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.dto.*;
import com.example.gasip.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberSignUpResponse> signup(@RequestBody @Valid MemberSignUpRequest memberSignUpRequest) {
        return ResponseEntity.ok(memberService.signup(memberSignUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLogInResponse> login(@RequestBody @Valid MemberLogInRequest memberLogInRequest) {
        return ResponseEntity.ok(memberService.login(memberLogInRequest));
    }

    @GetMapping("/mypage")
    public ResponseEntity<MemberMyPageResponse> getMyPage(@AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity.ok(memberService.getMyPage(memberDetails.getId()));
    }

    @GetMapping("/myboards")
    public ResponseEntity<MemberMyBoardResponse> getMyBoards(@AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity.ok(memberService.getBoards(memberDetails.getId()));
    }

}
