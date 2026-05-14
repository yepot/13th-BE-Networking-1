package cotato.backend.application.domain;

import cotato.backend.executive.domain.Executive;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(
	name = "application_likes",
	uniqueConstraints = {
		@UniqueConstraint(name = "uk_application_like_application_executive", columnNames = {"application_id", "executive_id"})
	},
	indexes = {
		@Index(name = "idx_application_like_application_id", columnList = "application_id"),
		@Index(name = "idx_application_like_executive_id", columnList = "executive_id")
	}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_like_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "application_id", nullable = false)
	private Application application;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "executive_id", nullable = false)
	private Executive executive;

	@Builder
	private ApplicationLike(Application application, Executive executive) {
		this.application = application;
		this.executive = executive;
	}

	public static ApplicationLike create(Application application, Executive executive) {
		return ApplicationLike.builder()
			.application(application)
			.executive(executive)
			.build();
	}
}
