package com.example.demo.example.service;

import com.example.demo.example.dto.request.UserCreationRequest;
import com.example.demo.example.dto.request.UserUpdateRequest;
import com.example.demo.example.dto.response.ResponseEntityUser;
import com.example.demo.example.entity.EntityUser;
import com.example.demo.example.exception.appException;
import com.example.demo.example.exception.stateErrorCode;
import com.example.demo.example.mapper.UserMapper;
import com.example.demo.example.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    public ResponseEntityUser createRequest (UserCreationRequest request){
        if(userRepository.existsUserByUserName(request.getUserName()))
            throw new appException(stateErrorCode.USER_EXISTED);
        EntityUser user = userMapper.toUserCreate(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toResponseUser(userRepository.save(user));
    }
    public ResponseEntityUser updateUser(UserUpdateRequest request, int id){
        EntityUser user = userRepository.findById(id).orElseThrow(() -> new appException(stateErrorCode.USER_NOT_FOUND));
       return userMapper.toResponseUser(userRepository
                                                        .save(userMapper.toUpdateUser(user, request)));
    }
    public List<EntityUser> getAllUser(){
       return userRepository.findAll();
    }
    public ResponseEntityUser getUserById(int id){
        return userMapper.toResponseUser(userRepository.findById(id).orElseThrow
                (() -> new appException(stateErrorCode.USER_NOT_FOUND)));
    }
    public void deleteUser(int id){
       userRepository.deleteById(id);
    }

}
