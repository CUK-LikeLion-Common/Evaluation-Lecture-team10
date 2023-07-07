package com.example.lectureevaluationdev.controller.evaluation;

import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.service.evaluation.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //@RestController 어노테이션은 사용된 클래스의 모든 메서드에 자동으로 JSON 변환을 적용
@RequestMapping("/evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationController(EvaluationService evaluationService, EvaluationRepository evaluationRepository) {
        this.evaluationService = evaluationService;
        this.evaluationRepository = evaluationRepository;
    }

}
