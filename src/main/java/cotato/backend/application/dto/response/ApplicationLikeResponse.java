package cotato.backend.application.dto.response;

import java.util.List;

import cotato.backend.application.domain.Application;

public record ApplicationLikeResponse(
	Long applicationId,
	boolean liked,
	Integer likeCount,
	List<ApplicationLikedExecutiveResponse> likedExecutives
) {

	public static ApplicationLikeResponse from(
		Application application,
		Long executiveId,
		List<ApplicationLikedExecutiveResponse> likedExecutives
	) {
		return new ApplicationLikeResponse(
			application.getId(),
			likedExecutives.stream().anyMatch(likedExecutive -> likedExecutive.executiveId().equals(executiveId)),
			application.getLikeCount(),
			likedExecutives
		);
	}
}
