package cotato.backend.executive.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExecutiveRole {

	PARTJANG("파트장"),
	PLANNING_TEAMJANG("기획팀장"),
	PROMOTION_TEAMJANG("홍보팀장"),
	VICE_PRESIDENT("부회장"),
	PRESIDENT("회장"),
	EDUCATION_TEAMJANG("교육팀장"),
	;

	private final String value;

	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public static ExecutiveRole from(String value) {
		return Arrays.stream(values())
			.filter(role -> role.value.equals(value))
			.findFirst()
			.orElseThrow(() -> new AppException(
				ErrorCode.INVALID_PARAMETER,
				"role은 파트장, 기획팀장, 홍보팀장, 부회장, 회장, 교육팀장 중 하나여야 합니다."
			));
	}
}
