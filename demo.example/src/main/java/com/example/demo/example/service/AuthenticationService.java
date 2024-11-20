package com.example.demo.example.service;

import com.example.demo.example.dto.request.AuthenticationRequest;
import com.example.demo.example.dto.request.IntrospectRequest;
import com.example.demo.example.dto.response.AuthenticationResponse;
import com.example.demo.example.dto.response.IntrospectResponse;
import com.example.demo.example.exception.appException;
import com.example.demo.example.exception.stateErrorCode;
import com.example.demo.example.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    protected static final String SIGN_KEY =
            "llOLLAGjN8hjLQTCefRomDKTGzzBxZISXiNKTgt9LNai5o1gHSCkFr9uR5cmrNFJ";

    public IntrospectResponse introspectResponse(IntrospectRequest request) throws JOSEException,ParseException {
        var token = request.getToken();
        // code sign
        JWSVerifier jwsVerifier = new MACVerifier(SIGN_KEY.getBytes(StandardCharsets.UTF_8));
        // take it from token
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(jwsVerifier);
        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();

    }

    public AuthenticationResponse checkPassword(AuthenticationRequest request){
        var user = userRepository.findByUserName(request.getUserName()).orElseThrow(
                                            () -> new appException(stateErrorCode.USERNAME_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10 );
        boolean result =  passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!result){
            throw new  appException(stateErrorCode.WRONG_PASSWORD);
        }else {
            var token = generateToken(user.getUserName());
            return AuthenticationResponse.builder()
                    .token(token)
                    .status(true)
                    .build();
        }
    }
    private String generateToken(String userName){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                                                    .subject(userName)
                                                    .issuer("ducdung")
                                                    .issueTime(new Date())
                                                    .expirationTime(new Date(
                                                            Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                                                    ))
                                                    .claim("customClaim", "Custom")
                                                    .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGN_KEY.getBytes(StandardCharsets.UTF_8)));
            return jwsObject.serialize();
        }catch (JOSEException e) {
            log.error("can't create token" + e);
            throw new RuntimeException(e);
        }
    }

}

