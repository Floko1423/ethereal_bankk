package com.bnp.ethereal_bank.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class Register_Request {

    // private String firstName;
    // private String lastName;
    private String name;
    private String password;
    private String email;

     public Register_Request(String name, String password, String email) {
     this.name = name;
     this.password = password;
     this.email = email;
     
     }

    // public String getFirstName() {
    //     return firstName;
    // }

    // public String getLastName() {
    //     return lastName;
    // }

    // public void setFirstName(String email) {
    //     this.firstName = firstName;
    // }

    // public void setLastName(String email) {
    //     this.lastName = lastName;
    // }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}
