package hanium.cocam.domain.tutor;

import hanium.cocam.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<User, Long> {
}
