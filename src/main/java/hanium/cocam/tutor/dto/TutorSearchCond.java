package hanium.cocam.tutor.dto;

import hanium.cocam.user.Category;
import hanium.cocam.user.UserSex;
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
    private Category category;
    private String orderCondition;
}
