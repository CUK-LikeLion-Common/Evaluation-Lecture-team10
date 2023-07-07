package com.example.lectureevaluationdev.entity.user;

import com.example.lectureevaluationdev.dto.user.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data //get,set 메소드 이용가능하게 하는 어노테이션
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//는 JPA에서 기본 키를 자동으로 생성할 때 사용하는 방법 중 하나
    @Column(name="ID")
    private Long ID;

    @Column(name="user_ID")
    private String  userId;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="status")
    private Boolean status;
    /*
    @Column(name="userEmailHash")
    private String userEmailHash;

    @Column(name="userEmailChecked")
    private boolean userEmailChecked;
    */

//    public Long getUserID() {
//        return userID;
//    }
//
//    public String getUserPassword() {
//        return userPassword;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }

    public static UserEntity toUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUserPassword(userDTO.getUserPassword());
        userEntity.setStatus(userDTO.isStatus());
        return userEntity;
    }
}
