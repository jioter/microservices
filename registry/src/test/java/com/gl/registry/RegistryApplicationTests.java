package com.gl.registry;

import com.gl.registry.entity.Device;
import com.gl.registry.repository.DeviceRepository;
import com.gl.registry.service.DeviceService;
import com.gl.registry.vo.DeviceResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/device-init.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/clean-up.sql")
class RegistryApplicationTests {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    void shouldGetById() {
        Long id = 2L;

        DeviceResponseVO actual = deviceService.getById(id);
        Optional<Device> expected = deviceRepository.findById(id);

        matchOne(expected, actual);
    }

    @Test
    void shouldGetBySerialNumber(){
        String serialNumber = "03ABCDEF03";

        DeviceResponseVO actual = deviceService.getBySerialNum(serialNumber);
        Optional<Device> expected = deviceRepository.findBySerialNum(serialNumber);

        matchOne(expected,actual);
    }

    private void matchOne(Optional<Device> expected, DeviceResponseVO actual) {
        assertEquals(expected.get().getDeviceId(), actual.getDeviceId());
        assertEquals(expected.get().getIp(), actual.getIp());
        assertEquals(expected.get().getNetmask(), actual.getNetmask());
        assertEquals(expected.get().getMac(), actual.getMac());
        assertEquals(expected.get().getModel(), actual.getModel());
        assertEquals(expected.get().getSerialNum(), actual.getSerialNum());
        assertEquals(expected.get().getVendor(), actual.getVendor());
    }
}

