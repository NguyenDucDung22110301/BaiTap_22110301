package com.example.demo.example.controllerUser;

import com.example.demo.example.dto.request.AuthenticationRequest;
import com.example.demo.example.dto.request.IntrospectRequest;
import com.example.demo.example.dto.request.ResponseAPI;
import com.example.demo.example.dto.response.AuthenticationResponse;
import com.example.demo.example.dto.response.IntrospectResponse;
import com.example.demo.example.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/token")
    ResponseAPI<AuthenticationResponse> checkPassword(@RequestBody AuthenticationRequest request){
        ResponseAPI responseAPI = new ResponseAPI<>();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        var result =  authenticationService.checkPassword(request);
//        result.setStatus(true);
        responseAPI.setResult(result);
        return  responseAPI;
    }
    @PostMapping("/introspect")
    ResponseAPI<IntrospectResponse> checkPassword(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        ResponseAPI responseAPI = new ResponseAPI<>();
        IntrospectRequest IntrospectRequest = new IntrospectRequest();
        var result =  authenticationService.introspectResponse(request);
        responseAPI.setResult(result);
        return  responseAPI;
    }
}
