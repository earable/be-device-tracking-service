package ai.earable.platform.device.tracking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.EnableReactiveCassandraAuditing;
import org.springframework.data.domain.ReactiveAuditorAware;

/**
 * @author Hungnv
 * @date 13/06/2022
 */
@Configuration
@EnableReactiveCassandraAuditing()
public class ReactiveCassandraAuditing {
    @Bean
    public ReactiveAuditorAware myAuditorProvider() {
        return new AuditorAwareImpl();
    }
}
