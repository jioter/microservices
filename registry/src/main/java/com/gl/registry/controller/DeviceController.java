package com.gl.registry.controller;

import com.gl.registry.service.DeviceService;
import com.gl.registry.vo.DeviceRequestVO;
import com.gl.registry.vo.DeviceResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalog/devices")
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping
    public List<DeviceResponseVO> findAll(@RequestParam(required = false) String vendor,
                                          @RequestParam(required = false) String model) {
        return deviceService.getAllDevices(vendor, model);
    }

    @PostMapping
    public DeviceResponseVO create(@RequestBody DeviceRequestVO deviceRequestVO) {
        return deviceService.createDevice(deviceRequestVO);
    }

    @GetMapping("/{id}")
    public DeviceResponseVO findById(@PathVariable Long id) {
        return deviceService.getById(id);
    }

    @GetMapping("/serial-number/{serialNum}")
    public DeviceResponseVO findBySerialNum(@PathVariable String serialNum) {
        return deviceService.getBySerialNum(serialNum);
    }
}
