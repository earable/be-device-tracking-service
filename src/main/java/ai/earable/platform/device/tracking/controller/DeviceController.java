package ai.earable.platform.device.tracking.controller;

import ai.earable.platform.device.tracking.api.DeviceAPI;
import ai.earable.platform.device.tracking.mapper.AppInfoMapper;
import ai.earable.platform.device.tracking.mapper.VersionInfoMapper;
import ai.earable.platform.device.tracking.model.CheckVersionRequestDTO;
import ai.earable.platform.device.tracking.model.CheckVersionResponseDTO;
import ai.earable.platform.device.tracking.model.CreateVersionRequestDTO;
import ai.earable.platform.device.tracking.model.CreateVersionResponseDTO;
import ai.earable.platform.device.tracking.model.StoreAppInfoRequestDTO;
import ai.earable.platform.device.tracking.model.StoreAppInfoResponseDTO;
import ai.earable.platform.device.tracking.service.AppInfoService;
import ai.earable.platform.device.tracking.service.VersionInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DeviceController implements DeviceAPI {
    private final AppInfoService appInfoService;

    private final VersionInfoService versionInfoService;

    private final AppInfoMapper appInfoMapper;

    private final VersionInfoMapper versionInfoMapper;

    @Override
    public Mono<String> checkHealth() {
        return Mono.just("Up");
    }

    @Override
    public Mono<StoreAppInfoResponseDTO> storeAppInfo(StoreAppInfoRequestDTO storeAppInfoRequestDTO) {
        return appInfoService.storeAppInfo(storeAppInfoRequestDTO)
                .map(appInfoMapper::map);
    }

    @Override
    public Flux<CheckVersionResponseDTO> checkVersion(CheckVersionRequestDTO checkVersionRequestDTO) {
        return versionInfoService.checkVersion(checkVersionRequestDTO);
    }

    @Override
    public Mono<CreateVersionResponseDTO> createVersion(CreateVersionRequestDTO createVersionRequestDTO) {
        return versionInfoService.createVersionInfo(createVersionRequestDTO)
                .map(versionInfoMapper::mapCreateResponse);
    }
}
