package cotato.backend.executive.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.executive.domain.Executive;
import cotato.backend.executive.dto.request.ExecutiveUpdateRequest;
import cotato.backend.executive.dto.response.ExecutiveResponse;
import cotato.backend.executive.repository.ExecutiveRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ExecutiveService {

	private final ExecutiveRepository executiveRepository;

	public ExecutiveResponse findById(Long id) {
		return ExecutiveResponse.from(getExecutive(id));
	}

	@Transactional
	public ExecutiveResponse update(Long id, ExecutiveUpdateRequest request) {
		Executive executive = getExecutive(id);
		executive.update(request.name(), request.age(), request.phoneNumber(), request.role());
		return ExecutiveResponse.from(executive);
	}

	private Executive getExecutive(Long id) {
		return executiveRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.EXECUTIVE_NOT_FOUND));
	}
}
