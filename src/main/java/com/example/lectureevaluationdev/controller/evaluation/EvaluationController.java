package com.example.lectureevaluationdev.controller.evaluation;

import com.example.lectureevaluationdev.dto.evaluation.EvaluationDTO;
import com.example.lectureevaluationdev.dto.user.UserDTO;
import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import com.example.lectureevaluationdev.mapper.evaluation.EvaluationMapper;
import com.example.lectureevaluationdev.repository.evaluation.EvaluationRepository;
import com.example.lectureevaluationdev.service.evaluation.EvaluationService;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequiredArgsConstructor //Lombok으로 스프링에서 DI(의존성 주입)의 방법 중에 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
@RestController //@RestController 어노테이션은 사용된 클래스의 모든 메서드에 자동으로 JSON 변환을 적용
@RequestMapping("/evaluation")
public class EvaluationController {
    public static final int SHOW_COUNT = 10;
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
    public EvaluationResponse writeEvaluationBoard(HttpServletRequest request,HttpSession session, @RequestBody EvaluationDTO evaluationDTO) throws Exception {
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
        UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인되지 않은 경우에 대한 처리
            response.setResponseData("message", "notLoggedIn");
            return response;
        }
        EvaluationResponse result = evaluationService.writeEvaluation(evaluationDTO);
        return result;
    }

    //강의평가 조회
    @GetMapping("/read")
    @ResponseBody
    public ResponseEntity<List<EvaluationDTO>> getAllBoards(HttpServletRequest request, @PageableDefault(size =10) Pageable pageable, @RequestParam(required = false) String sortingTag) throws Exception{
        /*
        int limit = getLimitCnt(pageNum);
        int offset = limit - SHOW_COUNT;
        */

        return evaluationService.getAllBoards(pageable, sortingTag);
    }

    private int getLimitCnt(int pageNum) {
        int limit = SHOW_COUNT;
        for(int i = 0; i <= pageNum; i++) {
            if(i != 0)
                limit += SHOW_COUNT;
        }

        return limit;
    }


    @GetMapping("/search/{pageNum}")
    public EvaluationResponse searchEvaluationBoards(@PathVariable("pageNum") int pageNum,
                                                     @RequestParam("lectureDivide") String lectureDivide,
                                                     @RequestParam("searchType") String searchType,
                                                     @RequestParam("search") String search,
                                                     HttpSession session) {
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        if (loginUser != null) {
            return evaluationService.searchEvaluations(pageNum, lectureDivide, searchType, search, loginUser);
        } else {
            EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
            response.setResponseData("message", "사용자가 로그인되어 있지 않습니다.");
            return response;
        }
    }


}
