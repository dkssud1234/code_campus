package hanium.cocam.domain.mentorship;

import hanium.cocam.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface MentorshipRepository extends JpaRepository<Mentorship, Long> {

    List<Mentorship> findByTutorAndMentorshipStatus(User tutor, String mentorshipStatus);

    // 튜티로 멘토십 찾기
    Optional<Mentorship> findByTutee(User tuteeNo);

    List<Mentorship> findByTuteeAndMentorshipStatus(User tutee, String mentorshipStatus);

    List<Mentorship> findByTuteeAndMentorshipStatusIn(User tutee, List<String> mentorshipStatus);
}


