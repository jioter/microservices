package com.gl.registry.service;

import com.gl.registry.entity.Device;
import com.gl.registry.entity.DeviceConfiguration;
import com.gl.registry.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public void addDevice(Device device) {

        ResponseEntity<DeviceConfiguration> getDeviceConfigurationBySerNum =
                new RestTemplate().getForEntity(
                        "http://localhost:8084/api/configuration/{device.getSerialNum()}",
                        DeviceConfiguration.class, device.getSerialNum());

        device.setIp(getDeviceConfigurationBySerNum.getBody().getIp());
        device.setNetmask(getDeviceConfigurationBySerNum.getBody().getNetmask());

        deviceRepository.save(device);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}
