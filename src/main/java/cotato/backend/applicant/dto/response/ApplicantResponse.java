package cotato.backend.applicant.dto.response;

import cotato.backend.applicant.domain.Applicant;

public record ApplicantResponse(
	Long id,
	String name,
	Integer age,
	String phoneNumber
) {

	public static ApplicantResponse from(Applicant applicant) {
		return new ApplicantResponse(
			applicant.getId(),
			applicant.getName(),
			applicant.getAge(),
			applicant.getPhoneNumber()
		);
	}
}
