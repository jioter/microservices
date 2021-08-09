package com.gl.configuration.mapper;

import com.gl.configuration.entity.DeviceConfiguration;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vo.DeviceConfigurationRequestVO;
import vo.DeviceConfigurationResponseVO;

@Component
public class ConfigurationMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ConfigurationMapper(){
        configureModelMapper();
    }

    private void configureModelMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public DeviceConfigurationResponseVO toVO(DeviceConfiguration deviceConfiguration){
        return modelMapper.map(deviceConfiguration, DeviceConfigurationResponseVO.class);
    }

    public DeviceConfiguration toEntity(DeviceConfigurationRequestVO deviceConfigurationRequestVO){
        return modelMapper.map(deviceConfigurationRequestVO, DeviceConfiguration.class);
    }
}
