package cotato.backend.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.backend.application.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

	boolean existsByPhoneNumberAndPeriod(String phoneNumber, Integer period);
}
