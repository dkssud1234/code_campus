package hanium.cocam.classes.dto;

import hanium.cocam.classes.ClassType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSearchCond {
    private String userSex;
    private String tutorUniv;
    private String tutorMajor;
    private String subjectName;
    private String classArea;
    private String classDate;
    private String classTitle;
    private ClassType classType;
    private String classLevel;
}
