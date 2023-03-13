package com.bnp.ethereal_bank.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;

    
    
    
    public static Builder builder() {
        return new Builder();
    }

    private AuthenticationResponse(Builder builder) {
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }

    public static class Builder {
        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this);
        }
    }
    
    
}
