package cotato.backend.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cotato.backend.application.domain.Application;
import cotato.backend.application.domain.ApplicationLike;
import cotato.backend.executive.domain.Executive;

public interface ApplicationLikeRepository extends JpaRepository<ApplicationLike, Long> {

	Optional<ApplicationLike> findByApplicationAndExecutive(Application application, Executive executive);

	@Query("""
		select applicationLike
		from ApplicationLike applicationLike
		join fetch applicationLike.executive
		where applicationLike.application.id = :applicationId
		order by applicationLike.id asc
		""")
	List<ApplicationLike> findAllWithExecutiveByApplicationId(@Param("applicationId") Long applicationId);
}
