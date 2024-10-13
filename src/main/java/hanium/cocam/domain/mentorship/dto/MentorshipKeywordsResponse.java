package hanium.cocam.domain.mentorship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MentorshipKeywordsResponse {
    private List<String> tutorKeywords;
    private List<String> tuteeKeywords;
    private String tutorLevel;
    private String tuteeLevel;
}
