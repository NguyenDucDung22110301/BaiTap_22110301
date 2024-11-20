package com.example.demo.example.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @Size(min = 8, message = "password must min length = 8")
    String password;
    String firstName;
    String lastName;
    Date dateBorn;
}
