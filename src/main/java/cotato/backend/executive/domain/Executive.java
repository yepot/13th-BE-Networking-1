package cotato.backend.executive.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "executives")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Executive {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "executive_id")
	private Long id;

	@Column(name = "name", nullable = false, length = 10)
	private String name;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "phone_number", nullable = false, length = 11)
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 30)
	private ExecutiveRole role;

	@Builder
	private Executive(String name, Integer age, String phoneNumber, ExecutiveRole role) {
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public static Executive create(String name, Integer age, String phoneNumber, ExecutiveRole role) {
		return Executive.builder()
			.name(name)
			.age(age)
			.phoneNumber(phoneNumber)
			.role(role)
			.build();
	}

	public void update(String name, Integer age, String phoneNumber, ExecutiveRole role) {
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}
}
