package com.example.demo.example.configuration;

import com.nimbusds.jose.crypto.MACSigner;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
    
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_API = {
            "/users", "/auth/introspect", "/auth/token"
    };
    @NonFinal
    @Value("${jwt.signKey}")
    private String SIGN_KEY;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                request -> request.requestMatchers(HttpMethod.POST, PUBLIC_API)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        );
        httpSecurity.oauth2ResourceServer(
          oauth2 -> oauth2.jwt(
                  jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
        );
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec spec = new SecretKeySpec(SIGN_KEY.getBytes() ,"HS512");
        return NimbusJwtDecoder.withSecretKey(spec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}
