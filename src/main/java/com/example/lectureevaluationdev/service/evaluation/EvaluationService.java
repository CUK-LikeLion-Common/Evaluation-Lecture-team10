package com.example.lectureevaluationdev.service.evaluation;

import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService extends ResponseService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository, UserRepository userRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
    }


}
