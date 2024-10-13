package hanium.cocam.domain.tutee;

import hanium.cocam.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TuteeRepository extends JpaRepository<User, Long> {
}
