package ai.earable.platform.device.tracking.mapper;

import ai.earable.platform.device.tracking.domain.AppInfo;
import ai.earable.platform.device.tracking.model.StoreAppInfoRequestDTO;
import ai.earable.platform.device.tracking.model.StoreAppInfoResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppInfoMapper {

    AppInfo map(StoreAppInfoRequestDTO storeAppInfoRequestDTO);

    StoreAppInfoResponseDTO map(AppInfo appInfo);

}
