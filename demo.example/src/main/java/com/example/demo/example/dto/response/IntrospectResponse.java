package com.example.demo.example.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IntrospectResponse {
    // check
    boolean valid;
}
