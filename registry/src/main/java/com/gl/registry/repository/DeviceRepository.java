package com.gl.registry.repository;

import com.gl.registry.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    List<Device> findAllByVendor(String vendor);

    List<Device> findAllByModel(String model);

    Device findBySerialNum(String serialNum);
}
