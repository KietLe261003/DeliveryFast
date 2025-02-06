package com.example.UserService.Dto.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    Integer code;
    String message;
    Object data;
}
