package com.gl.configuration.service;

import com.gl.configuration.entity.DeviceConfiguration;
import com.gl.configuration.mapper.ConfigurationMapper;
import com.gl.configuration.repository.ConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vo.DeviceConfigurationRequestVO;
import vo.DeviceConfigurationResponseVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;
    private final ConfigurationMapper configurationMapper;

    public List<DeviceConfigurationResponseVO> getAllConfigurations() {
        return configurationRepository.findAll().stream().map(configurationMapper::toVO).collect(Collectors.toList());
    }

    public DeviceConfigurationResponseVO createDeviceConfiguration(DeviceConfigurationRequestVO request) {
        DeviceConfiguration existedDeviceConfiguration = configurationRepository.findBySerialNum(request.getSerialNum());

        return existedDeviceConfiguration == null ? configurationMapper.toVO(configurationRepository.save(configurationMapper.toEntity(request))) :
                configurationMapper.toVO(existedDeviceConfiguration);
    }

    public DeviceConfigurationResponseVO getConfigurationBySerialNumber(String serialNum) {
        DeviceConfiguration deviceConfiguration = configurationRepository.findBySerialNum(serialNum);
        return configurationMapper.toVO(deviceConfiguration);
    }
}
