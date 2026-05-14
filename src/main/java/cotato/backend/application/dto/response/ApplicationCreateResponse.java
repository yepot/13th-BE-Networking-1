package cotato.backend.application.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import cotato.backend.application.domain.Application;
import cotato.backend.application.domain.ApplicationPart;

public record ApplicationCreateResponse(
	Long id,
	String name,
	Integer period,
	Integer age,
	ApplicationPart part,
	Integer ability,
	Integer passion,
	String phoneNumber,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime applicationTime,
	Integer likeCount
) {

	public static ApplicationCreateResponse from(Application application) {
		return new ApplicationCreateResponse(
			application.getId(),
			application.getName(),
			application.getPeriod(),
			application.getAge(),
			application.getPart(),
			application.getAbility(),
			application.getPassion(),
			application.getPhoneNumber(),
			application.getApplicationTime(),
			application.getLikeCount()
		);
	}
}
