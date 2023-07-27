package com.example.lectureevaluationdev.entity.likey;

import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data //get,set 메소드 이용가능하게 하는 어노테이션
@Table(name = "likey")
public class LikeyEntity {

    @ManyToOne
    @JoinColumn(name="evaluation_ID", referencedColumnName="evaluation_ID", insertable = false, updatable = false)
    private EvaluationEntity evaluation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    int id;

    @Column(name="user_ID")
    String userID;

    @Column(name="evaluation_ID")
    long EvaluationID;

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
}
