package com.example.lectureevaluationdev.controller.user;

import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
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


}
