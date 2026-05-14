package cotato.backend.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cotato.backend.application.domain.Application;
import cotato.backend.application.domain.ApplicationPart;

public record ApplicationDetailResponse(
	Long id,
	Long applicantId,
	String name,
	Integer period,
	Integer age,
	ApplicationPart part,
	Integer ability,
	Integer passion,
	String phoneNumber,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime applicationTime,
	Integer likeCount,
	List<ApplicationLikedExecutiveResponse> likedExecutives
) {

	public static ApplicationDetailResponse from(
		Application application,
		List<ApplicationLikedExecutiveResponse> likedExecutives
	) {
		return new ApplicationDetailResponse(
			application.getId(),
			application.getApplicant().getId(),
			application.getName(),
			application.getPeriod(),
			application.getAge(),
			application.getPart(),
			application.getAbility(),
			application.getPassion(),
			application.getPhoneNumber(),
			application.getApplicationTime(),
			application.getLikeCount(),
			likedExecutives
		);
	}
}
