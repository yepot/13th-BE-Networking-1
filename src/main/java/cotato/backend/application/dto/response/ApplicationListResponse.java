package cotato.backend.application.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;

import cotato.backend.application.domain.ApplicationListFilter;

public record ApplicationListResponse(
	String filterBy,
	int page,
	int pageSize,
	int totalPages,
	long totalElements,
	List<ApplicationListItemResponse> applications
) {

	public static ApplicationListResponse from(
		ApplicationListFilter filter,
		int page,
		int pageSize,
		Page<ApplicationListItemResponse> applications
	) {
		return new ApplicationListResponse(
			filter.getValue(),
			page,
			pageSize,
			applications.getTotalPages(),
			applications.getTotalElements(),
			applications.getContent()
		);
	}
}
