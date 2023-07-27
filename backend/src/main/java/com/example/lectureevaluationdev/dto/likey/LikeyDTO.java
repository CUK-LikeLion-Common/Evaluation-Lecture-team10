package com.example.lectureevaluationdev.dto.likey;

import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import jakarta.persistence.Column;
import lombok.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동으로 만들어줌
@AllArgsConstructor
@ToString //toString 메서드 자동으로 만들어줌
@Builder
public class LikeyDTO {
    private EvaluationEntity evaluation;
    int id;
    String userID;
    long EvaluationID;
    Date createdAt;
    Date updatedAt;
}
