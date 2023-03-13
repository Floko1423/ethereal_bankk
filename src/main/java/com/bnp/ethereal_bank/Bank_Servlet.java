package com.bnp.ethereal_bank;

import jakarta.servlet.http.HttpServlet;

public class Bank_Servlet extends HttpServlet{
    // Nao é necessario podemos definir de modo a ter maior controlo -> mais seguranca
    //Spring provides built-in support for handling HTTP requests and responses, which eliminates the need for manually creating and configuring servlets.

// Instead of using servlets, you can use Spring MVC (Model-View-Controller) 
// to handle incoming requests and map them to appropriate controller methods. 
// These controller methods can then interact with Hibernate and PostgreSQL to 
// fetch or store data, and return responses 
// to the client using the appropriate view templates.
}
