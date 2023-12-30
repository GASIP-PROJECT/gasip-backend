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
    DUPLICATE_LIKE("중복 좋아요 오류"),
    NO_STOCK_ERROR("재고 부족 오류"),
    NOT_FOUND_MEMBER("멤버 조회 불가 오류"),
    DUPLICATE_PHONENUMBER("이미 존재하는 휴대폰 번호");

    private final String message;
}
