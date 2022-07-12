package ai.earable.platform.device.tracking.service;

import ai.earable.platform.device.tracking.domain.VersionInfo;
import ai.earable.platform.device.tracking.mapper.VersionInfoMapper;
import ai.earable.platform.device.tracking.model.CheckVersionRequestDTO;
import ai.earable.platform.device.tracking.model.CheckVersionResponseDTO;
import ai.earable.platform.device.tracking.model.CreateVersionRequestDTO;
import ai.earable.platform.device.tracking.repository.VersionInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VersionInfoService {

    private final VersionInfoRepository versionInfoRepository;

    private final VersionInfoMapper versionInfoMapper;

    public Mono<VersionInfo> createVersionInfo(CreateVersionRequestDTO createVersionRequestDTO) {
        VersionInfo versionInfo = versionInfoMapper.map(createVersionRequestDTO);
        versionInfo.setId(UUID.randomUUID());
        return versionInfoRepository.save(versionInfo);
    }

    /**
     * check version
     * @param checkVersionRequestDTO CheckVersionRequestDTO
     * @return CheckVersionResponseDTO
     */
    public Flux<CheckVersionResponseDTO> checkVersion(CheckVersionRequestDTO checkVersionRequestDTO) {
        return versionInfoRepository.findByType(checkVersionRequestDTO.getType().name())
                .filter(versionInfo -> {
                    VersionInfo currentVersion = new VersionInfo();
                    currentVersion.setVersion(checkVersionRequestDTO.getCurrentVersion());
                    return versionInfo.compareTo(currentVersion) > 0;
                })
                .sort(Comparator.reverseOrder())
                .map(versionInfoMapper::mapToDTO);
    }
}
