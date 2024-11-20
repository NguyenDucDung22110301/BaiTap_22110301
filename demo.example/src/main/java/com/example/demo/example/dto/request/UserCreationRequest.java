package com.example.demo.example.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


// create fast an object
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String userName;
    @Size(min = 8, message = "USER_PASSWORD")
    String password;
    String firstName;
    String lastName;
    Date dateBorn;
}
