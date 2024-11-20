package com.example.demo.example.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE )
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseAPI<T> {
    int code= 1000;
    String message;
    T result;
}
