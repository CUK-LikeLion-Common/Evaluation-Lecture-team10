package com.example.lectureevaluationdev.service.evaluation;

import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.entity.evaluation.Evaluation;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EvaluationService extends ResponseService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository, UserRepository userRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
    }


    public EvaluationResponse writeEvaluation(Evaluation evaluationcontent)  {
        //이미 존재하는지 확인을..할수가없네 ? 테이블 구조상..평가 과목별로 id 지정하거나 나눈게 아니라서...
        //또는 나의 작성 게시글 확인같은걸 할수가 없음
        try {
            evaluationRepository.save(evaluationcontent);
            return setResponse(200, "message", "글 작성 성공");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("글 작성 오류 발생");
        }
        return null;

    }

    public EvaluationResponse modifyEvaluation(User userInfo, long evaluationID, Evaluation evaluationcontent) {
        Optional<User> usercheck = Optional.ofNullable(userRepository.findByUserID(userInfo.getUserID()));
        Optional<Evaluation> content = Optional.ofNullable(evaluationRepository.findByEvaluationID(evaluationID));

        try {
            //입력받은 user값과 db의 값이 일치할경우에만
            if (usercheck.isPresent() && content.isPresent()) {
                if (usercheck.get().getUserPassword() == userInfo.getUserPassword()) {
                    Evaluation evaluations = content.get();

                    if (evaluationcontent.getLectureName() != null) {
                        evaluations.setLectureName(evaluationcontent.getLectureName());
                    }
                    if (evaluationcontent.getProfessorName() != null) {
                        evaluations.setProfessorName(evaluationcontent.getProfessorName());
                    }
                    if (evaluationcontent.getSemesterDivide() != null) {
                        evaluations.setSemesterDivide(evaluationcontent.getSemesterDivide());
                    }
                    if (evaluationcontent.getLectureDivide() != null) {
                        evaluations.setLectureDivide(evaluationcontent.getLectureDivide());
                    }
                    if (evaluationcontent.getEvaluationTitle() != null) {
                        evaluations.setEvaluationTitle(evaluationcontent.getEvaluationTitle());
                    }
                    if (evaluationcontent.getTotalScore() != null) {
                        evaluations.setTotalScore(evaluationcontent.getTotalScore());
                    }
                    if (evaluationcontent.getCreditScore() != null) {
                        evaluations.setCreditScore(evaluationcontent.getCreditScore());
                    }
                    if (evaluationcontent.getComfortableScore() != null) {
                        evaluations.setComfortableScore(evaluationcontent.getComfortableScore());
                    }
                    if (evaluationcontent.getLectureScore() != null) {
                        evaluations.setLectureScore(evaluationcontent.getLectureScore());
                    }
                    //더 간단하게 작성하는 방법을 모르겠다..
                    evaluationRepository.save(evaluations);
                }
                return setResponse(200, "message", "수정이 완료되었습니다.");

            } else {
                return setResponse(500, "message", "수정이 불가합니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //삭제
    //@Transactional -> 복수의 데이터 삭제할때

    public EvaluationResponse deleteEvaluation(Long evaluationID, User userInfo) {
        Optional<User> usercheck = Optional.ofNullable(userRepository.findByUserID(userInfo.getUserID()));
        Optional<Evaluation> checkevaluation = Optional.ofNullable(evaluationRepository.findByEvaluationID(evaluationID));

        try {
            //입력받은 user값과 db의 값이 일치할경우에만
            if (usercheck.isPresent() && checkevaluation.isPresent()) {
                if (usercheck.get().getUserPassword().equals(userInfo.getUserPassword())) {
                    evaluationRepository.deleteById(evaluationID);
                    return setResponse(200, "message", "삭제가 완료되었습니다.");
                } else {
                    return setResponse(501, "message", "삭제 실패하였습니다.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //검색like
    private String setting(String keyword) {
        return keyword == null ? null : "%" + keyword + "%";
    }
    //검색
    public EvaluationResponse searchBoard(int pageNum, String lectureDivide, String searchType, String search) {


        Sort sort;
        if(searchType.equals("최신순")){
            sort = Sort.by(Sort.Direction.DESC,"createdAt");
            System.out.println("sort"+sort);
        }else if(searchType.equals("추천순")){
            sort = Sort.by(Sort.Direction.DESC,"likeCount");
        }else{
            sort = Sort.unsorted();
        }
        // 정렬 정보를 포함한 새로운 PageRequest를 생성합니다.
        Pageable pageable = PageRequest.of(pageNum, 10,sort);

        try{
            //search : 검색하고자 하는 단어
            // 전체, 전공, 교양, 기타
            Page<Evaluation> searchResults;
            List<Evaluation> searchresult;
            searchresult = evaluationRepository.searchByLectureDivideAndFields(lectureDivide,search,pageable).getContent();


            /*
            if(lectureDivide.equals("전체")){
                searchresult = evaluationRepository.findAllByKeywordContainingIgnoreCase(search,pageable);
            }else{
                searchresult = evaluationRepository.searchByLectureDivideAndFields(lectureDivide,search,pageable);
            }
*/


            // 수정된 PageRequest를 사용하여 다시 데이터베이스에서 조회합니다.
            //searchResults = evaluationRepository.findAll(pageable);
            // 검색 결과가 비어있는 경우 따로 처리
            if (searchresult.isEmpty()) {
                return setResponse(200, "no_results", new ArrayList<>());
            }
            else{
                // 검색 결과를 리스트로 반환합니다.
                return setResponse(200,"success",searchresult);
            }
            //페이징 num 에 맞게 검색 + 최신순, 추천순정렬값 searchtype sort
            //EvaluationResponse result = setResponse(200,"success",evaluationOptional);


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
