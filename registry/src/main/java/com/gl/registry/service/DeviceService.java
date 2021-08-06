package com.gl.registry.service;

import com.gl.registry.entity.Device;
import com.gl.registry.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public void addDevice(Device device){
        deviceRepository.save(device);
    }

    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }
}
