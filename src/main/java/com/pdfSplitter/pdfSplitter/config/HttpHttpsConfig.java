package com.pdfSplitter.pdfSplitter.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpHttpsConfig implements WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> {

    @Bean
    public WebServerFactoryCustomizer<WebServerFactory> customizer() {
        return factory -> {
            if (factory instanceof TomcatServletWebServerFactory tomcatFactory) {
                tomcatFactory.addAdditionalTomcatConnectors(createHttpConnector());
            }
        };
    }

    private Connector createHttpConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(8083); // HTTP Port
        return connector;
    }

    @Override
    public void customize(ConfigurableTomcatWebServerFactory factory) {

    }
}
