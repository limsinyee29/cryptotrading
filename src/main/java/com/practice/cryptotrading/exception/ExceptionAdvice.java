package com.practice.cryptotrading.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.practice.cryptotrading.error.ErrorCode;
import com.practice.cryptotrading.restservice.RestServiceException;

/**
 * @author Sin Yee
 *
 */

@ControllerAdvice
public class ExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

	@ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<ErrorResponse> serviceException(ServiceException e) {
		logger.info("Service Exception", e);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getErrorCode(), e.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = RestServiceException.class)
	public ResponseEntity<ErrorResponse> restServiceException(RestServiceException e) {
		logger.info("Rest Service Exception", e);
		return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> invalidFieldException(MethodArgumentNotValidException e) {
		List<FieldError> errors = e.getBindingResult().getFieldErrors();
		List<ErrorResponse.Error> errs = errors.stream().map(x -> new ErrorResponse.Error(x.getField(),
				ErrorCode.INVALID_FIELD.getErrorCode(), x.getDefaultMessage())).toList();
		ErrorResponse response = new ErrorResponse(errs);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> unknownException(Exception e) {
		logger.error("Unknown error ", e);
		return ResponseEntity.internalServerError().body(new ErrorResponse(ErrorCode.UNKNOWN));
	}

	@ExceptionHandler({ Unauthorized.class })
	public ResponseEntity<ErrorResponse> unauthorize(Unauthorized e) {
		logger.info("", e);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ Forbidden.class })
	public ResponseEntity<ErrorResponse> forbidden(Forbidden e) {
		logger.info("", e);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

}
