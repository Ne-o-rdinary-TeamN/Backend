package spring.hackerthon.global.error.exception.handler;

import spring.hackerthon.global.error.exception.GeneralException;
import spring.hackerthon.global.response.BaseErrorCode;

public class GeneralHandler extends GeneralException {
    public GeneralHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
