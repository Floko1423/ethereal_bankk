package com.bnp.ethereal_bank.authentication;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authentication_Request {
    private String nif;
    private String password;
    private String email;
    
    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email= email;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }


    
}
