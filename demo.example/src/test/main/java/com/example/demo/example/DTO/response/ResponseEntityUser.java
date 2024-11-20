package com.example.demo.example.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseEntityUser {
    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateBorn;
}
