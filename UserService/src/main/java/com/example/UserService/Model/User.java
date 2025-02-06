package com.example.UserService.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;
@Document(value = "User_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // Thông tin cơ bản
    @Id
    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;

    // Thông tin đăng nhập
    private String password; // Nên mã hóa trước khi lưu

    // Thông tin bổ sung
    private Date dateOfBirth;
    private String address;
    private Boolean gender;
    private String nationality;

    // Lịch sử và trạng thái tài khoản
    private String accountStatus; // Active, Inactive, Suspended, etc.
    private Date createdAt;
    private Date updatedAt;

    private String role;
}
