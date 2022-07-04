package ai.earable.platform.device.tracking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreAppInfoRequestDTO {
    private String notifyToken;
    @NotBlank(message = "appId is required")
    private String appId;
    private String os;
    private String appVersion;
}
