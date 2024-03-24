package hanium.cocam.classes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClassRequest {
    private String classArea;
    private String classDate;
    private String classIntro;
    private List<String> subjectCode;
    private Double classPay;
}
