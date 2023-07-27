package com.example.lectureevaluationdev.controller;

import com.example.lectureevaluationdev.entity.User;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    @ResponseBody
    public EvaluationResponse join(@RequestBody Map<String,Object> user) throws Exception {
        User userInfo = User.builder()
                .userId(user.get("user_ID").toString())
                .userPassword(user.get("user_password").toString())
                .userEmail(user.get("user_email").toString())
                .status(true)
                .build();

        EvaluationResponse result = userService.signUp(userInfo);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public EvaluationResponse delete(@RequestBody Map<String,String> user) throws Exception {
        User getuser = User.builder()
                .userId(user.get("user_ID").toString())
                .userPassword(user.get("user_password").toString())
                .userEmail(user.get("user_email").toString())
                .status(false)
                .build();

        return this.userService.delete(getuser);
    }
}
