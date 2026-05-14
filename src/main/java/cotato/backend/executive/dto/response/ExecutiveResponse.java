package cotato.backend.executive.dto.response;

import cotato.backend.executive.domain.Executive;
import cotato.backend.executive.domain.ExecutiveRole;

public record ExecutiveResponse(
	Long id,
	String name,
	Integer age,
	String phoneNumber,
	ExecutiveRole role
) {

	public static ExecutiveResponse from(Executive executive) {
		return new ExecutiveResponse(
			executive.getId(),
			executive.getName(),
			executive.getAge(),
			executive.getPhoneNumber(),
			executive.getRole()
		);
	}
}
