package ai.earable.platform.device.tracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreAppInfoResponseDTO {
    private UUID userId;
    private String notifyToken;
    private String appId;
    private String os;
    private String appVersion;
}
