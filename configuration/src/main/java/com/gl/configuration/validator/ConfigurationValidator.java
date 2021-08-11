package com.gl.configuration.validator;

import com.gl.configuration.exeptions.EntityAlreadyExistsException;
import com.gl.configuration.repository.ConfigurationRepository;
import com.gl.configuration.vo.DeviceConfigurationRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.module.Configuration;

@Component
@RequiredArgsConstructor
public class ConfigurationValidator {

    private final ConfigurationRepository configurationRepository;

    public void validateCreate(DeviceConfigurationRequestVO request) {
        checkDuplicateSerialNumber(request);
    }

    private void checkDuplicateSerialNumber(DeviceConfigurationRequestVO request) {
        if (request.getSerialNum() != null) {
            configurationRepository.findBySerialNum(request.getSerialNum())
                    .ifPresent(conf -> {
                        throw new EntityAlreadyExistsException(Configuration.class, "serial number", request.getSerialNum());
                    });
        }
    }
}
