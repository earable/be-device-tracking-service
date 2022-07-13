package ai.earable.platform.abs.controller;

import ai.earable.platform.abs.api.HealthCheckAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class HealthCheckController implements HealthCheckAPI {
    @Override
    public Mono<String> checkHealth() {
        return Mono.just("Up");
    }
}
