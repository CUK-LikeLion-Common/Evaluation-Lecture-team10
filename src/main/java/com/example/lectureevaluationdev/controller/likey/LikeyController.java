package com.example.lectureevaluationdev.controller.likey;

import com.example.lectureevaluationdev.entity.user.User;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.repository.likey.LikeyRepository;
import com.example.lectureevaluationdev.service.likey.LikeyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController //@RestController 어노테이션은 사용된 클래스의 모든 메서드에 자동으로 JSON 변환을 적용
@RequestMapping("/likey")
public class LikeyController {
    //근데 굳이 클래스를 따로 팔 필요는 없을것같기도..
    private final LikeyService likeyService;
    private final LikeyRepository likeyRepository;

    @Autowired
    public LikeyController(LikeyService likeyService, LikeyRepository likeyRepository) {
        this.likeyService = likeyService;
        this.likeyRepository = likeyRepository;
    }

}
