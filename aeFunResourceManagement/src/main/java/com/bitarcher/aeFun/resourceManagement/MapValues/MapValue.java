package com.bitarcher.aeFun.resourceManagement.MapValues;

/**
 * Created by michel on 08/01/15.
 */
public abstract class MapValue {
    int _numOfUse = 0;

    public MapValue() {
    }

    public void ref()
    {
        this._numOfUse++;
    }

    public void unref()
    {
        this._numOfUse--;
    }

    public boolean isUsed()
    {
        return this._numOfUse > 0;
    }

    public abstract void clean();
}
