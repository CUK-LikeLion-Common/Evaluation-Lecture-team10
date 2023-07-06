package com.example.lectureevaluationdev.repository.evaluation;

import com.example.lectureevaluationdev.entity.evaluation.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long>, PagingAndSortingRepository<Evaluation,Long> {
    //Optional<Evaluation> findByEvaluationID(long evaluationID);
    Evaluation findByEvaluationID(long evaluationID);

    //@Query("SELECT e FROM Evaluation e WHERE e.lectureName LIKE %:search% OR e.professorName LIKE %:search% OR e.evaluationTitle LIKE %:search% OR e.evaluationContent LIKE %:search%")
    //List<Evaluation> findBySearchLike(@Param("search") String search);
    //Page<Evaluation> findAll(Pageable pageable);
    //Page<Evaluation> findAllByEvaluationContentContainingIgnoreCase(String search, Pageable pageable);
    //Page<Evaluation> findAllByLectureDivideAndEvaluationContentContainingIgnoreCase(String lectureDivide, String search, Pageable pageable);
    //List<Evaluation> findAllByKeywordContainingIgnoreCase(String search, Pageable pageable);
    //List<Evaluation> findAllByEvaluationContentContainingIgnoreCase(String search);
    //List<Evaluation> findAllByLectureDivideAndEvaluationContentContainingIgnoreCase(String lectureDivide, String search,Pageable pageable);
    @Query("SELECT e FROM Evaluation e WHERE e.lectureName LIKE %:search% OR  e.professorName LIKE %:search% OR e.evaluationTitle LIKE %:search% AND (:lectureDivide = '전체' OR e.lectureDivide = :lectureDivide)")
    Page<Evaluation> searchByLectureDivideAndFields(String lectureDivide, String search, Pageable pageable);
}
