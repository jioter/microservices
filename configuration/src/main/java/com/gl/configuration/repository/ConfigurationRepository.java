package com.gl.configuration.repository;

import com.gl.configuration.entity.DeviceConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<DeviceConfiguration, Long> {

    Optional<DeviceConfiguration> findBySerialNum(String serialNum);
}
