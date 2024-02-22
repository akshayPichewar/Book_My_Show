package com.BookMyShowApp.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtAuthRequest {

    private String username;
    private String password;
}
