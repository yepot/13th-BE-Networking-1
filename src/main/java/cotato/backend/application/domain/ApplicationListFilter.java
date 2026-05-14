package cotato.backend.application.domain;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;

import cotato.backend.common.exception.AppException;
import cotato.backend.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplicationListFilter {

	GISU("gisu", List.of(
		Sort.Order.desc("period"),
		Sort.Order.asc("name"),
		Sort.Order.desc("id")
	)),
	LIKES("likes", List.of(
		Sort.Order.desc("likeCount"),
		Sort.Order.desc("id")
	)),
	GISU_LIKES("gisu+likes", List.of(
		Sort.Order.desc("period"),
		Sort.Order.desc("likeCount"),
		Sort.Order.asc("name"),
		Sort.Order.desc("id")
	)),
	;

	private final String value;
	private final List<Sort.Order> orders;

	public Sort toSort() {
		return Sort.by(orders);
	}

	public static ApplicationListFilter from(String value) {
		return Arrays.stream(values())
			.filter(filter -> filter.value.equals(value))
			.findFirst()
			.orElseThrow(() -> new AppException(
				ErrorCode.INVALID_PARAMETER,
				"filterBy는 likes, gisu, gisu+likes 중 하나여야 합니다."
			));
	}
}
