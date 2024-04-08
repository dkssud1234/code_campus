package hanium.cocam.classes.dto;

import hanium.cocam.classes.ClassType;
import hanium.cocam.classes.Classes;
import hanium.cocam.user.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassListResponse {
    private String userName; // 유저 이름
    private ClassType classType; // 수업 방식
    private String classTitle; // 강의 제목
    private String classLevel; //
    private String classArea;

    @Builder
    public ClassListResponse(User user, Classes classes) {
        this.userName = user.getUserName();
        this.classLevel = classes.getClassLevel();
        this.classTitle = classes.getClassTitle();
        this.classType = classes.getClassType();
        this.classArea = classes.getClassArea();
    }
}
