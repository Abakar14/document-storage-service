package com.bytmasoft.dss.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bytmasoft.dss.messages.ResponseMessage;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseMessage handleMaxSizeException(MaxUploadSizeExceededException ex) {

		return new ResponseMessage(ex.getMessage());
	}
}
