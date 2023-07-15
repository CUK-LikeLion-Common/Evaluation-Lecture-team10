package com.example.lectureevaluationdev.controller.user;

import com.example.lectureevaluationdev.dto.user.UserDTO;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.entity.user.UserEntity;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import com.example.lectureevaluationdev.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;
    private final ResponseService responseService = new ResponseService();


    @Autowired
    public UserController(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;

    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }



    @PostMapping("/login")
    @ResponseBody
    public EvaluationResponse login(@RequestBody UserDTO userDTO, HttpSession session) throws Exception{
//        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
        EvaluationResponse result = userService.login(userDTO);

        if(userDTO != null ){
            session.setAttribute("loginUser", userDTO);
            System.out.println("로그인 User >> " + session.getAttribute("loginUser"));
        }else{
            EvaluationResponse errorResponse = responseService.setResponse(403, "message", "로그인 실패");
            return errorResponse;
        }
        return result;

    }

    @GetMapping("/logout")
    public String logoutForm() {
        return "logout";
    }

    @PostMapping("/logout")
    @ResponseBody
    public EvaluationResponse logout(@RequestBody UserDTO userDTO,HttpSession session) throws Exception {
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();

        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        System.out.println("Login User: " + loginUser);
//      String requestUserID = userDTO.getUserID();

        if (loginUser != null) {
//                userDTO.setUserID( loginUser.getUserID());
            if (userDTO.getUserID().equals(loginUser.getUserID()) && userDTO.getUserEmail().equals(loginUser.getUserEmail())
                    && userDTO.getUserEmail().equals(loginUser.getUserEmail())) {
                EvaluationResponse result = userService.logout(userDTO);
                session.invalidate(); //정말 로그아웃 되었으면 session 에서 나가게 만들어야해얗
                return result;
            }else{
                response.setResponseData("message","회원정보가 옳지 않습니다");
                return response;
            }
        } else {
            response.setResponseData("message", "notLoggedIn");
            return response;
        }
    }


    @PostMapping("/join")
    @ResponseBody
    public EvaluationResponse join(@RequestBody UserDTO userDTO) throws Exception {
        UserEntity userInfo = UserEntity.builder()
                .userID(userDTO.getUserID())
                .userPassword(userDTO.getUserPassword())
                .userEmail(userDTO.getUserEmail())
                .build();

        EvaluationResponse result = userService.signUp(userInfo);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<EvaluationResponse> delete(@PathVariable("id") String userID, @RequestBody Map<String, String> user, HttpSession session) throws Exception {
        String password = user.get("userPassword").toString();

        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        System.out.println("Login User: " + loginUser);

        if (loginUser != null && loginUser.getUserID().equals(userID) && loginUser.getUserPassword().equals(password)) {
            EvaluationResponse response = this.userService.delete(loginUser);
            session.invalidate();
            return ResponseEntity.ok(response);
        } else {
            // 세션에 저장된 사용자와 삭제하려는 사용자가 다를 경우 에러 처리
            EvaluationResponse errorResponse = responseService.setResponse(403, "message", "사용자 정보가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
    }


    @GetMapping("/")
    public String index(){
        return "index";
    }
}
