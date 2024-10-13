package hanium.cocam.domain.tutee.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class TuteeMyPageResponse {
    private TuteeDetailResponse tuteeDetailResponse;
    private List<RequestedMentorshipListResponse> requestedMentorshipList;
    private List<MyTutorListResponse> ongoingMentorshipList;
}
