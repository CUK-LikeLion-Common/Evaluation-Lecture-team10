package com.example.lectureevaluationdev.service;

import com.example.lectureevaluationdev.entity.User;
import com.example.lectureevaluationdev.repository.UserRepository;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.primary.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService extends ResponseService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public EvaluationResponse signUp(User userInfo) {
        try {
            Optional<User> existUser = Optional.ofNullable(userRepository.findByUserId(userInfo.getUserId()));
            if (existUser.isPresent()) {
                return setResponse(400, "message", "이미 존재하는 아이디입니다. 다른 아이디를 사용해 주세요.");
            }

            existUser = Optional.ofNullable(userRepository.findByUserIdAndUserPassword(
                    userInfo.getUserId(), userInfo.getUserPassword()));
            if (existUser.isPresent()) {
                return setResponse(400, "message", "이미 존재하는 회원 정보입니다.");
            }
            if (!isValidEmail(userInfo.getUserEmail())) {
                return setResponse(406, "message", "유효하지 않은 이메일 형식입니다.");
            }
            userRepository.save(userInfo);
            return setResponse(200, "message", "회원가입 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public EvaluationResponse delete(User user) {
        User deleteUser = userRepository.findByUserId(user.getUserId());

        try {
            if (user != null && deleteUser.getUserPassword().equals(user.getUserPassword())) {
                userRepository.deleteUserByUserId(user.getUserId());
                return setResponse(200, "message", "회원 탈퇴가 완료되었습니다.");
            } else if (deleteUser == null) {
                // 404 (Not Found) : 서버가 요청받은 리소스를 찾지 못함
                return setResponse(404, "message", "존재하지 않는 아이디입니다. 다시 입력해 주세요.");
            } else {
                return setResponse(400, "message", "회원 탈퇴에 실패하셨습니다. 다시 시도해 주세요.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
}
