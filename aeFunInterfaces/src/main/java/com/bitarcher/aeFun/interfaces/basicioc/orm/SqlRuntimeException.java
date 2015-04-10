/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.interfaces.basicioc.orm;

/**
 * Created by michel on 14/02/15.
 */
public class SqlRuntimeException extends RuntimeException {
    Exception exception;

    public Exception getException() {
        return exception;
    }

    public SqlRuntimeException(Exception e) {
        this.exception = e;
    }
}

