package com.example.lectureevaluationdev.service.likey;

import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.repository.likey.LikeyRepository;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import org.springframework.stereotype.Service;

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
