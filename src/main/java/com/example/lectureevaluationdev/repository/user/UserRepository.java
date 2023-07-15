package com.example.lectureevaluationdev.repository.user;

import com.example.lectureevaluationdev.entity.user.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserID(String userID);

//    @Modifying
//    @Transactional
//    @Query("UPDATE UserEntity u SET u.status = true WHERE u.userID = :userID")
//    int setStatusTrue(@Param("userID") String userID);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE UserEntity u SET u.status = false WHERE u.userID = :userID")
//    int setStatusFalse(@Param("userID") String userID);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserEntity u WHERE u.userID = :userID")
    int deleteUserByUserID(@Param("userID") String userID);

}