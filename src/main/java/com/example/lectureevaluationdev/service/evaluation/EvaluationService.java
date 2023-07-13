package com.example.lectureevaluationdev.service.evaluation;

import com.example.lectureevaluationdev.dto.evaluation.EvaluationDTO;
import com.example.lectureevaluationdev.dto.user.UserDTO;
import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import com.example.lectureevaluationdev.entity.user.UserEntity;
import com.example.lectureevaluationdev.mapper.evaluation.EvaluationMapper;
import com.example.lectureevaluationdev.mapper.user.UserMapper;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import com.google.gson.JsonObject;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            EvaluationEntity evaluationEntity = EvaluationMapper.INSTANCE.toEntity(evaluationDTO);
            evaluationRepository.save(evaluationEntity);
            return setResponse(200, "message", "글 작성 성공");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("글 작성 오류 발생");
        }
        return null;
    }

    public EvaluationResponse searchEvaluations(int pageNum, String lectureDivide, String searchType, String search, UserDTO loginUser) {
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();

        try {
            int pageSize = 10; // 페이지당 게시물 수

            Sort sort = Sort.by(Sort.Direction.DESC, "createdAt"); // 정렬 기준 설정
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);

            Page<EvaluationEntity> evaluationPage;

            if (searchType.equals("최신순")) {
                if (StringUtils.isNotEmpty(search)) {
                    evaluationPage = evaluationRepository.findByLectureDivideAndSearchOrderByCreatedAtDesc(lectureDivide, search, pageable);
                } else {
                    response.setResponseData("message", "검색어를 입력해주세요.");
                    return response;
                }
            } else if (searchType.equals("추천순")) {
                if (StringUtils.isNotEmpty(search)) {
                    evaluationPage = evaluationRepository.findByLectureDivideAndSearchOrderByLikeCountDesc(lectureDivide, search, pageable);
                } else {
                    response.setResponseData("message", "검색어를 입력해주세요.");
                    return response;
                }
            } else {
                response.setResponseData("message", "올바르지 않은 검색 조건입니다.");
                return response;
            }


            List<EvaluationDTO> evaluationDTOList = new ArrayList<>();
            for (EvaluationEntity evaluationEntity : evaluationPage.getContent()) {
                EvaluationDTO evaluationDTO = EvaluationMapper.INSTANCE.toDTO(evaluationEntity);
                evaluationDTOList.add(evaluationDTO);
            }

            response.setResponseData("message", "검색 결과를 가져왔습니다.");
            response.setResponseData("evaluations", evaluationDTOList);
            response.setResponseData("totalPages", evaluationPage.getTotalPages());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponseData("message", "검색 도중 오류가 발생했습니다.");
        }

        return response;
    }


    public EvaluationResponse modifyEvaluation(long evaluationID,EvaluationDTO evaluationDTO){
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
        Optional<EvaluationEntity> evaluationentity =   evaluationRepository.findByEvaluationID(evaluationID);
        Optional<UserEntity> userEntityOptional = userRepository.findByUserID(evaluationDTO.getUserID());

        if (evaluationentity.isPresent() && userEntityOptional.isPresent()) {
            EvaluationEntity evaluationEntity1 = evaluationentity.get();
            UserEntity userEntity = userEntityOptional.get();

            // 사용자 정보 확인
            if (userEntity.getUserEmail().equals(evaluationDTO.getUserEmail()) && userEntity.getUserPassword().equals(evaluationDTO.getUserPassword())) {
                try {
                    EvaluationEntity evaluationEntity2 = EvaluationMapper.INSTANCE.toEntity(evaluationDTO);
                    System.out.println(evaluationEntity1);
                    System.out.println(evaluationEntity2);

                    if (evaluationEntity2.getLectureName() != null){
                        evaluationEntity1.setLectureName(evaluationEntity2.getLectureName());
                    }
                    if (evaluationEntity2.getProfessorName()  != null){
                        evaluationEntity1.setProfessorName(evaluationEntity2.getProfessorName() );
                    }
                    if (evaluationEntity2.getLectureYear() != 0){
                        evaluationEntity1.setLectureYear(evaluationEntity2.getLectureYear() );
                    }
                    if (evaluationEntity2.getSemesterDivide()!= null){
                        evaluationEntity1.setSemesterDivide(evaluationEntity2.getSemesterDivide() );
                    }
                    if (evaluationEntity2.getLectureDivide() != null){
                        evaluationEntity1.setLectureDivide(evaluationEntity2.getLectureDivide() );
                    }
                    if (evaluationEntity2.getEvaluationTitle() != null){
                        evaluationEntity1.setEvaluationTitle(evaluationEntity2.getEvaluationTitle() );
                    }
                    if (evaluationEntity2.getEvaluationContent() != null){
                        evaluationEntity1.setEvaluationContent(evaluationEntity2.getEvaluationContent() );
                    }
                    if (evaluationEntity2.getTotalScore() != null){
                        evaluationEntity1.setTotalScore(evaluationEntity2.getTotalScore());
                    }
                    if (evaluationEntity2.getCreditScore() != null){
                        evaluationEntity1.setCreditScore(evaluationEntity2.getCreditScore() );
                    }
                    if (evaluationEntity2.getComfortableScore() != null){
                        evaluationEntity1.setComfortableScore(evaluationEntity2.getComfortableScore());
                    }
                    if (evaluationEntity2.getLectureScore() != null ){
                        evaluationEntity1.setLectureScore(evaluationEntity2.getLectureScore());
                    }

                    System.out.println(evaluationEntity1);
                    evaluationRepository.save(evaluationEntity1);
                    response.setResponseData("success", "수정 완료");
                    return response;
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setResponseData( "error", "수정 실패");
                }
            } else {
                response.setResponseData( "message", "사용자 정보가 일치하지 않습니다.");
            }
        } else {
            response.setResponseData("message", "존재하지 않는 정보입니다.");
        }
        return response;
    }



   public EvaluationResponse deleteEvaluation(long evaluationID,EvaluationDTO evaluationDTO){
       EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
       Optional<EvaluationEntity> evaluationentity =   evaluationRepository.findByEvaluationID(evaluationID);
       Optional<UserEntity> userEntityOptional = userRepository.findByUserID(evaluationDTO.getUserID());

       if (evaluationentity.isPresent() && userEntityOptional.isPresent()) {
           UserEntity userEntity = userEntityOptional.get();

           // 사용자 정보 확인
           if (userEntity.getUserEmail().equals(evaluationDTO.getUserEmail()) && userEntity.getUserPassword().equals(evaluationDTO.getUserPassword())) {
               try {
                   evaluationRepository.deleteById(evaluationID);
                   return setResponse(200, "success", "삭제 성공");
               } catch (Exception e) {
                   e.printStackTrace();
                   response.setResponseData( "error", "삭제 실패");
               }
           } else {
               response.setResponseData( "message", "사용자 정보가 일치하지 않습니다.");
           }
       } else {
           response.setResponseData("message", "존재하지 않는 정보입니다.");
       }
       return response;

   }

}
