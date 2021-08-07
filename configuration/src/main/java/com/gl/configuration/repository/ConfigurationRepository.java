package com.gl.configuration.repository;

import com.gl.configuration.entity.DeviceConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<DeviceConfiguration, Long> {

    DeviceConfiguration findBySerialNum(String serialNum);
}
