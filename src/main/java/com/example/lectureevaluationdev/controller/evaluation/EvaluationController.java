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
import org.hibernate.annotations.Fetch;
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


    @GetMapping("/search/{pageNum}")
    public EvaluationResponse searchEvaluationBoards(@PathVariable("pageNum") int pageNum,
                                                     @RequestParam("lectureDivide") String lectureDivide,
                                                     @RequestParam("searchType") String searchType,
                                                     @RequestParam("search") String search) {
        return evaluationService.searchEvaluations(pageNum, lectureDivide, searchType, search);
    }

    @PatchMapping("/modify/{EvaluationID}")
    @ResponseBody
    public EvaluationResponse modifyEvaluation( @PathVariable("EvaluationID") long EvaluationID, @RequestBody EvaluationDTO evaluationDTO,
                                               HttpSession session) throws Exception {
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        System.out.println(loginUser);
        if (loginUser != null) {
            try {
                EvaluationResponse result = evaluationService.modifyEvaluation(EvaluationID, evaluationDTO);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                response.setResponseData("error", "evaluation modify Error");
            }
        } else {
            response.setResponseData("message", "notLoggedIn");
        }
        return response;
    }

    @DeleteMapping("/delete/{EvaluationID}")
    @ResponseBody
    public EvaluationResponse deleteEvaluation( @PathVariable("EvaluationID") long evaluationID, @RequestBody EvaluationDTO evaluationDTO,
                                                HttpSession session) throws Exception {
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        if (loginUser != null) {
            EvaluationResponse result = evaluationService.deleteEvaluation(evaluationID,evaluationDTO);
            return result;
        }
        else{response.setResponseData("message", "notLoggedIn");
            return response;
        }

    }

}
