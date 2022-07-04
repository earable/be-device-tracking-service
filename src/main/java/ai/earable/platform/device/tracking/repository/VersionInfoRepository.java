/*
 * Copyright (c) 2021 Earable Inc
 *
 * ==============================================================================
 */

package ai.earable.platform.device.tracking.repository;

import ai.earable.platform.device.tracking.domain.VersionInfo;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface VersionInfoRepository extends ReactiveCassandraRepository<VersionInfo, UUID> {

    @AllowFiltering
    Flux<VersionInfo> findByType(String type);
}
