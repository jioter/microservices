package com.gl.registry.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.InetAddress;


@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeviceResponseVO extends DeviceBaseVO {

    @NotNull
    private Integer deviceId;

    @NotBlank
    @Size(min = 7, max = 15)
    private InetAddress ip;

    @NotBlank
    @Size(min = 7, max = 15)
    private InetAddress netmask;
}
