package com.example.demo.example.mapper;


import com.example.demo.example.dto.request.UserCreationRequest;
import com.example.demo.example.dto.request.UserUpdateRequest;
import com.example.demo.example.dto.response.ResponseEntityUser;
import com.example.demo.example.entity.EntityUser;
import org.mapstruct.*;

@Mapper (componentModel =  "Spring")
public interface UserMapper {
    EntityUser toUserCreate(UserCreationRequest request);
    EntityUser toUpdateUser(@MappingTarget EntityUser entityUser, UserUpdateRequest request);
    ResponseEntityUser toResponseUser(EntityUser entityUser);
}
