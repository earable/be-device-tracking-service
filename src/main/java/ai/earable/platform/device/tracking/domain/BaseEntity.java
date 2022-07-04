package ai.earable.platform.device.tracking.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.cassandra.core.mapping.Column;

import java.time.Instant;

public class BaseEntity {
    @LastModifiedDate
    @Column("updated_at")
    private Instant updatedAt;
    @CreatedDate
    @Column("created_at")
    private Instant createdAt;
}
