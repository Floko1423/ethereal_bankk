package com.bnp.ethereal_bank.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.bnp.ethereal_bank.repository.Client_Repository;
import com.bnp.ethereal_bank.services.JwtUtils;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

// @RestController
// @RequestMapping("/auth")
// @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = { RequestMethod.GET,
// RequestMethod.POST })


@RestController
@RequestMapping("")
public class Controller_Authentication {

    private Authentication_Service service;

    @Autowired
    private Client_Repository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(".....") // /register
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody Register_Request request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> register(
        @RequestBody Authentication_Request request
    ) {
        return ResponseEntity.ok(service.authenticate(request));

    }


}
// @CrossOrigin(origins = "http://localhost:3000")
// @PostMapping("/login")
// public ResponseEntity<String> authenticateUser(@RequestBody
// Authentication_Request authRequest) {
// Authentication authentication = authenticationManager
// .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getNif(),
// authRequest.getpassword())); // nao
// // é
// // username/password
// // mas
// // como
// // recebe
// // 2
// // objs
// // é

// // SecurityContextHolder.getContext().setAuthentication(authentication);
// // String token = jwtUtils.generateToken(authentication.getPrincipal());
// // return ResponseEntity.ok(new AuthResponse(token));
// return ResponseEntity.ok("ses");
// }

// }

// // @GetMapping("/secured")
// // public ResponseEntity<?> securedEndpoint() {

// // String username = jwtUtils.getUsernameFromToken(getTokenFromHeader());

// // Client client = repository.findByNif(username);
// // // fazer handle error caso seja .error
// // return ResponseEntity.ok(client);
// // }

// // private String getTokenFromHeader() {
// // HttpServletRequest request = ((ServletRequestAttributes)
// RequestContextHolder.getRequestAttributes()).getRequest();
// // String header = request.getHeader("Authorization");
// // if (header != null && header.startsWith("Bearer ")) {
// // return header.substring(7);
// // }
// // return null;
// // }
// }
