package cotato.backend.common.dto;

import org.springframework.http.HttpStatus;
public abstract class BaseResponse {

	protected BaseResponse(HttpStatus status) {
	}
}