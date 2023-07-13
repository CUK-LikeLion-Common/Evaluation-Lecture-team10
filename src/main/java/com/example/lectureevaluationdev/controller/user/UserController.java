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

    /*@PostMapping("/login")
    @ResponseBody
    public EvaluationResponse login(@RequestBody UserDTO userDTO, HttpSession session) {
       EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
        UserDTO loginUser = userService.login(userDTO);

        if (loginUser != null) {
            //로그인 성공
            System.out.println(loginUser);
            if(loginUser.isStatus()) {
                System.out.println("이미 로그인했습니다");
                session.setAttribute("loginID", loginUser.getUserID());
                System.out.println(session.getAttribute("loginID"));
                response.setResponseData("message", "alreadyLoggedIn");
                return response;
            }else{
                System.out.println("로그인 성공");
                session.setAttribute("loginID", loginUser.getUserID());
                response.setResponseData("message", "LoginSuccess");
                return response;
            }
        } else {
            response.setResponseData("message", "loginFail");
            return response;
        }
    }*/

    @PostMapping("/login")
    @ResponseBody
    public EvaluationResponse login(@RequestBody UserDTO userDTO, HttpSession session) {
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();
        UserDTO loginUser = userService.login(userDTO);

        if (loginUser != null) {
            // 로그인 성공
//            System.out.println(loginUser);

            if (loginUser.isStatus()) {
                session.setAttribute("loginUser", loginUser);
                System.out.println(session.getAttribute("loginUser")+"이미 로그인");
                response.setResponseData("message", "alreadyLoggedIn");
                return response;
            } else {
                session.setAttribute("loginUser", loginUser);
                System.out.println(session.getAttribute("loginUser")+"로그인 성공");
                response.setResponseData("message", "LoginSuccess");
                return response;
            }
        } else {
            response.setResponseData("message", "loginFail");
            return response;
        }
    }

    @GetMapping("/logout")
    public String logoutForm() {
        return "logout";
    }

    @PostMapping("/logout")
    @ResponseBody
    public EvaluationResponse logout(@RequestBody UserDTO userDTO,HttpSession session) throws Exception {
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();

        UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
        System.out.println("Login User: " + loginUser);
//      String requestUserID = userDTO.getUserID();

            if ( loginUser != null) {
                userDTO.setUserID( loginUser.getUserID());
                userService.logout(userDTO);
                session.invalidate();
                response.setResponseData("message", "logOut");
                return response;
            }

                response.setResponseData("message", "notLoggedIn");

        return response;

    }

    @PostMapping("/join")
    @ResponseBody
    public EvaluationResponse join(@RequestBody UserDTO userDTO) throws Exception {
        UserEntity userInfo = UserEntity.builder()
                .userID(userDTO.getUserID())
                .userPassword(userDTO.getUserPassword())
                .userEmail(userDTO.getUserEmail())
                .status(userDTO.isStatus())
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
