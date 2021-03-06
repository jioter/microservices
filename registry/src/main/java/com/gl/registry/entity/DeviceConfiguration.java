package com.gl.registry.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "device_configuration")
@SuperBuilder
@NoArgsConstructor
public class DeviceConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conf_id")
    private Long confId;
    @Column(unique = true)
    private String serialNum;
    private String ip;
    private String netmask;
}
