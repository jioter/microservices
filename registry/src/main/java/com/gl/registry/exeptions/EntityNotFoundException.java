package com.gl.registry.exeptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends CustomBaseException {

    private static final String EXCEPTION_MSG = "Could not find %s with %s: %s";

    public EntityNotFoundException(Class clazz, String fieldName, Object value) {
        super(clazz, fieldName, value, EXCEPTION_MSG);
    }
}
