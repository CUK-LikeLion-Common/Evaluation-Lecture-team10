package com.example.lectureevaluationdev.service.user;

import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService extends ResponseService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}