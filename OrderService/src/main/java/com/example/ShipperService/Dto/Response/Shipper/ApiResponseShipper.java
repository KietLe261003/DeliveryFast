package com.example.ShipperService.Dto.Response.Shipper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseShipper {
    private int code;
    private String message;
    private Shipper data;
}
