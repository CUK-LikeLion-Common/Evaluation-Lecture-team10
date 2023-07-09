package com.example.lectureevaluationdev.service.evaluation;

import com.example.lectureevaluationdev.dto.evaluation.EvaluationDTO;
import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import com.example.lectureevaluationdev.mapper.evaluation.EvaluationMapper;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
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


    public EvaluationResponse writeEvaluation(EvaluationDTO evaluationDTO) {
        //테이블 구조상..평가 과목별로 id 지정하거나 나눈게 아니라서...
        //나의 작성 게시글 확인같은걸 할수가 없음

        try{
            EvaluationEntity evaluationEntity = EvaluationMapper.evaluationMapper.toEntity(evaluationDTO);
            evaluationRepository.save(evaluationEntity);
            return setResponse(200, "message", "글 작성 성공");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("글 작성 오류 발생");
        }
        return null;


    }
}
