package com.parlayZ.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private boolean status = true;
    private int errorCode;
    private String errorDescription;
    private Object responseDto;

}
