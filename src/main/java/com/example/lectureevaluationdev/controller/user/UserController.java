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
    public String login(@ModelAttribute UserDTO userDTO,  HttpSession session) {

        UserDTO loginUser = userService.login(userDTO);

        if (loginUser != null) {
            //로그인 성공
            System.out.println(loginUser);

            if(loginUser.isStatus()) {
                System.out.println("이미 로그인했습니다");
                session.setAttribute("loginId", loginUser.getUserId());
                System.out.println(session.getAttribute("loginId"));
                return "alert";
            }else{
                System.out.println("로그인 성공");
                session.setAttribute("loginId", loginUser.getUserId());
                System.out.println(session.getAttribute("loginId"));
                return "main";
            }
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String userID = (String) session.getAttribute("loginId");
        if (userID != null) {
            UserDTO memberDTO = new UserDTO();
            memberDTO.setUserId(userID);
            userService.logout(memberDTO);
            session.invalidate();
        }
        return "index";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
