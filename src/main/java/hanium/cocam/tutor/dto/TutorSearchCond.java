package hanium.cocam.tutor.dto;

import hanium.cocam.user.entity.UserSex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TutorSearchCond {
    private UserSex userSex;
    private String level;
    private String classArea;
    private String keyword;
    private String orderCondition;
}
