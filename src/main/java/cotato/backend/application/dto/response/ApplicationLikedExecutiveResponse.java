package cotato.backend.application.dto.response;

import cotato.backend.executive.domain.Executive;
import cotato.backend.executive.domain.ExecutiveRole;

public record ApplicationLikedExecutiveResponse(
	Long executiveId,
	String name,
	ExecutiveRole role
) {

	public static ApplicationLikedExecutiveResponse from(Executive executive) {
		return new ApplicationLikedExecutiveResponse(
			executive.getId(),
			executive.getName(),
			executive.getRole()
		);
	}
}
