package com.example.lectureevaluationdev.service.likey;

import com.example.lectureevaluationdev.dto.likey.LikeyDTO;
import com.example.lectureevaluationdev.dto.user.UserDTO;
import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import com.example.lectureevaluationdev.entity.likey.LikeyEntity;
import com.example.lectureevaluationdev.entity.user.UserEntity;
import com.example.lectureevaluationdev.mapper.likey.LikeyMapper;
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

    public EvaluationResponse updateLike(long evaluationID, LikeyDTO likeyDTO) {
        Optional<UserEntity> usercheck = userRepository.findByUserID(likeyDTO.getUserID());
        Optional<EvaluationEntity> checkevaluation = evaluationRepository.findByEvaluationID(evaluationID);
        try{
            if (usercheck.isPresent() && checkevaluation.isPresent()) { //유저id & evalutation 게시물 존재하는지
                return toggleLike(evaluationID,likeyDTO);
            }else{
                    return setResponse(600,"message","추천 실패 : 존재하지 않는 정보입니다.");
                }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Transactional
    public EvaluationResponse toggleLike(Long evaluationId,LikeyDTO likeyDTO) {
        // 이전에 좋아요를 누른 적이 있는지 확인
        String userId = likeyDTO.getUserID();
        Optional<LikeyEntity> existingLikey = likeyRepository.findFirstByuserIDAndEvaluationID(userId, evaluationId);
        EvaluationEntity evaluationEntity = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 평가가 없습니다."));
        try{
            if (existingLikey.isPresent()) {
                // 좋아요를 이전에 누른 적이 있으면, 삭제
                likeyRepository.delete(existingLikey.get());
                // 좋아요 개수 -1 업데이트
                evaluationEntity.setLikeCount(evaluationEntity.getLikeCount() - 1);
                evaluationRepository.save(evaluationEntity);
                return setResponse(201, "success", "추천 취소 완료");

            } else {
                // 좋아요를 이전에 누르지 않았다면, 추가
                LikeyEntity likeyEntity = LikeyMapper.INSTANCE.toEntity(likeyDTO);
                likeyEntity.setUserID(userId);
                likeyEntity.setEvaluationID(evaluationId);
                likeyRepository.save(likeyEntity);

                // 좋아요 개수 +1 업데이트
                evaluationEntity.setLikeCount(evaluationEntity.getLikeCount() + 1);
                evaluationRepository.save(evaluationEntity);
                return setResponse(200,"success","추천 성공");

            }
        }catch(Exception e){
            e.printStackTrace();

        }
        return null;

    }

}
