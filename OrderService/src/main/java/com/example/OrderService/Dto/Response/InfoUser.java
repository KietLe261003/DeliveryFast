package com.example.OrderService.Dto.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class InfoUser {
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
