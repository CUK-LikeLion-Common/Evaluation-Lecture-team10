package com.example.lectureevaluationdev.service.user;

import com.example.lectureevaluationdev.dto.user.UserDTO;
import com.example.lectureevaluationdev.entity.user.UserEntity;
import com.example.lectureevaluationdev.mapper.user.UserMapper;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends ResponseService {

    private final UserRepository userRepository;

//    @Autowired
//   public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public EvaluationResponse  login(UserDTO userDTO) {
            try{
            Optional<UserEntity> userExist = userRepository.findByUserID(userDTO.getUserID());

            if(userExist.isPresent()) {
                //해당 이메일을 가진 회원 정보가 있다
                UserEntity userEntity = userExist.get();
                if (userEntity.getUserPassword().equals(userDTO.getUserPassword())) {
                    //비밀번호 일치 -> dto 리턴
                        UserDTO dto = UserMapper.INSTANCE.toDTO(userEntity);
                        return setResponse(200,"message",userDTO);

                }else{
                    return setResponse(400,"message","회원정보가 옳지 않습니다");
                }
            } else {
                return setResponse(404,"message","존재하지 않는 회원입니다");
            }
        }catch (Exception e){
                e.printStackTrace();
            }
        return null;
    }

    public EvaluationResponse logout(UserDTO userDTO) throws  Exception {
        Optional<UserEntity> userExist = userRepository.findByUserID(userDTO.getUserID());
        try {
            if (userExist.isPresent()) {
                UserEntity userEntity = userExist.get();

                userRepository.save(userEntity); // 변경된 상태를 저장
                return setResponse(200,"message","로그아웃 성공");
            } else {
                return setResponse(404, "message", "존재하지 않는 아이디입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return setResponse(400, "message", "로그아웃에 실패하셨습니다. 다시 시도해 주세요.");
        }
    }


    public EvaluationResponse signUp(UserEntity userInfo) {
        try {
            Optional<UserEntity> existUser = userRepository.findByUserID(userInfo.getUserID());
            if (existUser.isPresent()) {
                return setResponse(400, "message", "이미 존재하는 아이디입니다. 다른 아이디를 사용해 주세요.");
            }
            if (!isValidEmail(userInfo.getUserEmail())) {
                return setResponse(406, "message", "유효하지 않은 이메일 형식입니다.");
            }
            else {
                userRepository.save(userInfo);
                return setResponse(200, "message", "회원가입 완료");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public EvaluationResponse delete(UserDTO userDTO) throws Exception {
        Optional<UserEntity> deleteUser = userRepository.findByUserID(userDTO.getUserID());

        try {
            if (deleteUser.isPresent()) {
                userRepository.deleteUserByUserID(userDTO.getUserID());
                return setResponse(200, "message", "회원 탈퇴가 완료되었습니다.");
            } else {
                return setResponse(404, "message", "존재하지 않는 아이디입니다. 다시 입력해 주세요.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return setResponse(400, "message", "회원 탈퇴에 실패하셨습니다. 다시 시도해 주세요.");
        }
    }



    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }




}
