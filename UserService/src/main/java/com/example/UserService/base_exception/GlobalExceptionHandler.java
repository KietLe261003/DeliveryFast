package com.example.UserService.base_exception;

import com.example.UserService.Dto.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(e.errorCode.getCode())
                .message(e.errorCode.getMessage())
                .build();
        return ResponseEntity.status(e.errorCode.getHttpStatus()).body(apiResponse);
    }
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity handleRuntimeException(RuntimeException e) {
        ApiResponse Res = ApiResponse.builder()
                .code(400)
                .message("Request bad")
                .data(e.getMessage()).build();
        return ResponseEntity.status(400).body(Res);
    }
}
