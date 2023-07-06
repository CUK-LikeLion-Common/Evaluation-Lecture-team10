package com.example.lectureevaluationdev.repository.user;

import com.example.lectureevaluationdev.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserID(String userID);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = true WHERE u.userID = :userID")
    int setStatusTrue(String userID);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = false WHERE u.userID= :userID")
    int setStatusFalse(String userID);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.userID = :userID")
    int deleteUserById(String userID);
}