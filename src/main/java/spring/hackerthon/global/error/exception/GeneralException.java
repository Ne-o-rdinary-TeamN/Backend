package spring.hackerthon.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spring.hackerthon.global.response.BaseErrorCode;
import spring.hackerthon.global.response.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
