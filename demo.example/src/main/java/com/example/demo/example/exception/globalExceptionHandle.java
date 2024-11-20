package com.example.demo.example.exception;

import com.example.demo.example.dto.request.ResponseAPI;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// catch case exception
public class globalExceptionHandle {
    // statement help us generate code catch error// when I catch error from user it'll come here and catching it again?
    @ExceptionHandler (value = appException.class)
    public ResponseEntity<ResponseAPI> handleExistUserException(appException e){
        stateErrorCode stateErrorCode = e.getStateErrorCode();
        ResponseAPI responseAPI = new ResponseAPI();
        responseAPI.setCode(stateErrorCode.getCode());
        responseAPI.setMessage(stateErrorCode.getMessage());
        return ResponseEntity.badRequest().body(responseAPI);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseAPI> handlingValidation(MethodArgumentNotValidException e){
        String enumKey = e.getFieldError().getDefaultMessage();
        stateErrorCode ErrorCode = stateErrorCode.valueOf(enumKey);
        ResponseAPI responseAPI = new ResponseAPI();
        responseAPI.setMessage(ErrorCode.getMessage());
        responseAPI.setCode(ErrorCode.getCode());
        return ResponseEntity.badRequest().body(responseAPI);
    }
}
