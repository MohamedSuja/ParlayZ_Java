package com.parlayZ.util;


import com.parlayZ.config.MessageConfig;
import com.parlayZ.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUtil {

    @Autowired
    MessageConfig messageConfig;

    public ResponseDto getExceptionServiceResponse(Exception e) {
        ResponseDto serviceResponseDto;
        serviceResponseDto = new ResponseDto();
        serviceResponseDto.setStatus(Boolean.FALSE);
        serviceResponseDto.setResponseDto(e.getMessage());
        serviceResponseDto.setErrorDescription(e.getMessage());
        return serviceResponseDto;
    }

    public ResponseDto getServiceResponse(Object responseObject) {
        ResponseDto serviceResponceDto;
        serviceResponceDto = new ResponseDto();
        serviceResponceDto.setStatus(Boolean.TRUE);
        serviceResponceDto.setResponseDto(responseObject);
        return serviceResponceDto;
    }

    public ResponseDto getErrorServiceResponse(Object responseObject) {
        ResponseDto serviceResponceDto;
        serviceResponceDto = new ResponseDto();
        serviceResponceDto.setStatus(Boolean.FALSE);
        serviceResponceDto.setResponseDto(responseObject);
        return serviceResponceDto;
    }

    public ResponseDto getErrorServiceResponse(String errorCode) {
        ResponseDto serviceResponseDto = new ResponseDto();
        serviceResponseDto.setStatus(Boolean.FALSE);
        serviceResponseDto.setErrorCode(Integer.parseInt(messageConfig.getErrorCode(errorCode)));
        serviceResponseDto.setErrorDescription(messageConfig.getMessage(errorCode));
        return serviceResponseDto;
    }

    public ResponseDto getCurrentPWErrorServiceResponse(String errorCode) {
        ResponseDto serviceResponseDto = new ResponseDto();
        serviceResponseDto.setStatus(Boolean.FALSE);
        serviceResponseDto.setErrorCode(342);
        serviceResponseDto.setErrorDescription(errorCode);
        return serviceResponseDto;
    }

    public ResponseDto getExceptionServiceResponseByProperties(String errorCode) {
        ResponseDto serviceResponseDto;
        serviceResponseDto = new ResponseDto();
        serviceResponseDto.setStatus(Boolean.FALSE);
        serviceResponseDto.setErrorCode(Integer.parseInt(messageConfig.getErrorCode(errorCode)));
        serviceResponseDto.setErrorDescription(messageConfig.getMessage(errorCode));
        return serviceResponseDto;
    }

    public ResponseDto getValidationErrorResponse(String message) {
        ResponseDto serviceResponseDto;
        serviceResponseDto = new ResponseDto();
        serviceResponseDto.setStatus(Boolean.FALSE);
        serviceResponseDto.setResponseDto(null);
        serviceResponseDto.setErrorCode(777);
        serviceResponseDto.setErrorDescription(message);
        return serviceResponseDto;
    }
}
