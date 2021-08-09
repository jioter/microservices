package com.gl.registry.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.InetAddress;

@Setter
@Getter
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Integer deviceId;
    @Column(name = "vendor")
    private String vendor;
    @Column(name = "model")
    private String model;
    @Column(unique = true, name = "serial_number")
    private String serialNum;
    @Column(name = "mac")
    private String mac;
    @Column(name = "ip")
    private InetAddress ip;
    @Column(name = "netmask")
    private InetAddress netmask;
}
