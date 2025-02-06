package com.example.OrderService.Base_Exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {
    NotFoundOrder(1000,"Not found Order", org.springframework.http.HttpStatus.NOT_FOUND),
    NotFoundUser(1000,"Not found User", org.springframework.http.HttpStatus.NOT_FOUND),
    ;
    Integer code;
    String message;
    HttpStatus HttpStatus;
    ErrorCode(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.HttpStatus = httpStatus;
    }

}
