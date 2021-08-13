package com.gl.registry.service;

import com.gl.registry.entity.Device;
import com.gl.registry.entity.DeviceConfiguration;
import com.gl.registry.exeptions.EntityNotFoundException;
import com.gl.registry.mapper.DeviceMapper;
import com.gl.registry.repository.DeviceRepository;
import com.gl.registry.vo.DeviceRequestVO;
import com.gl.registry.vo.DeviceResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public DeviceResponseVO addDevice(DeviceRequestVO request) {

        ResponseEntity<DeviceConfiguration> getDeviceConfigurationBySerNum =
                new RestTemplate().getForEntity(
                        "http://localhost:8084/api/configuration/{request.getSerialNum()}",
                        DeviceConfiguration.class, request.getSerialNum());

        Device device = deviceMapper.toEntity(request);

        device.setIp(getDeviceConfigurationBySerNum.getBody().getIp());
        device.setNetmask(getDeviceConfigurationBySerNum.getBody().getNetmask());

        return deviceMapper.toVO(deviceRepository.save(device));
    }

    public List<DeviceResponseVO> getAllDevices(String vendor, String model) {
        if (vendor != null) {
            return deviceRepository.findAllByVendor(vendor).stream().map(deviceMapper::toVO).collect(Collectors.toList());
        } else if (model != null) {
            return deviceRepository.findAllByModel(model).stream().map(deviceMapper::toVO).collect(Collectors.toList());
        } else {
            return deviceRepository.findAll().stream().map(deviceMapper::toVO).collect(Collectors.toList());
        }
    }

    public DeviceResponseVO getById(Long id) {
        return deviceMapper.toVO(deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Device.class, "id", id)));
    }

    public DeviceResponseVO getBySerialNum(String serialNum) {
        return deviceMapper.toVO(deviceRepository.findBySerialNum(serialNum).orElseThrow(() ->
                new EntityNotFoundException(Device.class, "serial number", serialNum)));
    }
}
