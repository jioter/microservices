package com.gl.configuration.controller;

import com.gl.configuration.entity.DeviceConfiguration;
import com.gl.configuration.service.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/configuration")
@RequiredArgsConstructor
public class ConfigurationController {
    private final ConfigurationService configurationService;

    @GetMapping
    public List<DeviceConfiguration> findAll() {
        return configurationService.getAllConfigurations();
    }

    @PostMapping
    public void create(@RequestBody DeviceConfiguration deviceConfiguration) {
        configurationService.saveConfiguration(deviceConfiguration);
    }
}
