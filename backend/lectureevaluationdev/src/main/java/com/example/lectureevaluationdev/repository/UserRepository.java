package com.example.lectureevaluationdev.repository;

import com.example.lectureevaluationdev.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String user_ID);
    User findByUserIdAndUserPassword(String user_ID, String user_password);


    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.user_ID = :user_ID")
    int deleteUserByUserId(@Param("user_ID") String user_ID);
}
