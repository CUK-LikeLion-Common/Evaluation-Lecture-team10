package com.example.lectureevaluationdev.controller.user;

import com.example.lectureevaluationdev.dto.user.UserDTO;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.entity.user.UserEntity;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import com.example.lectureevaluationdev.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public EvaluationResponse login(@RequestBody UserDTO userDTO,  HttpSession session) {
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
                System.out.println(session.getAttribute("loginID"));

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
    public EvaluationResponse logout(@RequestBody UserDTO userDTO) {
        EvaluationResponse.ResponseMap response = new EvaluationResponse.ResponseMap();

        String requestUserID = userDTO.getUserID();

            if (userRepository.findByUserID(requestUserID).isPresent()) {
                UserDTO memberDTO = new UserDTO();
                memberDTO.setUserID(requestUserID);
                userService.logout(memberDTO);
                response.setResponseData("message", "loginOut");
                return response;
            }

            response.setResponseData("message", "notLoggedIn");
        return response;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
