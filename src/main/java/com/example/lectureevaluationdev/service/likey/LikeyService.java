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

    public EvaluationResponse updateLike(long evaluationID, User userInfo) {
        Optional<User> usercheck = Optional.ofNullable(userRepository.findByUserID(userInfo.getUserID()));
        Optional<Evaluation> checkevaluation = Optional.ofNullable(evaluationRepository.findByEvaluationID(evaluationID));

        try{
            if (usercheck.isPresent() && checkevaluation.isPresent()) { //유저id & evalutation 게시물 존재하는지
                if (usercheck.get().getUserPassword().equals(userInfo.getUserPassword())) {
                    return toggleLike(evaluationID,userInfo.getUserID());
                }else{
                    return setResponse(600,"message","추천 실패 : 존재하지 않는 정보입니다.");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



        @Transactional
        public EvaluationResponse toggleLike(Long evaluationId,String userId) {
            // 이전에 좋아요를 누른 적이 있는지 확인
            Optional<Likey> existingLikey = likeyRepository.findFirstByuserIDAndEvaluationID(userId, evaluationId);
            Evaluation evaluation = evaluationRepository.findById(evaluationId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 평가가 없습니다."));
            try{
                if (existingLikey.isPresent()) {
                    // 좋아요를 이전에 누른 적이 있으면, 삭제
                    likeyRepository.delete(existingLikey.get());
                    // 좋아요 개수 -1 업데이트
                    evaluation.setLikeCount(evaluation.getLikeCount() - 1);
                    evaluationRepository.save(evaluation);
                    return setResponse(201, "success", "추천 취소 완료");

                } else {
                    // 좋아요를 이전에 누르지 않았다면, 추가
                    Likey likey = new Likey();
                    likey.setUserID(userId);
                    likey.setEvaluationID(evaluationId);
                    likeyRepository.save(likey);

                    // 좋아요 개수 +1 업데이트
                    evaluation.setLikeCount(evaluation.getLikeCount() + 1);
                    evaluationRepository.save(evaluation);
                    return setResponse(200,"success","추천 성공");

                }
            }catch(Exception e){
                e.printStackTrace();

            }
            return null;

        }


}
