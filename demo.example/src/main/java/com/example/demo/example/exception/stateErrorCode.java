package com.example.demo.example.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
// it representative for class, constants not change
public enum stateErrorCode {
    // any class enum, it's presentation class ()
    USER_EXISTED (1001,"user existed!"),
    USER_NOT_FOUND(1002, "user not found"),
    USER_PASSWORD(1003, "password least 8 character"),
    USERNAME_NOT_FOUND(1004, "username not found"),
    WRONG_PASSWORD(1005, "Nguoi dung nhap sai mat khau"),
    ;
    // define class
    private final int code;
    private final String message;
}
