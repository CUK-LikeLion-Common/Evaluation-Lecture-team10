package com.example.lectureevaluationdev.service.likey;

import com.example.lectureevaluationdev.entity.evaluation.Evaluation;
import com.example.lectureevaluationdev.entity.likey.Likey;
import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.repository.likey.LikeyRepository;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LikeyService extends ResponseService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final LikeyRepository likeyRepository;

    public LikeyService(EvaluationRepository evaluationRepository, UserRepository userRepository, LikeyRepository likeyRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.likeyRepository = likeyRepository;
    }

}
