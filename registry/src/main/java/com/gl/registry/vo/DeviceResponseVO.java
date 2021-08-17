package com.gl.registry.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement
public class DeviceResponseVO extends DeviceBaseVO {

    @NotNull
    private Long deviceId;

    @NotBlank
    @Size(min = 7, max = 15)
    private String ip;

    @NotBlank
    @Size(min = 7, max = 15)
    private String netmask;
}
