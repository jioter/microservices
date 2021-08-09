package com.gl.registry.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
public class DeviceBaseVO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String vendor;

    @NotBlank
    @Size(min = 2, max = 50)
    private String model;

    @NotBlank
    @Size(min = 10, max = 15)
    private String serialNum;

    @NotBlank
    @Size(min = 17, max = 17)
    private String mac;
}
