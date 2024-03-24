package hanium.cocam.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {
    List<Classes> findByUserNo_UserNo(Long userNo);
}
