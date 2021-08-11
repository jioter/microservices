package com.gl.configuration;

import com.gl.configuration.entity.DeviceConfiguration;
import com.gl.configuration.exeptions.EntityNotFoundException;
import com.gl.configuration.repository.ConfigurationRepository;
import com.gl.configuration.service.ConfigurationService;
import com.gl.configuration.vo.DeviceConfigurationRequestVO;
import com.gl.configuration.vo.DeviceConfigurationResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void shouldThrowExceptionIfConfigurationNotFound() {
        String serialNumber = "wrong_Id-892734";

        assertThrows(EntityNotFoundException.class, () -> {
            configurationService.getConfigurationBySerialNumber(serialNumber);
        });
    }

    @Test
    void shouldGetAllConfigurations() {
        List<DeviceConfigurationResponseVO> actual = configurationService.getAllConfigurations();

        List<DeviceConfiguration> expected = configurationRepository.findAll();

        matchAll(expected, actual);
    }

    @Test
    void shouldCreateConfiguration(){
        Long id = 1L;
        String serialNumber = "01ABCDEF04";

        DeviceConfigurationRequestVO configurationRequest = createConfigurationRequest();
        DeviceConfigurationResponseVO configurationResponse = configurationService.createDeviceConfiguration(configurationRequest);

        assertNotNull(configurationResponse);
        assertEquals(id, configurationResponse.getConfId());
        assertEquals(serialNumber, configurationResponse.getSerialNum());

        matchOne(configurationRequest, configurationResponse);
    }

    private DeviceConfigurationRequestVO createConfigurationRequest() {
        String serialNum  = "01ABCDEF04";
        String ip = "192.168.0.104";
        String netmask = "255.255.255.4";

        return DeviceConfigurationRequestVO.builder()
                .serialNum(serialNum)
                .ip(ip)
                .netmask(netmask)
                .build();
    }

    private void matchAll(List<DeviceConfiguration> expected, List<DeviceConfigurationResponseVO> actual) {
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            matchOne(expected.get(i), actual.get(i));
        }
    }

    private void matchOne(DeviceConfiguration expected, DeviceConfigurationResponseVO actual) {
        assertEquals(expected.getConfId(), actual.getConfId());
        assertEquals(expected.getSerialNum(), actual.getSerialNum());
        assertEquals(expected.getIp(), actual.getIp());
        assertEquals(expected.getNetmask(), actual.getNetmask());
    }

    private void matchOne(DeviceConfigurationRequestVO expected, DeviceConfigurationResponseVO actual){
        assertEquals(expected.getSerialNum(), actual.getSerialNum());
        assertEquals(expected.getIp(), actual.getIp());
        assertEquals(expected.getNetmask(), actual.getNetmask());
    }
}
