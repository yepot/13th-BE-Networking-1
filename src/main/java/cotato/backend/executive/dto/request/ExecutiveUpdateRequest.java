package cotato.backend.executive.dto.request;

import cotato.backend.executive.domain.ExecutiveRole;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ExecutiveUpdateRequest(

	@NotNull(message = "이름은 필수입니다.")
	@Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2글자 이상 10글자 이하여야 합니다.")
	String name,

	@NotNull(message = "나이는 필수입니다.")
	@Min(value = 22, message = "나이는 22살 이상이어야 합니다.")
	@Max(value = 30, message = "나이는 30살 이하여야 합니다.")
	Integer age,

	@NotNull(message = "휴대폰 번호는 필수입니다.")
	@Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리 숫자여야 합니다.")
	String phoneNumber,

	@NotNull(message = "운영진 역할은 필수입니다.")
	ExecutiveRole role
) {
}
