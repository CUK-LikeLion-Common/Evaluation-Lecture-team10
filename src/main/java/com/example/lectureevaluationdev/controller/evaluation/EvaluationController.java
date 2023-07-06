package com.example.lectureevaluationdev.controller.evaluation;

import com.example.lectureevaluationdev.entity.evaluation.Evaluation;
import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.service.evaluation.EvaluationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    //강의평가 쓰기
    @PostMapping("/write")
    @ResponseBody
    public EvaluationResponse writeEvaluationBoard(HttpServletRequest request, @RequestBody Map<String,Object> content) throws Exception {
        Evaluation evaluationcontent = Evaluation.builder()
                .userID(content.get("userID").toString())
                .lectureName(content.get("lectureName").toString())
                .professorName(content.get("professorName").toString())
                .lectureYear((Integer) content.get("lectureYear"))
                .semesterDivide(content.get("semesterDivide").toString())
                .lectureDivide(content.get("lectureDivide").toString())
                .evaluationTitle(content.get("evaluationTitle").toString())
                .evaluationContent(content.get("evaluationContent").toString())
                .totalScore(content.get("totalScore").toString())
                .creditScore(content.get("creditScore").toString())
                .comfortableScore(content.get("comfortableScore").toString())
                .lectureScore(content.get("lectureScore").toString())
                .build();

        EvaluationResponse result = evaluationService.writeEvaluation(evaluationcontent);
        return result;


    }
    //글 수정

    @PatchMapping("/modify/{evaluationID}")
    @ResponseBody
    public EvaluationResponse modifyEvaluationBoard(HttpServletRequest request,@PathVariable("evaluationID") long evaluationID,@RequestBody Map<String,Object> content ) throws Exception{
        User userInfo = User.builder()
                .userID(content.get("userID").toString())
                .userPassword(content.get("userPassword").toString())
                .userEmail(content.get("userEmail").toString())
                .build();

        Evaluation existingEvaluation = evaluationRepository.findByEvaluationID(evaluationID); // Retrieve the existing entity
        Evaluation evaluationcontent = Evaluation.builder()
                .userID(content.get("userID").toString())
                .lectureName(content.get("lectureName")!= null ? content.get("lectureName").toString() : null)
                .professorName(content.get("professorName")!= null ? content.get("professorName").toString() : null)
                .lectureYear(content.get("lectureYear") != null ? Integer.parseInt((String) content.get("lectureYear")) : null)
                .semesterDivide(content.get("semesterDivide")!= null ? content.get("semesterDivide").toString() : null)
                .lectureDivide(content.get("lectureDivide")!= null ? content.get("lectureDivide").toString() : null)
                .evaluationTitle(content.get("evaluationTitle")!= null ? content.get("evaluationTitle").toString() : null)
                .evaluationContent(content.get("evaluationContent")!= null ? content.get("evaluationContent").toString() : null)
                .totalScore(content.get("totalScore")!= null ? content.get("totalScore").toString() : null)
                .creditScore(content.get("creditScore")!= null ? content.get("creditScore").toString() : null)
                .comfortableScore(content.get("comfortableScore")!= null ? content.get("comfortableScore").toString() : null)
                .lectureScore(content.get("lectureScore")!= null ? content.get("lectureScore").toString() : null)
                .build();

        BeanUtils.copyProperties(existingEvaluation, evaluationcontent);
        EvaluationResponse result = evaluationService.modifyEvaluation(userInfo,evaluationID,evaluationcontent);
        return result;
    }


    //글 삭제
    //권한확인이 따로 불가함
    //안넣었으니까.. user role 을..
    @DeleteMapping("/delete/{evaluationID}")
    @ResponseBody
    public EvaluationResponse deleteEvaluationBoard(HttpServletRequest request,@PathVariable("evaluationID") Long evaluationID,@RequestBody Map<String,Object> user ) throws Exception{
        User userInfo = User.builder()
                .userID(user.get("userID").toString())
                .userPassword(user.get("userPassword").toString())
                .userEmail(user.get("userEmail").toString())
                .build();
        EvaluationResponse result = evaluationService.deleteEvaluation(evaluationID,userInfo);
        return result;
    }

    @GetMapping("/search/{page}")
    @ResponseBody
    public EvaluationResponse getSearch(HttpServletRequest request,@PathVariable("page") int pageNum, @RequestParam("lectureDivide") String lectureDivide, @RequestParam("searchType") String searchType, @RequestParam("search") String search) throws Exception{

        if(search.isEmpty()){
            return null;
        }else{
            return evaluationService.searchBoard(pageNum,lectureDivide,searchType,search);
        }

    }

}
