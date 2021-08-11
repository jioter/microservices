package com.gl.configuration.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class DeviceConfigurationBaseVO implements Serializable {

    @NotBlank
    @Size(min = 10, max = 15)
    private String serialNum;

    @NotBlank
    @Size(min = 7, max = 15)
    private String ip;

    @NotBlank
    @Size(min = 7, max = 15)
    private String netmask;
}
