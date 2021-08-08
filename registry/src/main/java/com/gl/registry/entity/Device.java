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
    private Integer device_id;
    private String vendor;
    private String model;
    @Column(unique = true)
    private String serialNum;
    private String mac;
    private InetAddress ip;
    private InetAddress netmask;
}
