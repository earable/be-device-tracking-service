package ai.earable.platform.abs.api;

import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

public interface HealthCheckAPI {
    String HEALTH = "/health";

    @GetMapping(value = HEALTH)
    Mono<String> checkHealth();
}

