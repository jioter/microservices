package com.gl.configuration.service;

import com.gl.configuration.entity.DeviceConfiguration;
import com.gl.configuration.exeptions.EntityNotFoundException;
import com.gl.configuration.mapper.ConfigurationMapper;
import com.gl.configuration.repository.ConfigurationRepository;
import com.gl.configuration.validator.ConfigurationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.gl.configuration.vo.DeviceConfigurationRequestVO;
import com.gl.configuration.vo.DeviceConfigurationResponseVO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;
    private final ConfigurationMapper configurationMapper;
    private final ConfigurationValidator configurationValidator;

    public List<DeviceConfigurationResponseVO> getAllConfigurations() {
        return configurationRepository.findAll().stream().map(configurationMapper::toVO).collect(Collectors.toList());
    }

    public DeviceConfigurationResponseVO createDeviceConfiguration(DeviceConfigurationRequestVO request) {
        configurationValidator.validateCreate(request);

        Optional<DeviceConfiguration> existedDeviceConfiguration = configurationRepository.findBySerialNum(request.getSerialNum());

        return existedDeviceConfiguration.isPresent() ?
                configurationMapper.toVO(existedDeviceConfiguration.get()) :
                configurationMapper.toVO(configurationRepository
                        .save(configurationMapper.toEntity(request)));
    }

    public DeviceConfigurationResponseVO getConfigurationBySerialNumber(String serialNum) {
        DeviceConfiguration deviceConfiguration = configurationRepository.findBySerialNum(serialNum)
                .orElseThrow(() -> new EntityNotFoundException(DeviceConfiguration.class, "serial number", serialNum));
        return configurationMapper.toVO(deviceConfiguration);
    }
}
