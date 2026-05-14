package cotato.backend.application.dto.request;

import cotato.backend.application.domain.ApplicationListFilter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ApplicationListRequest(

	@NotBlank(message = "filterBy는 필수입니다.")
	String filterBy,

	@Min(value = 1, message = "page는 1 이상이어야 합니다.")
	Integer page,

	@Min(value = 1, message = "pageSize는 1 이상이어야 합니다.")
	@Max(value = 10, message = "pageSize는 10 이하여야 합니다.")
	Integer pageSize
) {

	public ApplicationListRequest {
		page = page == null ? 1 : page;
		pageSize = pageSize == null ? 10 : pageSize;
	}

	public ApplicationListFilter toFilter() {
		return ApplicationListFilter.from(filterBy);
	}
}
