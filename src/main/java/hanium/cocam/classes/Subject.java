package hanium.cocam.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_SUBJECT")
@Builder
@Entity
public class Subject {
    @Id
    private String subjectCode; // 대분류, 소분류 나눠야할수도있음
    private String subjectName;
}
