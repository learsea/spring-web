package com.csy.webmvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.csy.webmvc.bean.vo.ErrorResult;
import com.csy.webmvc.constant.Code;

@ControllerAdvice
public class MVCException {
	@ResponseBody
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String fileTooBig() {
		return ErrorResult.getResult(Code._200, "文件过大").toJSONString();
	}
}
