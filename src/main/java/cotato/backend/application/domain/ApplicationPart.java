package cotato.backend.application.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplicationPart {

	PLANNING("기획"),
	DESIGNER("디자이너"),
	FRONTEND("프론트엔드"),
	BACKEND("백엔드"),
	;

	private final String description;

	@JsonValue
	public String getDescription() {
		return description;
	}

	@JsonCreator
	public static ApplicationPart from(String value) {
		for (ApplicationPart part : values()) {
			if (part.description.equals(value)) {
				return part;
			}
		}
		throw new AppException(
			ErrorCode.INVALID_PARAMETER,
			"지원 파트는 기획, 디자이너, 프론트엔드, 백엔드 중 하나여야 합니다."
		);
	}
}
