package com.example.demo.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class EntityUser implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;
    String userName;
    String password;
    String firstName;
    String lastName;
    Date dateBorn;
    Set <String> Role;
}
