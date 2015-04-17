package com.bitarcher.aeFun.interfaces.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 17/04/15.
 */
public interface ITPipeline<Type> extends ITFunction<Type, Type> {
    void prependFunction(ITFunction<Type, Type> function);
    void appendFunction(ITFunction<Type, Type> function);
}
