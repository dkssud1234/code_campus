package hanium.cocam.user;

import hanium.cocam.user.entity.Profile;
import hanium.cocam.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
