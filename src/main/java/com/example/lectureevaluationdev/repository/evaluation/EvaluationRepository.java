package com.example.lectureevaluationdev.repository.evaluation;

import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long>, PagingAndSortingRepository<EvaluationEntity,Long> {
}
