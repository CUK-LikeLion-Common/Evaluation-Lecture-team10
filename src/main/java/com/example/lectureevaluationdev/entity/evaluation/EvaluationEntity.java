package com.example.lectureevaluationdev.entity.evaluation;

import com.example.lectureevaluationdev.entity.likey.LikeyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data //get,set 메소드 이용가능하게 하는 어노테이션
@Table(name = "evaluation")
public class EvaluationEntity {
    @OneToMany(mappedBy = "evaluation")
    private List<LikeyEntity> likes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//는 JPA에서 기본 키를 자동으로 생성할 때 사용하는 방법 중 하나
    @Column(name="evaluation_ID")
    long evaluationID;

    @Column(name="user_ID")
    String userID;

    @Column(name="lecture_name")
    String lectureName;
    @Column(name="professor_name")
    String professorName;
    @Column(name="lecture_year")
    int lectureYear;

    @Column(name="semester_divide")
    String semesterDivide;

    @Column(name="lecture_divide")
    String lectureDivide;

    @Column(name="evaluation_title")
    String evaluationTitle;

    @Column(name="evaluation_content")
    String evaluationContent;

    @Column(name="total_score")
    String totalScore;

    @Column(name="credit_score")
    String creditScore;

    @Column(name="comfortable_score")
    String comfortableScore;

    @Column(name="lecture_score")
    String lectureScore;

    @Column(name="like_count")
    int likeCount;


    @Column(name="created_at", nullable = false)
    Date createdAt;

    @Column(name="updated_at", nullable = false)
    Date updatedAt;


    @PrePersist
    public void prePersist() {
        // 기본값을 현재 시간으로 설정
        final Date currentDate = new Date();
        createdAt = currentDate;
        updatedAt = currentDate;
    }

    public EvaluationEntity(String userID, String lectureName, String professorName, int lectureYear, String semesterDivide, String lectureDivide, String evaluationTitle, String evaluationContent, String totalScore, String creditScore, String comfortableScore, String lectureScore) {
        this.userID = userID;
        this.lectureName = lectureName;
        this.professorName = professorName;
        this.lectureYear = lectureYear;
        this.semesterDivide = semesterDivide;
        this.lectureDivide = lectureDivide;
        this.evaluationTitle = evaluationTitle;
        this.evaluationContent = evaluationContent;
        this.totalScore = totalScore;
        this.creditScore = creditScore;
        this.comfortableScore = comfortableScore;
        this.lectureScore = lectureScore;
    }


}
