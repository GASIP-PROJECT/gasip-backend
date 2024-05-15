package com.example.gasip.member.controller;

import com.example.gasip.global.api.ApiUtils;
import com.example.gasip.global.security.MemberDetails;
import com.example.gasip.member.dto.*;
import com.example.gasip.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    @Tag(name = "Service 등록", description = "교수 평가 서비스를 제공하는 Gasip을 사용하기 위한 토큰 발급 및 인증 프로세스를 제공합니다.")
    @Operation(summary = "계정 생성", description = "Gasip 서비스를 이용하기 위해서는 토큰을 발급받아야 합니다. \n" +
        "인가 토큰을 발급을 위해서 Gasip 서비스에 사용할 계정을 가입해야 합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Created",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = MemberSignUpResponse.class))}),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
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
    @Tag(name = "Service 등록", description = "교수 평가 서비스를 제공하는 Gasip을 사용하기 위한 토큰 발급 및 인증 프로세스를 제공합니다.")
    @Operation(summary = "로그인 토큰 인증 및 발급", description = "Gasip에서 계정 생성을 한 이후 인가 토큰 발급을 수행해야 합니다. 인가 토큰 발급은 "
        + "로그인을 통해 진행하게 됩니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = MemberLogInResponse.class))}),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
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
    @Tag(name = "Service Member Interface", description = "API 호출 시점을 기준으로 Gasip 플랫폼에 가입한 유저들의 정보 목록을 사용자에게 제공합니다." +
        "사용자가 작성한 게시글, 현재 토큰의 인가여부 등 자신의 계정에 관련된 정보를 확인 할 수 있습니다.")
    @Operation(summary = "사용자 상세 정보", description = "API 호출 시점을 기준으로 현재 로그인 한 사용자의 상세정보를 보여줍니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = MemberMyPageResponse.class)
            )})
    })
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
    @Tag(name = "Service Member Interface", description = "API 호출 시점을 기준으로 Gasip 플랫폼에 가입한 유저들의 정보 목록을 사용자에게 제공합니다." +
        "사용자가 작성한 게시글, 현재 토큰의 인가여부 등 자신의 계정에 관련된 정보를 확인 할 수 있습니다.")
    @Operation(summary = "사용자가 작성한 게시글 목록", description = "API 호출 시점을 기준으로 현재 로그인 한 사용자가 작성한 게시글 목록은 보여줍니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = MemberMyBoardResponse.class)
            )})
    })
    public ResponseEntity<?> getMyBoards(
        @AuthenticationPrincipal MemberDetails memberDetails,
        Pageable pageable) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.getMyBoards(memberDetails,pageable)
                )
            );
    }

    @GetMapping("/authcheck")
    @Tag(name = "Service Member Interface", description = "API 호출 시점을 기준으로 Gasip 플랫폼에 가입한 유저들의 정보 목록을 사용자에게 제공합니다." +
        "사용자가 작성한 게시글, 현재 토큰의 인가여부 등 자신의 계정에 관련된 정보를 확인 할 수 있습니다.")
    @Operation(summary = "발급된 토큰 만료 여부 확인", description = "API 호출 시점을 기준으로 현재 로그인 한 사용자의 토큰 만료 여부를 확인합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<?> isAuthenticated(@RequestHeader(name = "Authorization") String Authorization) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.isAuthenticated(Authorization)
                )
            );
    }

    @PutMapping("/passwords")
    @Tag(name = "Service Member Interface", description = "비밀번호를 변경하는 api 입니다.")
    @Operation(summary = "비밀번호 변경", description = "비밀번호를 변경하는 api 입니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<?> editPassword(@AuthenticationPrincipal MemberDetails memberDetails,
                                          @RequestBody MemberUpdatePasswordRequest memberUpdatePasswordRequest) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.updatePassword(memberDetails,memberUpdatePasswordRequest)
                )
            );
    }
    @PutMapping("/passwords/noauth")
    @Tag(name = "Service Member Interface", description = "로그인 화면에서 비밀번호를 재설정하는 api 입니다.")
    @Operation(summary = "로그인 전 비밀번호 재설정", description = "비밀번호를 재설정하는 api 입니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<?> editPasswordWithoutAuth(
            @RequestBody MemberResetPasswordRequest memberResetPasswordRequest) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.resetPassword(memberResetPasswordRequest)
                )
            );
    }

    @PutMapping("/nicknames")
    @Tag(name = "Service Member Interface", description = "닉네임을 변경하는 api 입니다.")
    @Operation(summary = "닉네임 변경", description = "닉네임 변경하는 api 입니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<?> editNickname(@AuthenticationPrincipal MemberDetails memberDetails,
                                          @RequestBody MemberUpdateNicknameRequest memberUpdateNicknameRequest) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.updateNickname(memberDetails,memberUpdateNicknameRequest)
                )
            );
    }

    @PostMapping("/emails/verification-requests")
    @Tag(name = "Service Member Interface", description = "이메일 인증 api 입니다.")
    @Operation(summary = "이메일 인증번호 전송 api", description = "이메일 인증 api 입니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity sendMessage(@RequestParam("email") String email) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.sendCodeToEmail(email)
                )
            );
    }

    @PostMapping("/emails/verification-requests/exist")
    @Tag(name = "Service Member Interface", description = "기존 이메일 인증 api 입니다.")
    @Operation(summary = "이메일 인증번호 전송 api", description = "기존 이메일 인증 api 입니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity sendMessageToExistEmail(@RequestParam("email") String email) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.sendCodeToExistedEmail(email)
                )
            );
    }

    @GetMapping("/emails/verifications")
    @Tag(name = "Service Member Interface", description = "이메일 인증번호 검증 api 입니다.")
    @Operation(summary = "인증번호 6자리 검증", description = "인증번호 검증 api 입니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity verificationEmail(@RequestParam("email") String email,
                                            @RequestParam("code") String authCode) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.verifiedCode(email, authCode)
                )
            );
    }

    @DeleteMapping()
    @Tag(name = "Service Member Interface", description = "회원 탈퇴 api 입니다.")
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 api 입니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity withdrawAccount(@AuthenticationPrincipal MemberDetails memberDetails) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    memberService.withdrawAccount(memberDetails)
                )
            );
    }

}
