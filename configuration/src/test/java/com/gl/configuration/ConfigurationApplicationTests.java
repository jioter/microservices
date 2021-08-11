package com.gl.configuration;

import com.gl.configuration.entity.DeviceConfiguration;
import com.gl.configuration.repository.ConfigurationRepository;
import com.gl.configuration.service.ConfigurationService;
import com.gl.configuration.vo.DeviceConfigurationResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/configuration-init.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/clean-up.sql")
class ConfigurationApplicationTests {

    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private ConfigurationRepository configurationRepository;

    @Test
    void shouldGetConfigurationBySerial() {
        String serialNumber = "01ABCDEF01";

        Optional<DeviceConfiguration> expected = configurationRepository.findBySerialNum(serialNumber);
        DeviceConfigurationResponseVO actual = configurationService.getConfigurationBySerialNumber(serialNumber);

        matchOne(expected.get(), actual);
    }


    private void matchOne(DeviceConfiguration expected, DeviceConfigurationResponseVO actual) {
        assertEquals(expected.getConfId(), actual.getConfId());
        assertEquals(expected.getSerialNum(), actual.getSerialNum());
        assertEquals(expected.getIp(), actual.getIp());
        assertEquals(expected.getNetmask(), actual.getNetmask());
    }
}
