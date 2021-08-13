package com.gl.registry.repository;

import com.gl.registry.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByVendor(String vendor);

    List<Device> findAllByModel(String model);

    Optional<Device> findBySerialNum(String serialNum);
}
