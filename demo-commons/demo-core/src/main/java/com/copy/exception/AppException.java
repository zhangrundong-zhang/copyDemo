package com.copy.exception;
import com.copy.enums.MessageCode;

/**
 * 自定义异常
 *
 * @author 张润东
 * @date 20181117
 * @since v1.0
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -2398022078271719334L;

    private MessageCode messageCode;

    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(MessageCode messageCode) {
        super(messageCode.getMsg());
        this.messageCode = messageCode;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(MessageCode messageCode) {
        this.messageCode = messageCode;
    }
}
