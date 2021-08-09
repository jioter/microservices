package com.gl.registry.mapper;

import com.gl.registry.entity.Device;
import com.gl.registry.vo.DeviceRequestVO;
import com.gl.registry.vo.DeviceResponseVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public DeviceMapper() {
        configureModelMapper();
    }

    private void configureModelMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public DeviceResponseVO toVO(Device device) {
        return modelMapper.map(device, DeviceResponseVO.class);
    }

    public Device toEntity(DeviceRequestVO deviceRequestVO) {
        return modelMapper.map(deviceRequestVO, Device.class);
    }
}
