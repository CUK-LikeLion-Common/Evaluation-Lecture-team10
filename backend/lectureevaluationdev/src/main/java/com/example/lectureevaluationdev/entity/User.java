package com.example.lectureevaluationdev.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
@Table(name = "user")
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @Column(name = "user_ID")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "status")
    private Boolean status;

    public User() {
        this.userId = null;
        this.userPassword = null;
        this.userEmail = null;
    }

    public User(Long id, String userId, String userPassword, String userEmail, Boolean status) {
        this.Id = id;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.status = status;
    }

    public String getUserId() { return userId; }

    public String getUserPassword() { return userPassword; }

    public String getUserEmail() { return userEmail; }
}
