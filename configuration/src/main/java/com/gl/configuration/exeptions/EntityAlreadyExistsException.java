package com.gl.configuration.exeptions;

import lombok.Getter;

@Getter
public class EntityAlreadyExistsException extends CustomBaseException {

    private static final String EXCEPTION_MSG = "%s already exists with %s: %s";

    public EntityAlreadyExistsException(Class clazz, String fieldName, Object value) {
        super(clazz, fieldName, value, EXCEPTION_MSG);
    }
 }
