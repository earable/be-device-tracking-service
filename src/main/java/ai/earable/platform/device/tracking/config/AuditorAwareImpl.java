package ai.earable.platform.device.tracking.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.ReactiveAuditorAware;
import reactor.core.publisher.Mono;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
public class AuditorAwareImpl implements ReactiveAuditorAware<String> {

    @Override
    public Mono<String> getCurrentAuditor() {
        return Mono.just("DTS");
    }
}
