package com.example.ShipperService.Base_Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {
    NotFoundOrder(1000,"Not found Order", org.springframework.http.HttpStatus.NOT_FOUND),
    NotFoundUser(1000,"Not found User", org.springframework.http.HttpStatus.NOT_FOUND),
    NotFoundShipper(1000,"Not found Shipper", org.springframework.http.HttpStatus.NOT_FOUND),
    NotFoundOrderShipper(1000,"Not found order Shipper", org.springframework.http.HttpStatus.NOT_FOUND),
    NotFoundTracking(1000,"Not found Tracking", org.springframework.http.HttpStatus.NOT_FOUND),
    CreateShipperFail(1001,"Create Shipper Fail", org.springframework.http.HttpStatus.BAD_REQUEST),
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
