package ai.earable.platform.device.tracking.model;

import ai.earable.platform.device.tracking.enums.VersionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckVersionRequestDTO {
    @NotNull(message = "type is required")
    private VersionType type;
    @NotBlank(message = "currentVersion is required")
    private String currentVersion;
}
