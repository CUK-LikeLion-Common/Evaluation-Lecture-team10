package com.example.lectureevaluationdev.repository.likey;

import com.example.lectureevaluationdev.entity.likey.LikeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeyRepository extends JpaRepository<LikeyEntity,Long> {
    @Query("SELECT l FROM LikeyEntity l WHERE l.userID = :userID AND l.EvaluationID = :evaluationID")
    Optional<LikeyEntity> findFirstByuserIDAndEvaluationID(String userID, Long evaluationID);
}
