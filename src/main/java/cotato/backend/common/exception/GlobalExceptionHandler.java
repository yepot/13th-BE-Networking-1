package cotato.backend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cotato.backend.common.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	// 처리되지 않은 모든 예외를 잡는 핸들러
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllException(Exception e, HttpServletRequest request) {
		log.error("처리되지 않은 예외 발생: ", e);
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
		ErrorResponse errorResponse = ErrorResponse.of(
			ErrorCode.INTERNAL_SERVER_ERROR,
			request
		);
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(errorResponse);
	}

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleAppCustomException(AppException e, HttpServletRequest request) {
		log.error("AppException 발생: {}", e.getMessage());
		log.error("에러가 발생한 지점 {}, {}", request.getMethod(), request.getRequestURI());
		ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), e.getMessage(), request);
		return ResponseEntity
			.status(e.getErrorCode().getHttpStatus())
			.body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                               HttpServletRequest request) {
		FieldError fieldError = e.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
		String message = fieldError == null ? ErrorCode.INVALID_PARAMETER.getMessage() : fieldError.getDefaultMessage();
		log.error("유효성 검사 실패: {}", message);
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_PARAMETER, message, request);
		return ResponseEntity
			.status(ErrorCode.INVALID_PARAMETER.getHttpStatus())
			.body(errorResponse);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e,
                                                                            HttpServletRequest request) {
		String message = e.getConstraintViolations().stream()
			.findFirst()
			.map(violation -> violation.getMessage())
			.orElse(ErrorCode.INVALID_PARAMETER.getMessage());
		log.error("제약 조건 위반: {}", message);
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_PARAMETER, message, request);
		return ResponseEntity
			.status(ErrorCode.INVALID_PARAMETER.getHttpStatus())
			.body(errorResponse);
	}

	@ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
	public ResponseEntity<ErrorResponse> handleBadRequestException(Exception e, HttpServletRequest request) {
		String message = "요청 본문 또는 파라미터 형식이 올바르지 않습니다.";
		log.error("잘못된 요청 형식: {}", e.getMessage());
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_PARAMETER, message, request);
		return ResponseEntity
			.status(ErrorCode.INVALID_PARAMETER.getHttpStatus())
			.body(errorResponse);
	}
}
