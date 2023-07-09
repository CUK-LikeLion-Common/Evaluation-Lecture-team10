package com.example.lectureevaluationdev.dto.evaluation;

import com.example.lectureevaluationdev.entity.likey.LikeyEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동으로 만들어줌
@AllArgsConstructor
@ToString //toString 메서드 자동으로 만들어줌
@Builder
public class EvaluationDTO {
    @JsonProperty("lectureScore")
    private String lecturescore;
    @JsonProperty("comfortableScore")
    private String comfortablescore;
    @JsonProperty("creditScore")
    private String creditscore;
    @JsonProperty("totalScore")
    private String totalscore;
    @JsonProperty("evaluationContent")
    private String evaluationcontent;
    @JsonProperty("evaluationTitle")
    private String evaluationtitle;
    @JsonProperty("lectureDivide")
    private String lecturedivide;
    @JsonProperty("semesterDivide")
    private String semesterdivide;
    @JsonProperty("lectureYear")
    private int lectureyear;
    @JsonProperty("professorName")
    private String professorname;
    @JsonProperty("lectureName")
    private String lecturename;
    @JsonProperty("userID")
    private String userid;
//    private List<LikeyEntity> likes;


}
