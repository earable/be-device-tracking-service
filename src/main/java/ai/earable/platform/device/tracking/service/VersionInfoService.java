package ai.earable.platform.device.tracking.service;

import ai.earable.platform.common.data.exception.EarableErrorCode;
import ai.earable.platform.common.data.exception.EarableException;
import ai.earable.platform.device.tracking.domain.VersionInfo;
import ai.earable.platform.device.tracking.enums.VersionType;
import ai.earable.platform.device.tracking.mapper.VersionInfoMapper;
import ai.earable.platform.device.tracking.model.CheckVersionRequestDTO;
import ai.earable.platform.device.tracking.model.CheckVersionResponseDTO;
import ai.earable.platform.device.tracking.model.CreateVersionRequestDTO;
import ai.earable.platform.device.tracking.repository.VersionInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        log.info("[CREATE_VERSION_INFO]");
        VersionInfo versionInfo = versionInfoMapper.map(createVersionRequestDTO);
        versionInfo.setId(UUID.randomUUID());
        return versionInfoRepository.save(versionInfo);
    }

    /**
     * check version
     *
     * @param checkVersionRequestDTO CheckVersionRequestDTO
     * @return CheckVersionResponseDTO
     */
    public Flux<CheckVersionResponseDTO> checkVersion(CheckVersionRequestDTO checkVersionRequestDTO) {
        log.info("[CHECK_VERSION] with type {} and currentVersion {}", checkVersionRequestDTO.getType(), checkVersionRequestDTO.getCurrentVersion());
        String versionName, versionNumber;
        if (VersionType.FIRMWARE.equals(checkVersionRequestDTO.getType())) {
            // get current version name and number from full name
            String fullFirmwareVersionName = checkVersionRequestDTO.getCurrentVersion();
            int lastSplit = fullFirmwareVersionName.lastIndexOf("_");
            if (lastSplit == -1 || (lastSplit + 1 == fullFirmwareVersionName.length())) {
                throw new EarableException(HttpStatus.BAD_REQUEST.value(), EarableErrorCode.INPUT_INVALID, "The check version is invalid format");
            }
            versionName = fullFirmwareVersionName.substring(0, lastSplit);
            versionNumber = fullFirmwareVersionName.substring(lastSplit + 1);
        } else {
            // with app version
            versionNumber = checkVersionRequestDTO.getCurrentVersion();
            versionName = "";
        }
        Flux<VersionInfo> result = versionInfoRepository.findByType(checkVersionRequestDTO.getType().name())
                // with firmware then we need compare name of version before compare version number
                .filter(versionInfo -> !VersionType.FIRMWARE.equals(versionInfo.getType()) || versionInfo.getName().equalsIgnoreCase(versionName))
                .filter(versionInfo -> {
                    VersionInfo currentVersion = new VersionInfo();
                    currentVersion.setVersion(versionNumber);
                    return versionInfo.compareTo(currentVersion) > 0;
                })
                .sort(Comparator.reverseOrder());
        if (VersionType.FIRMWARE.equals(checkVersionRequestDTO.getType())) {
            // for firmware we return only latest version
            return result.take(1).map(versionInfoMapper::mapToDTO);
        } else {
            return result.map(versionInfoMapper::mapToDTO);
        }
    }
}
