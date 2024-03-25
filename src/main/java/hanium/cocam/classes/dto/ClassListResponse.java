package hanium.cocam.classes.dto;

import hanium.cocam.classes.ClassType;
import hanium.cocam.classes.Classes;
import hanium.cocam.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassListResponse {
    private String userName; // 유저 이름
    private ClassType classType; // 수업 방식
    private String classTitle; // 강의 제목
    private String classLevel;

    @Builder
    public ClassListResponse(User user, Classes classes) {
        this.userName = user.getUserName();
        this.classLevel = classes.getClassLevel();
        this.classTitle = classes.getClassTitle();
        this.classType = classes.getClassType();
    }
}
