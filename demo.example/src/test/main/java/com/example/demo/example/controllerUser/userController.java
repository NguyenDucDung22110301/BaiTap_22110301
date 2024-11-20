package com.example.demo.example.controllerUser;

import com.example.demo.example.dto.request.ResponseAPI;
import com.example.demo.example.dto.request.UserCreationRequest;
import com.example.demo.example.dto.request.UserUpdateRequest;
import com.example.demo.example.dto.response.ResponseEntityUser;
import com.example.demo.example.entity.EntityUser;
import com.example.demo.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private UserService userService;

    @PostMapping
    ResponseAPI<ResponseEntityUser> createUser(@RequestBody @Valid UserCreationRequest request){
        ResponseAPI<ResponseEntityUser> responseAPI = new ResponseAPI<>();
        responseAPI.setResult(userService.createRequest(request));
        return responseAPI;
    }
    @GetMapping
    List<EntityUser> getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping("/{id}")
    ResponseEntityUser findById(@PathVariable int id){
        return userService.getUserById(id);
    }
    @PutMapping("/{id}")
    ResponseEntityUser updateUser(@PathVariable int id, @RequestBody @Valid UserUpdateRequest request){
        return userService.updateUser(request,id );
    }
    @DeleteMapping("/{id}")
    void deleteUser (@PathVariable int id){
        userService.deleteUser(id);
    }
}
