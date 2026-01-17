package com.leonardo.api;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAPI {
	
//	 	@ExceptionHandler(IndexOutOfBoundsException.class)
//	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	    public ErrorMessage TodoException(Exception ex,  WebRequest request) {
//	        return new ErrorMessage("Đối tượng không tồn tại", ex.getCause());
//	    }
//	 	
//	 	@ExceptionHandler(Exception.class)
//	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	    public ErrorMessage CommonException(Exception ex,  WebRequest request) {
//	        return new ErrorMessage("Đã xảy ra lỗi trong khi biên dịch", ex.getCause());
//	    }
//	 	
//	 	@ExceptionHandler(InternalError.class)
//	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	    public ErrorMessage InternalException(Exception ex,  WebRequest request) {
//	        return new ErrorMessage("Đã xảy ra lỗi Internal trong khi biên dịch", ex.getCause());
//	    }
//	 	
//	 	@ExceptionHandler(NullPointerException.class)
//	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	    public ErrorMessage NullException(Exception ex,  WebRequest request) {
//	        return new ErrorMessage("Đối tượng bị null", ex.getCause());
//	    }
//	 	
//	 	@ExceptionHandler(MaxUploadSizeExceededException.class)
//	 	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	 	public ErrorMessage handleMaxSizeException(MaxUploadSizeExceededException ex) {
//	 	    return new ErrorMessage("File có kích thước quá lớn", ex.getCause());
//	 	}
}
