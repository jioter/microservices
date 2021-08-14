package com.gl.registry;

import com.gl.registry.entity.Device;
import com.gl.registry.entity.DeviceConfiguration;
import com.gl.registry.exeptions.EntityNotFoundException;
import com.gl.registry.mapper.DeviceMapper;
import com.gl.registry.repository.DeviceRepository;
import com.gl.registry.service.DeviceService;
import com.gl.registry.vo.DeviceRequestVO;
import com.gl.registry.vo.DeviceResponseVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/device-init.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/clean-up.sql")
class RegistryApplicationTests {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceRepository deviceRepository;
    @Mock
    private RestTemplate restTemplate;
    @Autowired
    private DeviceMapper deviceMapper;

    @BeforeEach
    public void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);

        deviceService = new DeviceService(deviceRepository, deviceMapper, restTemplate);

    }

    @Test
    void shouldGetById() {
        Long id = 2L;

        DeviceResponseVO actual = deviceService.getById(id);
        Optional<Device> expected = deviceRepository.findById(id);

        matchOne(expected, actual);
    }

    @Test
    void shouldGetBySerialNumber() {
        String serialNumber = "03ABCDEF03";

        DeviceResponseVO actual = deviceService.getBySerialNum(serialNumber);
        Optional<Device> expected = deviceRepository.findBySerialNum(serialNumber);

        matchOne(expected, actual);
    }

    @Test
    void shouldThrowExceptionIfDeviceNotFoundBySerialNumber() {
        String serialNumber = "not_correct_serial_number_123";

        assertThrows(EntityNotFoundException.class, () -> {
            deviceService.getBySerialNum(serialNumber);
        });
    }

    @Test
    void shouldCreateDevice() {
        Long id = 1L;
        String serialNumber = "01ABCDEF03";

        DeviceRequestVO deviceRequest = createDeviceRequest();

        when(restTemplate.getForEntity(
                eq("http://localhost:8084/api/configuration/{request.getSerialNum()}"),
                eq(DeviceConfiguration.class), eq(deviceRequest.getSerialNum())))
                .thenReturn(new ResponseEntity<>(createDeviceWithConfigurationResponse(), HttpStatus.CREATED));


        DeviceResponseVO deviceResponse = deviceService.createDevice(deviceRequest);

        assertNotNull(deviceResponse);
        assertEquals(id, deviceResponse.getDeviceId());
        assertEquals(serialNumber, deviceResponse.getSerialNum());

        matchOne(deviceRequest, deviceResponse);
    }

    private DeviceRequestVO createDeviceRequest() {

        String vendor = "Apple";
        String model = "iPhone-5";
        String serialNum = "01ABCDEF03";
        String mac = "11-22-33-44-55-66";

        return DeviceRequestVO.builder()
                .serialNum(serialNum)
                .vendor(vendor)
                .model(model)
                .mac(mac)
                .build();
    }

    private DeviceConfiguration createDeviceWithConfigurationResponse() {
        Long confId = 1L;
        String ip = "192.168.0.104";
        String netmask = "255.255.255.4";
        String serialNum = "01ABCDEF03";

        return DeviceConfiguration.builder()
                .ip(ip)
                .netmask(netmask)
                .serialNum(serialNum)
                .confId(confId)
                .build();
    }

    @Test
    void shouldGetAllDevice() {
        List<DeviceResponseVO> actual = deviceService.getAllDevices(null, null);

        List<Device> expected = deviceRepository.findAll();

        matchAll(expected, actual);
    }

    private void matchAll(List<Device> expected, List<DeviceResponseVO> actual) {
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            matchOne(expected.get(i), actual.get(i));
        }
    }

    private void matchOne(Device expected, DeviceResponseVO actual) {
        assertEquals(expected.getDeviceId(), actual.getDeviceId());
        assertEquals(expected.getIp(), actual.getIp());
        assertEquals(expected.getNetmask(), actual.getNetmask());
        assertEquals(expected.getMac(), actual.getMac());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getSerialNum(), actual.getSerialNum());
        assertEquals(expected.getVendor(), actual.getVendor());
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

    private void matchOne(DeviceRequestVO request, DeviceResponseVO response) {
        assertEquals(request.getMac(), response.getMac());
        assertEquals(request.getModel(), response.getModel());
        assertEquals(request.getSerialNum(), response.getSerialNum());
        assertEquals(request.getVendor(), response.getVendor());

    }
}

