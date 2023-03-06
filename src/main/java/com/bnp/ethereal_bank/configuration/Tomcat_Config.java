package com.bnp.ethereal_bank.configuration;

import org.apache.catalina.connector.Connector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.web.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

/*
don't need to have a Tomcat configuration class with that code if you're using Spring Boot.
 Spring Boot provides auto-configuration
  for embedded servlet containers, including Tomcat, so you don't need to configure the container yourself.


*/
/*@Configuration
public class Tomcat_Config {

    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatFactory() {
        return new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void customizeConnector(Connector connector) {
                super.customizeConnector(connector);
                connector.setPort(8080);
            }
        };
    } 
}

*/