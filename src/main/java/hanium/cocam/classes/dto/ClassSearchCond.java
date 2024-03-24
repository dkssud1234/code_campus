package hanium.cocam.classes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSearchCond {
    private String userSex;
    private String mentorUniv;
    private String mentorMajor;
    private String subjectName;
    private String classArea;
    private String classDate;
}
