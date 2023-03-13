package com.bnp.ethereal_bank.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bnp.ethereal_bank.configuration.Jwt_Service;
import com.bnp.ethereal_bank.model.Client;
import com.bnp.ethereal_bank.model.Role;
import com.bnp.ethereal_bank.repository.Client_Repository;
import static com.bnp.ethereal_bank.model.Client.Builder.*;

import lombok.RequiredArgsConstructor;
//import lombok.var;

//class que implementa o register e authenticate methods

@Service
@RequiredArgsConstructor
public class Authentication_Service {

    private Client_Repository repository;
    //private final PasswordEncoder passwordEncoder;
    private final Jwt_Service jwtService;

    //private final AuthenticationManager authenticationManager;


    public Authentication_Service( Jwt_Service jwtService, AuthenticationManager authenticationManager) {
        //this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        //this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(Register_Request request) { // create user, save into db and return token
                                                                       // generated
        Client.Builder builder = new Client.Builder();
        Client client = builder
                // se nao der crio um builder
                .setName(request.getName())
                .setEmail(request.getEmail())
                // .build(); // encode password before saving it to the db, temos de injetar o
                // servico de passenc
                .setPassword((request.getPassword())) //passwordEncoder.encode
                .setRole(Role.USER)
                .build();


        repository.save(client);

        var jwtToken = jwtService.generateToken(client);

        // repository.saveUser(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public Object authenticate(Authentication_Request request) {

        //authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getpassword()); // username e
                                                                                                     // password estao
                                                                                                     // certos -> gerar
                                                                                                     // token e envia-lo
                                                                                                     // de volta

        Client client = repository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(client);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
