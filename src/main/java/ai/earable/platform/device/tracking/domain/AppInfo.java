package ai.earable.platform.device.tracking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@Table(value = "user_app_info")
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo extends BaseEntity {
    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID id;
    @Column("app_id")
    private String appId;
    @Column("notify_token")
    private String notifyToken;
    @Column("os")
    private String os;
    @Column("app_version")
    private String appVersion;
    @Column("user_id")
    private UUID userId;
}
