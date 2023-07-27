package com.example.lectureevaluationdev.dto.evaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동으로 만들어줌
@AllArgsConstructor
@ToString //toString 메서드 자동으로 만들어줌
@Builder
public class EvaluationDTO {

    @JsonProperty("lectureScore")
    private String lectureScore;

    @JsonProperty("comfortableScore")
    private String comfortableScore;

    @JsonProperty("creditScore")
    private String creditScore;

    @JsonProperty("totalScore")
    private String totalScore;

    @JsonProperty("evaluationContent")
    private String evaluationContent;

    @JsonProperty("evaluationTitle")
    private String evaluationTitle;

    @JsonProperty("lectureDivide")
    private String lectureDivide;

    @JsonProperty("semesterDivide")
    private String semesterDivide;

    @JsonProperty("lectureYear")
    private int lectureYear;

    @JsonProperty("professorName")
    private String professorName;

    @JsonProperty("lectureName")
    private String lectureName;

    @JsonProperty("userID")
    private String userID;

    @JsonProperty("created_at")
    Date createdAt;

    long evaluationID;

    @JsonProperty("userPassword")
    private String userPassword;

//    private List<LikeyEntity> likes;


}
