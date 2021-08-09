package com.gl.configuration.controller;

import com.gl.configuration.service.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vo.DeviceConfigurationRequestVO;
import vo.DeviceConfigurationResponseVO;

import java.util.List;

@RestController
@RequestMapping("api/configuration")
@RequiredArgsConstructor
public class ConfigurationController {
    private final ConfigurationService configurationService;

    @GetMapping("/{serialNum}")
    public DeviceConfigurationResponseVO getConfigurationBySerialNumber(@PathVariable String serialNum) {
        return configurationService.getConfigurationBySerialNumber(serialNum);
    }

    @GetMapping
    public List<DeviceConfigurationResponseVO> findAll() {
        return configurationService.getAllConfigurations();
    }

    @PostMapping
    public DeviceConfigurationResponseVO create(@RequestBody DeviceConfigurationRequestVO deviceConfigurationRequestVO) {
        return configurationService.createDeviceConfiguration(deviceConfigurationRequestVO);
    }
}
