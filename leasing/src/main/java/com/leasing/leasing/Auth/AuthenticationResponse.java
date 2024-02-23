package com.leasing.leasing.Auth;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
private String Token;
private String error;
}
