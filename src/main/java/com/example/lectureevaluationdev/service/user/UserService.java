package com.example.lectureevaluationdev.service.user;

import com.example.lectureevaluationdev.dto.user.UserDTO;
import com.example.lectureevaluationdev.entity.user.UserEntity;
import com.example.lectureevaluationdev.mapper.user.UserMapper;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.example.lectureevaluationdev.primary.ResponseService;
import com.example.lectureevaluationdev.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends ResponseService {

    private final UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public UserDTO login(UserDTO userDTO) {
        /*
        1.회원이 입력한 아이디로 DB에서 조회
        2.DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호 맞는지 확인
         */
        Optional<UserEntity> byUserId = userRepository.findByUserID(userDTO.getUserID());

        if (byUserId.isPresent()) {
            //해당 이메일을 가진 회원 정보가 있다
            UserEntity userEntity = byUserId.get();
            if (userEntity.getUserPassword().equals(userDTO.getUserPassword()) ){
                //비밀번호 일치 -> dto 리턴
                if(userEntity.getStatus()){
                    //만약에 Enti.y status 가 1이면 -> 이미 로그인된 상태
                   userDTO.setStatus(true);
                    return userDTO;
                }else { //로그인 안 되어있던 상태
//                    UserDTO.setMemberStatus(true);
                    userRepository.setStatusTrue(userEntity.getUserID());
                    UserDTO dto = UserMapper.INSTANCE.toDTO(userEntity);
//                    UserDTO dto = UserDTO.toUserDTO(userEntity);
                    return dto;
                }
            }
            //비밀번호 불일치
            return null;
        } else {
            //조회 결과 없음
            return null;
        }
    }

    public void logout(UserDTO memberDTO){
        Optional<UserEntity> byMemberId = userRepository.findByUserID(memberDTO.getUserID());
        if (byMemberId.isPresent()){
           UserEntity userEntity = byMemberId.get();
            userRepository.setStatusFalse(userEntity.getUserID());
            userRepository.save(userEntity); // 변경된 상태를 저장
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
