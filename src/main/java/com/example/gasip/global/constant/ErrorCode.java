package com.example.gasip.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 기존 Exception 에 대한 ErrorCode
    NO_HANDLER_FOUND("존재하지 않는 URL"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED("지원되지 않는 요청 메소드"),

    METHOD_ARGUMENT_TYPE_MISMATCH("요청 파라미터 오류"),
    PARAM_PARSING_MAPPING_ERROR("요청 입력 파싱 또는 매핑 오류"),
    BEAN_VALIDATION_ERROR("입력 데이터 유효성 검증 오류"),

    INTERNAL_SERVER_ERROR("Internal Server Error"),

    UNAUTHORIZED("인증 오류"),

    // Custom Exception 에 대한 ErrorCode
    NO_SUCH_ENTITY("존재하지 않는 엔티티"),
    INVALID_PARAM_ERROR("유효하지 않은 데이터"),
    DUPLICATE_EMAIL("이미 존재하는 이메일"),
    ACCESS_FORBIDDEN("권한 오류"),
    DUPLICATE_LIKE("좋아요 중복 오류"),
    NO_STOCK_ERROR("재고 부족 오류"),
    DUPLICATE_PHONENUMBER("이미 존재하는 휴대폰 번호"),
    DUPLICATE_REPORT("신고 중복 오류"),
    CANNOT_REPORT_YOURSELF("자신이 작성한 글을 신고할 수 없습니다."),

    // Member
    NOT_FOUND_MEMBER("회원 정보가 없습니다. 로그인 후 이용해주세요."),
    DUPLICATE_MEMBER("이미 등록된 회원입니다."),
    WITHDRAWED_MEMBER("탈퇴한 회원입니다."),
    DUPLICATE_NICKNAME("이미 등록된 닉네임입니다."),
    NO_SUCH_CODE("코드가 발급되지 않았습니다."),
    INVALID_CODE("입력한 발급코드가 일치하지 않습니다."),
    // Professor
    NOT_FOUND_PROFESSOR("교수 정보 조회 불가 오류"),
    //Board
    INVALID_WRITTER("작성자만 글을 수정하거나 삭제할 수 있습니다."),
    NOT_FOUND_BOARD("해당 게시글을 찾을 수 없습니다."),

    // Grade
    DUPLICATE_GRADE("이미 평점을 등록했습니다."),
    INVALID_GRADEPOINT("평점은 0~5점 사이로 입력해주세요.");

    private final String message;

}
