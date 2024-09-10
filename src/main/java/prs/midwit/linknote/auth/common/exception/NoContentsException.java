package prs.midwit.linknote.auth.common.exception;

import lombok.Getter;
import prs.midwit.linknote.auth.common.exception.type.ExceptionCode;

@Getter
public class NoContentsException extends CustomException {
    public NoContentsException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
