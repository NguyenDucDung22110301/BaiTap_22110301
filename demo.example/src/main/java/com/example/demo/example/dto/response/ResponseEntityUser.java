package com.example.demo.example.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseEntityUser {
    int id;
    String userName;
    String firstName;
    String lastName;
    Date dateBorn;
    Set <String> Role;
}
