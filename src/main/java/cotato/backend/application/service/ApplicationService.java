package cotato.backend.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.applicant.domain.Applicant;
import cotato.backend.applicant.service.ApplicantService;
import cotato.backend.application.domain.Application;
import cotato.backend.application.domain.ApplicationLike;
import cotato.backend.application.domain.ApplicationListFilter;
import cotato.backend.application.dto.request.ApplicationCreateRequest;
import cotato.backend.application.dto.request.ApplicationLikeRequest;
import cotato.backend.application.dto.request.ApplicationListRequest;
import cotato.backend.application.dto.response.ApplicationCreateResponse;
import cotato.backend.application.dto.response.ApplicationDetailResponse;
import cotato.backend.application.dto.response.ApplicationLikeResponse;
import cotato.backend.application.dto.response.ApplicationLikedExecutiveResponse;
import cotato.backend.application.dto.response.ApplicationListItemResponse;
import cotato.backend.application.dto.response.ApplicationListResponse;
import cotato.backend.application.repository.ApplicationLikeRepository;
import cotato.backend.application.repository.ApplicationRepository;
import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.executive.domain.Executive;
import cotato.backend.executive.service.ExecutiveService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationService {

	private final ApplicantService applicantService;
	private final ExecutiveService executiveService;
	private final ApplicationRepository applicationRepository;
	private final ApplicationLikeRepository applicationLikeRepository;

	@Transactional
	public ApplicationCreateResponse create(ApplicationCreateRequest request) {
		validateDuplicateApplication(request.phoneNumber(), request.period());
		Applicant applicant = applicantService.findOrCreate(request.name(), request.age(), request.phoneNumber());
		Application application = applicationRepository.save(Application.from(request, applicant));
		return ApplicationCreateResponse.from(application);
	}

	public ApplicationDetailResponse findById(Long id) {
		Application application = getApplication(id);
		return ApplicationDetailResponse.from(application, getLikedExecutives(application.getId()));
	}

	@Transactional
	public ApplicationLikeResponse like(Long applicationId, ApplicationLikeRequest request) {
		Application application = getApplication(applicationId);
		Executive executive = executiveService.findExecutiveEntity(request.executiveId());
		applicationLikeRepository.findByApplicationAndExecutive(application, executive)
			.ifPresentOrElse(existingLike -> {
				applicationLikeRepository.delete(existingLike);
				application.decreaseLikeCount();
			}, () -> {
				applicationLikeRepository.save(ApplicationLike.create(application, executive));
				application.increaseLikeCount();
			});
		return ApplicationLikeResponse.from(application, executive.getId(), getLikedExecutives(applicationId));
	}

	public ApplicationListResponse findAll(ApplicationListRequest request) {
		ApplicationListFilter filter = request.toFilter();
		PageRequest pageRequest = PageRequest.of(request.page() - 1, request.pageSize(), filter.toSort());
		Page<ApplicationListItemResponse> applications = applicationRepository.findAll(pageRequest)
			.map(ApplicationListItemResponse::from);
		return ApplicationListResponse.from(filter, request.page(), request.pageSize(), applications);
	}

	private Application getApplication(Long id) {
		return applicationRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.APPLICATION_NOT_FOUND));
	}

	private List<ApplicationLikedExecutiveResponse> getLikedExecutives(Long applicationId) {
		return applicationLikeRepository.findAllWithExecutiveByApplicationId(applicationId).stream()
			.map(applicationLike -> ApplicationLikedExecutiveResponse.from(applicationLike.getExecutive()))
			.toList();
	}

	private void validateDuplicateApplication(String phoneNumber, Integer period) {
		if (applicationRepository.existsByPhoneNumberAndPeriod(phoneNumber, period)) {
			throw new AppException(ErrorCode.DUPLICATE_APPLICATION);
		}
	}
}
