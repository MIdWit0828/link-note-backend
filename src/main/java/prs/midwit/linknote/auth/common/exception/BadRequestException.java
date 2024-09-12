package prs.midwit.linknote.auth.common.exception;

import lombok.Getter;
import prs.midwit.linknote.auth.common.exception.type.ExceptionCode;

@Getter
public class BadRequestException extends CustomException {
    public BadRequestException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
