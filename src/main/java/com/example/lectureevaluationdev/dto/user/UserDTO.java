package com.example.lectureevaluationdev.dto.user;



import com.example.lectureevaluationdev.entity.user.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 자동으로 만들어줌
@AllArgsConstructor
@ToString //toString 메서드 자동으로 만들어줌
public class UserDTO {

    private String userID;
    private String userEmail;
    private String userPassword;
    private boolean status;



    public static UserDTO toUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(userEntity.getUserID());
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserPassword(userEntity.getUserPassword());
        userDTO.setStatus(userEntity.getStatus());
        return userDTO;
    }
}
