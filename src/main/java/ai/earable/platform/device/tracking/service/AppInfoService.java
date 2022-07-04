package ai.earable.platform.device.tracking.service;

import ai.earable.platform.common.webflux.security.ReactiveSecurityContextUtils;
import ai.earable.platform.device.tracking.domain.AppInfo;
import ai.earable.platform.device.tracking.mapper.AppInfoMapper;
import ai.earable.platform.device.tracking.model.StoreAppInfoRequestDTO;
import ai.earable.platform.device.tracking.repository.AppInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppInfoService {

    private final AppInfoRepository appInfoRepository;

    private final ReactiveSecurityContextUtils reactiveSecurityContextUtils;

    private final AppInfoMapper appInfoMapper;

    /**
     * store app info
     *
     * @param storeAppInfoRequestDTO StoreAppInfoRequestDTO
     * @return StoreAppInfoResponseDTO
     */
    public Mono<AppInfo> storeAppInfo(StoreAppInfoRequestDTO storeAppInfoRequestDTO) {
        return reactiveSecurityContextUtils.getUserId().flatMap(userId -> {
            AppInfo newAppInfo = appInfoMapper.map(storeAppInfoRequestDTO);
            newAppInfo.setUserId(UUID.fromString(userId));
            newAppInfo.setId(UUID.randomUUID());
            return appInfoRepository.save(newAppInfo);
        });
    }
}
