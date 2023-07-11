package com.example.lectureevaluationdev.service.evaluation;

import com.example.lectureevaluationdev.dto.evaluation.EvaluationDTO;
import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import com.example.lectureevaluationdev.mapper.evaluation.EvaluationMapper;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public EvaluationResponse searchEvaluations(String lectureDivide, int pageNum, String searchType, String search) {
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

    public EvaluationResponse modifyEvaluation(EvaluationDTO evaluationDTO){

        try{
            EvaluationEntity evaluationEntity = EvaluationMapper.INSTANCE.toEntity(evaluationDTO);
            evaluationRepository.save(evaluationEntity);
            return setResponse(200, "message", "글 작성 성공");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("글 작성 오류 발생");
        }
        return null;
    }
}
