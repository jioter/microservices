package com.gl.configuration.entity;

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
    private Long conf_id;
    private String serialNum;
    private InetAddress ip;
    private InetAddress netmask;
}
