package ai.earable.platform.device.tracking.mapper;

import ai.earable.platform.device.tracking.domain.VersionInfo;
import ai.earable.platform.device.tracking.model.CheckVersionRequestDTO;
import ai.earable.platform.device.tracking.model.CheckVersionResponseDTO;
import ai.earable.platform.device.tracking.model.CreateVersionRequestDTO;
import ai.earable.platform.device.tracking.model.CreateVersionResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VersionInfoMapper {

    VersionInfo map(CreateVersionRequestDTO createVersionRequestDTO);

    CreateVersionResponseDTO mapCreateResponse(VersionInfo versionInfo);

    VersionInfo map(CheckVersionRequestDTO checkVersionRequestDTO);
    
    CheckVersionResponseDTO mapToDTO(VersionInfo versionInfo);
}
