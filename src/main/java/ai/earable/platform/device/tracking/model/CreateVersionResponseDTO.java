package ai.earable.platform.device.tracking.model;

import ai.earable.platform.device.tracking.enums.VersionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVersionResponseDTO {
    private UUID id;
    private String version;
    private String url;
    private boolean required;
    private List<String> releaseNote;
    private VersionType type;
    private String name;
}
