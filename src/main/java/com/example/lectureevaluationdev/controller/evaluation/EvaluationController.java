package com.example.lectureevaluationdev.controller.evaluation;

import com.example.lectureevaluationdev.dto.evaluation.EvaluationDTO;
import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import com.example.lectureevaluationdev.mapper.evaluation.EvaluationMapper;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.service.evaluation.EvaluationService;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RequiredArgsConstructor //Lombok으로 스프링에서 DI(의존성 주입)의 방법 중에 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
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
    public EvaluationResponse writeEvaluationBoard(HttpServletRequest request, @RequestBody EvaluationDTO evaluationDTO) throws Exception {
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
        HttpSession sessions = request.getSession(); // 기존 세션 가져오기

        if (sessions == null || sessions.getAttribute("loginID") == null) {
            System.out.println(sessions.getAttribute("loginID")+"세션id");
            // 로그인되지 않은 경우에 대한 처리
            response.setResponseData("message", "notLoggedIn");
            return response;
        }
        EvaluationResponse result = evaluationService.writeEvaluation(evaluationDTO);
        return result;
    }

    @GetMapping("/search/{pageNum}")
    public EvaluationResponse searchEvaluationBoards(@PathVariable("pageNum") int pageNum,
                                                     @RequestParam("lectureDivide") String lectureDivide,
                                                     @RequestParam("searchType") String searchType,
                                                     @RequestParam("search") String search) {
        return evaluationService.searchEvaluations(pageNum, lectureDivide, searchType, search);
    }
}
