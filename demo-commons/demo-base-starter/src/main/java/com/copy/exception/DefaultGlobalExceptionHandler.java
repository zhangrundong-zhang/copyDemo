package com.copy.exception;

import com.copy.enums.MessageCode;
import com.copy.response.ResponseBase;
import com.copy.response.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 统一异常处理
 *
 * @author zhongyong
 * @date 20181117
 * @since v1.0
 */
@Slf4j
public class DefaultGlobalExceptionHandler {

    /**
     * 处理自定义业务异常处理
     */
    @ExceptionHandler(AppException.class)
    public ResponseBase handleCpsException(AppException e) {
        MessageCode messageCode = e.getMessageCode();
        log.error(e.getMessage());
        if (messageCode == null) {
            return ResponseBuilder.buildErrorMsg(e.getMessage());
        } else {
            return ResponseBuilder.buildErrorMsg(messageCode);
        }
    }

    /**
     * 参数校验异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBase handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getFieldError().getDefaultMessage();
        log.error(errorMsg);
        return ResponseBuilder.buildErrorMsg(errorMsg);
    }

    @ExceptionHandler(BindException.class)
    public ResponseBase handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getFieldError().getDefaultMessage();
        log.error(errorMsg);
        return ResponseBuilder.buildErrorMsg(errorMsg);
    }

    /**
     * 上传文件大小限制异常处理
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseBase handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error(e.getMessage(), e);
        return ResponseBuilder.buildErrorMsg(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseBase handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseBuilder.buildErrorMsg(MessageCode.ERR105);
    }
}
