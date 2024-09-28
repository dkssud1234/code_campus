package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorshipRepository extends JpaRepository<Mentorship, Long> {

    List<Mentorship> findByTutorAndMentorshipStatus(User tutor, String mentorshipStatus);
}
