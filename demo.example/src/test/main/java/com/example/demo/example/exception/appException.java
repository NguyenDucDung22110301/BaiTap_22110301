package com.example.demo.example.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class appException extends RuntimeException{
        stateErrorCode stateErrorCode;
        public appException(stateErrorCode stateErrorCode) {
//            super(stateErrorCode.getMessage());
            this.stateErrorCode = stateErrorCode;
        }
}
