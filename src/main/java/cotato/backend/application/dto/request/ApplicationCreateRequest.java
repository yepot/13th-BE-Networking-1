package cotato.backend.application.dto.request;

import cotato.backend.application.domain.ApplicationPart;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ApplicationCreateRequest(

	@NotNull(message = "이름은 필수입니다.")
	@Pattern(regexp = "^[가-힣]{2,10}$", message = "이름은 한글 2글자 이상 10글자 이하여야 합니다.")
	String name,

	@NotNull(message = "지원 기수는 필수입니다.")
	@Min(value = 1, message = "지원 기수는 1 이상의 정수여야 합니다.")
	Integer period,

	@NotNull(message = "나이는 필수입니다.")
	@Min(value = 22, message = "나이는 22살 이상이어야 합니다.")
	@Max(value = 30, message = "나이는 30살 이하여야 합니다.")
	Integer age,

	@NotNull(message = "지원 파트는 필수입니다.")
	ApplicationPart part,

	@NotNull(message = "실력은 필수입니다.")
	@Min(value = 0, message = "실력은 0 이상이어야 합니다.")
	@Max(value = 10, message = "실력은 10 이하여야 합니다.")
	Integer ability,

	@NotNull(message = "열정은 필수입니다.")
	@Min(value = 0, message = "열정은 0 이상이어야 합니다.")
	@Max(value = 10, message = "열정은 10 이하여야 합니다.")
	Integer passion,

	@NotNull(message = "휴대폰 번호는 필수입니다.")
	@Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호는 010으로 시작하는 11자리 숫자여야 합니다.")
	String phoneNumber
) {
}
