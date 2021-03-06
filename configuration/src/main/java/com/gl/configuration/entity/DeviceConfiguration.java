package com.gl.configuration.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "device_configuration")
public class DeviceConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conf_id")
    private Long confId;
    @Column(unique = true, name = "serial_number")
    private String serialNum;
    @Column(name = "ip")
    private String ip;
    @Column(name = "netmask")
    private String netmask;
}
