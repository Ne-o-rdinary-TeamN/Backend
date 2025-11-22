package spring.hackerthon.global.response.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.hackerthon.global.response.BaseErrorCode;
import spring.hackerthon.global.response.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    SAME_USERNAME(HttpStatus.BAD_REQUEST, "MEMBER4000", "중복된 이름입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "아이디 또는 비밀번호가 일치하지 않습니다."),
    SAME_USERID(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 존재하는 아이디입니다."),

    CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "CONTENT4000", "존재하지 않는 컨텐츠입니다."),

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST4000", "존재하지 않는 게시글입니다."),

    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY4000", "존재하지 않는 카테고리입니다.")
    ;

    private final HttpStatus httpStatus;
    private String code;
    private String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
