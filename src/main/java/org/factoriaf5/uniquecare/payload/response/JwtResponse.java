package org.factoriaf5.uniquecare.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Date expiresAt;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Date expiresAt, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.expiresAt = expiresAt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}