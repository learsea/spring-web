package com.csy.webmvc.exception;

import com.csy.webmvc.constant.Code;

/**
 * 自定义业务层异常
 *
 * @author caishiyu
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -1353943450805355235L;
    private Code errorCode;

    /**
     * 自定义错误信息
     */
    public BusinessException(Code errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 自定义错误信息
     */
    public BusinessException(Code errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public Code getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Code errorCode) {
        this.errorCode = errorCode;
    }
}
