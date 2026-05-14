package cotato.backend.application.dto.response;

import cotato.backend.application.domain.Application;
import cotato.backend.application.domain.ApplicationPart;

public record ApplicationListItemResponse(
	Long id,
	String name,
	Integer period,
	ApplicationPart part,
	Integer likeCount
) {

	public static ApplicationListItemResponse from(Application application) {
		return new ApplicationListItemResponse(
			application.getId(),
			application.getName(),
			application.getPeriod(),
			application.getPart(),
			application.getLikeCount()
		);
	}
}
