package com.example.gasip.member.controller;

import com.example.gasip.member.dto.*;
import com.example.gasip.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/mypage")
    public ResponseEntity<MemberMyPageResponse> getMyPage(@RequestBody @Valid MemberMyPageRequest memberMyPageRequest) {
        return ResponseEntity.ok(memberService.getMyPage(memberMyPageRequest));
    }

}
