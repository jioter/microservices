package com.gl.registry.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.InetAddress;

@Setter
@Getter
@Entity
@Table(name="device_configuration")
public class DeviceConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conf_id")
    private Long confId;
    @Column(unique = true)
    private String serialNum;
    private InetAddress ip;
    private InetAddress netmask;
}
