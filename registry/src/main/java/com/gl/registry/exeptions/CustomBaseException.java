package com.gl.registry.exeptions;

import lombok.Getter;

@Getter
public abstract class CustomBaseException extends RuntimeException {

    private Class clazz;
    private String fieldName;
    private Object fieldValue;

    protected CustomBaseException(Class clazz, String fieldName, Object fieldValue, String exceptionMsg){
        super(String.format(exceptionMsg, clazz.getSimpleName(), fieldName, fieldValue));

        this.clazz = clazz;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
