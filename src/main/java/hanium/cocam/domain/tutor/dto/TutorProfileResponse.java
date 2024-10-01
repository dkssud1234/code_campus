package hanium.cocam.domain.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class TutorProfileResponse {
    private String tutorProfileImg;
    private String keyword;
    private String name;
    private String classArea;
    private String classType;
    private String school;
    private String tutorIntro;
    private String chatLink;
    private String portLink;
}
