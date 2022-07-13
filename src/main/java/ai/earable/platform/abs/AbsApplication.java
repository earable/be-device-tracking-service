/*
 * Copyright (c) 2021 Earable Inc
 *
 * ==============================================================================
 */

package ai.earable.platform.abs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@OpenAPIDefinition(servers = {@Server(url = "/dts/api/v3", description = "Default Server URL")})
@SpringBootApplication
@ComponentScan(basePackages = {"ai.earable.platform"})
@EnableWebFlux
public class AbsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AbsApplication.class, args);
    }
}
