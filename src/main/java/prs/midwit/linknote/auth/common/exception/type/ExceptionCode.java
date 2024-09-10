package prs.midwit.linknote.auth.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
* 1xxx :
* 2XXX
* 3XXX
* 4xxx : 인증, 인가관련
* 5xxx : 프로젝트 오류 관련
*
*
* x1xx : 조회하려는 콘텐츠가 없을 때
* */
@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    FAIL_LOGIN(4000, "로그인에 실패하였습니다."),
    NOT_FOUND_REFRESH_TOKEN(4001, "해당 리프레시 토큰이 유효하지 않습니다."),
    UNAUTHORIZED(4002, "인증 되지 않은 요청입니다."),
    ACCESS_DENIED(4003, "허가 되지 않은 요청입니다."),

    NO_CONTENTS_PROJECT(5100,"요정한 정보에 맞는 프로젝트가 존재하지 않습니다.");

    private final int code;
    private final String message;
}
