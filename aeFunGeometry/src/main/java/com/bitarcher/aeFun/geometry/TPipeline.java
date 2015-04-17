package com.bitarcher.aeFun.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.ITFunction;
import com.bitarcher.aeFun.interfaces.geometry.ITPipeline;

import org.andengine.util.adt.list.SmartList;



/**
 * Created by michel on 17/04/15.
 */
public class TPipeline<Type> implements ITPipeline<Type> {
    SmartList<ITFunction<Type, Type>> functionsList = new SmartList<>();


    @Override
    public void prependFunction(ITFunction<Type, Type> function) {
        this.functionsList.addFirst(function);
    }

    @Override
    public void appendFunction(ITFunction<Type, Type> function) {
        this.functionsList.addLast(function);
    }

    @Override
    public Type getYByX(Type xValue) {
        Type retval = xValue;

        for(ITFunction<Type, Type> function : this.functionsList)
        {
            retval = function.getYByX(retval);
        }

        return retval;
    }
}
