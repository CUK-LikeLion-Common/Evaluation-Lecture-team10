package com.example.lectureevaluationdev.repository.evaluation;

import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long>, PagingAndSortingRepository<EvaluationEntity,Long>, CrudRepository<EvaluationEntity,Long> {
    Optional<EvaluationEntity> findByEvaluationID(long evaluationID);
    List<EvaluationEntity> findAll(Sort sort);
    Page<EvaluationEntity> findAll(Pageable pageable);


    @Query("SELECT e FROM EvaluationEntity e WHERE (:lectureDivide = '전체' OR e.lectureDivide = :lectureDivide) " +
            "AND (LOWER(e.lectureName) LIKE %:search% OR LOWER(e.professorName) LIKE %:search% OR LOWER(e.semesterDivide) LIKE %:search% " +
            "OR LOWER(e.evaluationTitle) LIKE %:search% OR LOWER(e.evaluationContent) LIKE %:search%) " +
            "ORDER BY e.createdAt DESC")
    Page<EvaluationEntity> findByLectureDivideAndSearchOrderByCreatedAtDesc(
            @Param("lectureDivide") String lectureDivide,
            @Param("search") String search,
            Pageable pageable
    );

    @Query("SELECT e FROM EvaluationEntity e WHERE (:lectureDivide = '전체' OR e.lectureDivide = :lectureDivide) " +
            "AND (LOWER(e.lectureName) LIKE %:search% OR LOWER(e.professorName) LIKE %:search% OR LOWER(e.semesterDivide) LIKE %:search% " +
            "OR LOWER(e.evaluationTitle) LIKE %:search% OR LOWER(e.evaluationContent) LIKE %:search%) " +
            "ORDER BY e.likeCount DESC")
    Page<EvaluationEntity> findByLectureDivideAndSearchOrderByLikeCountDesc(
            @Param("lectureDivide") String lectureDivide,
            @Param("search") String search,
            Pageable pageable
    );


}
