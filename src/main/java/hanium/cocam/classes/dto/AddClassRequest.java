package hanium.cocam.classes.dto;

import hanium.cocam.classes.Classes;
import hanium.cocam.classes.Subject;
import hanium.cocam.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddClassRequest {
    private String classArea;
    private String classDate;
    private String classIntro;
    private List<String> subjectCode;
    private Double classPay;

    public Classes toEntity(Subject subject, User user) {
        return Classes.builder()
                .classArea(classArea)
                .classDate(classDate)
                .classIntro(classIntro)
                .subjectCode(subject)
                .classPay(classPay)
                .userNo(user)
                .build();
    }
}
