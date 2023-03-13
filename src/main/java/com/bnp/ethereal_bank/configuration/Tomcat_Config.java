package com.bnp.ethereal_bank.configuration;

import org.apache.catalina.connector.Connector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.web.embedded.tomcat.TomcatEmbeddedServletAccountinerFactory;

/*
don't need to have a Tomcat configuration class  if using Spring Boot.
 Spring Boot provides auto-configuration
  for embedded servlet containers, including Tomcat, so you don't need to configure.


*/
/*@Configuration
public class Tomcat_Config {

    @Bean
    public TomcatEmbeddedServletAccountinerFactory tomcatFactory() {
        return new TomcatEmbeddedServletAccountinerFactory() {
            @Override
            protected void customizeConnector(Connector connector) {
                super.customizeConnector(connector);
                connector.setPort(8080);
            }
        };
    } 
}

*/