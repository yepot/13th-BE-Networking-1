package cotato.backend.application.domain;

import java.time.LocalDateTime;

import cotato.backend.applicant.domain.Applicant;
import cotato.backend.application.dto.request.ApplicationCreateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(
	name = "applications",
	uniqueConstraints = {
		@UniqueConstraint(name = "uk_application_phone_period", columnNames = {"phone_number", "period"})
	},
	indexes = {
		@Index(name = "idx_application_period", columnList = "period"),
		@Index(name = "idx_application_like_count", columnList = "like_count")
	}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	private Applicant applicant;

	@Column(name = "name", nullable = false, length = 10)
	private String name;

	@Column(name = "period", nullable = false)
	private Integer period;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Enumerated(EnumType.STRING)
	@Column(name = "part", nullable = false, length = 20)
	private ApplicationPart part;

	@Column(name = "ability", nullable = false)
	private Integer ability;

	@Column(name = "passion", nullable = false)
	private Integer passion;

	@Column(name = "phone_number", nullable = false, length = 11)
	private String phoneNumber;

	@Column(name = "application_time", nullable = false)
	private LocalDateTime applicationTime;

	@Column(name = "like_count", nullable = false)
	private Integer likeCount;

	@Builder
	private Application(
		Applicant applicant,
		String name,
		Integer period,
		Integer age,
		ApplicationPart part,
		Integer ability,
		Integer passion,
		String phoneNumber,
		LocalDateTime applicationTime,
		Integer likeCount
	) {
		this.applicant = applicant;
		this.name = name;
		this.period = period;
		this.age = age;
		this.part = part;
		this.ability = ability;
		this.passion = passion;
		this.phoneNumber = phoneNumber;
		this.applicationTime = applicationTime;
		this.likeCount = likeCount;
	}

	public static Application from(ApplicationCreateRequest request, Applicant applicant) {
		return Application.builder()
			.applicant(applicant)
			.name(request.name())
			.period(request.period())
			.age(request.age())
			.part(request.part())
			.ability(request.ability())
			.passion(request.passion())
			.phoneNumber(request.phoneNumber())
			.applicationTime(LocalDateTime.now())
			.likeCount(0)
			.build();
	}

	public void increaseLikeCount() {
		this.likeCount++;
	}

	public void decreaseLikeCount() {
		if (this.likeCount > 0) {
			this.likeCount--;
		}
	}
}
