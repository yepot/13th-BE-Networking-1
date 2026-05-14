package cotato.backend.executive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cotato.backend.executive.domain.Executive;

public interface ExecutiveRepository extends JpaRepository<Executive, Long> {
}
