package com.gl.registry.exeptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@RequiredArgsConstructor
public class ExceptionResponse implements Serializable {
    private final String exceptionMessage;
    private final String message;
}
