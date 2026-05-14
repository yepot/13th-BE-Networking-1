package cotato.backend.applicant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.backend.applicant.domain.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

	Optional<Applicant> findByPhoneNumber(String phoneNumber);

	boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
