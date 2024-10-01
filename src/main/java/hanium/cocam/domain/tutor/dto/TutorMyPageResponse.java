package hanium.cocam.domain.tutor.dto;

import hanium.cocam.domain.user.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class TutorMyPageResponse {
    private TutorDetailResponse tutorDetailResponse;
    private List<MyTuteeListResponse> myTuteeList;
    private List<RequestedMentorshipListResponse> requestedList;
}
