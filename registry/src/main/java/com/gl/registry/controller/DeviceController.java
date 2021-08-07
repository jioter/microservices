package com.gl.registry.controller;

import com.gl.registry.entity.Device;
import com.gl.registry.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalog/devices")
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping
    public List<Device> findAll() {
        return deviceService.getAllDevices();
    }

    @PostMapping
    public void create(@RequestBody Device device) {
        deviceService.addDevice(device);
    }
}
