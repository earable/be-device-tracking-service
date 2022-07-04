/*
 * Copyright (c) 2021 Earable Inc
 *
 * ==============================================================================
 */

package ai.earable.platform.device.tracking.repository;

import ai.earable.platform.device.tracking.domain.AppInfo;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppInfoRepository extends ReactiveCassandraRepository<AppInfo, UUID> {
}
