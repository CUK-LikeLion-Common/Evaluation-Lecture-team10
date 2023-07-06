package com.example.lectureevaluationdev.repository.likey;

import com.example.lectureevaluationdev.entity.likey.Likey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeyRepository extends JpaRepository<Likey,Long> {
    @Query("SELECT l FROM Likey l WHERE l.userID = :userID AND l.EvaluationID = :evaluationID")
    Optional<Likey> findFirstByuserIDAndEvaluationID(String userID, Long evaluationID);
}
