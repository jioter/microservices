package com.gl.configuration.service;

import com.gl.configuration.entity.DeviceConfiguration;
import com.gl.configuration.repository.ConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;

    public List<DeviceConfiguration> getAllConfigurations(){
        return configurationRepository.findAll();
    }

    public void saveConfiguration(DeviceConfiguration deviceConfiguration){
        configurationRepository.save(deviceConfiguration);
    }
}
