package cotato.backend.applicant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(
	name = "applicants",
	uniqueConstraints = {
		@UniqueConstraint(name = "uk_applicant_phone_number", columnNames = "phone_number")
	},
	indexes = {
		@Index(name = "idx_applicant_phone_number", columnList = "phone_number")
	}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicant_id")
	private Long id;

	@Column(name = "name", nullable = false, length = 10)
	private String name;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "phone_number", nullable = false, length = 11)
	private String phoneNumber;

	@Builder
	private Applicant(String name, Integer age, String phoneNumber) {
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}

	public static Applicant create(String name, Integer age, String phoneNumber) {
		return Applicant.builder()
			.name(name)
			.age(age)
			.phoneNumber(phoneNumber)
			.build();
	}

	public void update(String name, Integer age, String phoneNumber) {
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}
}
