package com.bnp.ethereal_bank.configuration;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bnp.ethereal_bank.model.Authorization;
import com.bnp.ethereal_bank.model.Client;
import com.bnp.ethereal_bank.repository.Client_Repository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// repository/service annotatations extends component annotation portanto tanto faz 

@Component
@RequiredArgsConstructor // para criar constructor usando qlq final field que usarmos
public class Jwt_Authentication_Filter  {
     String authHeader; // quando fazemos a call precisamos do header
    private  Jwt_Service jwtService;
    private UserDetailsService userDetailsService; // queremos a nossa propria implementacao para irbuscar a nossa DB


    

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, // we can intercept all requests and provide
                                                                         // info on the response depending on this
                                                                         // content
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) // chain of responsibility design apttern -> will call filters
                                              // sequentially
            throws ServletException, IOException {
        final String authHeader = request.getHeader(("Authorization"));
        final String jwtToken; //

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // passamos para o filter seguinte se o header nao tiver o bearer
            return; // para sair deste metodo
        }
        jwtToken = authHeader.substring(7); // pq len do Bearer=7

        String clientEmail = jwtService.extractClientName(jwtToken); // todo extract the clientEmail; -> from jwt Token
                                                                     // -> preciso duma class que manipule o token
        

        if (clientEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { // ver se ja esta
                                                                                                     // autenticado
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(clientEmail); // Client porque implements
                                                                                          // UserDetails
            if (jwtService.isTokenValid(jwtToken, userDetails)) { // se token for valid update do securitycontext e
                                                                  // fazer o request para o dispatcherservlet
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken); // update do securitycontext 
            }
        }
        filterChain.doFilter(request, response); // para passar para os novos filtros a executar
    }

}
