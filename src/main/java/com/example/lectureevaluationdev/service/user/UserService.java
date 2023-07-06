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

    public EvaluationResponse login(User user) {
        User foundUser = userRepository.findByUserID(user.getUserID());
        int result = 0;
        boolean success = result == 1;

        try {
            if (user != null && foundUser.getUserPassword().equals(user.getUserPassword())) {
                result = 1; // 로그인 성공
                userRepository.setStatusTrue(user.getUserID());
                return setResponse(200, "message", "로그인 성공");
            } else if (foundUser == null) {
                result = -1; // ID 없음
                return setResponse(404, "message", "존재하지 않는 ID입니다.");

            }

            else {
                result = 0; //로그인 실패
                return setResponse(402, "message", "로그인 실패");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public EvaluationResponse signUpUser(User userInfo) {
        try {

            //존재하는지 확인
            Optional<User> userinfo = Optional.ofNullable(userRepository.findByUserID(userInfo.getUserID()));
            if(userinfo.isPresent()){
                return setResponse(400,"message","회원가입 실패. 중복회원입니다.");
            }

            else{
                //이메일 유효성 확인
                if(isValidEmail(userInfo.getUserEmail()) == false){
                    return setResponse(406,"message","유효하지 않은 이메일 형식입니다.");
                }
                
                userRepository.save(userInfo);//유저정보 저장 메소드 (insert문을 쓰지않아도..된다니!)
                return setResponse(200, "message", "회원가입 완료");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public EvaluationResponse logout(User getuser) {
        User foundUser = userRepository.findByUserID(getuser.getUserID());
        int result = 0;
        boolean success = result == 1;

        try {
            if (getuser != null && foundUser.getUserPassword().equals(getuser.getUserPassword())) {
                result = 1; //값이 존재
                userRepository.setStatusFalse(getuser.getUserID());
                return setResponse(200, "message", "로그아웃 성공");
            } else if (foundUser == null) {
                result = -1; // ID 없음
                return setResponse(404, "message", "존재하지 않는 ID입니다.");
            }

            else {
                result = 0; //로그아웃 실패
                return setResponse(402, "message", "로그아웃 실패");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public EvaluationResponse deleteUser(User getuser) {
        User foundUser = userRepository.findByUserID(getuser.getUserID());
        int result = 0;
        boolean success = result == 1;

        try {
            if (getuser != null && foundUser.getUserPassword().equals(getuser.getUserPassword())) {
                result = 1; //값이 존재
                userRepository.deleteUserById(getuser.getUserID());
                return setResponse(200, "message", "회원탈퇴 성공");
            } else if (foundUser == null) {
                result = -1; // ID 없음
                return setResponse(404, "message", "존재하지 않는 ID입니다.");
            }

            else {
                result = 0; //로그아웃 실패
                return setResponse(402, "message", "회원탈퇴 실패");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //참고
    private boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            err = true;
        }
        return err;
    }

}