package ai.earable.platform.device.tracking.model;

import ai.earable.platform.device.tracking.enums.VersionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVersionRequestDTO {
    private String version;
    private String url;
    private boolean required;
    private List<String> releaseNote;
    private VersionType type;
    private String name;
}
