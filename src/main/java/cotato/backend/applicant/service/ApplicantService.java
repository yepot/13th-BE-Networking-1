package cotato.backend.applicant.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.applicant.domain.Applicant;
import cotato.backend.applicant.dto.request.ApplicantUpdateRequest;
import cotato.backend.applicant.dto.response.ApplicantResponse;
import cotato.backend.applicant.repository.ApplicantRepository;
import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantService {

	private final ApplicantRepository applicantRepository;

	@Transactional
	public Applicant findOrCreate(String name, Integer age, String phoneNumber) {
		return applicantRepository.findByPhoneNumber(phoneNumber)
			.map(applicant -> {
				applicant.update(name, age, phoneNumber);
				return applicant;
			})
			.orElseGet(() -> applicantRepository.save(Applicant.create(name, age, phoneNumber)));
	}

	public ApplicantResponse findById(Long id) {
		return ApplicantResponse.from(getApplicant(id));
	}

	@Transactional
	public ApplicantResponse update(Long id, ApplicantUpdateRequest request) {
		validateDuplicatePhoneNumber(id, request.phoneNumber());
		Applicant applicant = getApplicant(id);
		applicant.update(request.name(), request.age(), request.phoneNumber());
		return ApplicantResponse.from(applicant);
	}

	private Applicant getApplicant(Long id) {
		return applicantRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICANT_NOT_FOUND));
	}

	private void validateDuplicatePhoneNumber(Long id, String phoneNumber) {
		if (applicantRepository.existsByPhoneNumberAndIdNot(phoneNumber, id)) {
			throw new AppException(ErrorCode.DUPLICATE_APPLICANT_PHONE_NUMBER);
		}
	}
}
