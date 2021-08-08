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
    public List<Device> findAll(@RequestParam(required = false) String vendor,
                                @RequestParam(required = false) String model) {
        return deviceService.getAllDevices(vendor, model);
    }

    @PostMapping
    public void create(@RequestBody Device device) {
        deviceService.addDevice(device);
    }

    @GetMapping("/{id}")
    public Device getById(@PathVariable Integer id){
        return deviceService.getById(id);
    }

    @GetMapping("/serial-number/{serialNum}")
    public Device findBySerialNum(@PathVariable String serialNum){
        return deviceService.getBySerialNum(serialNum);
    }
}
