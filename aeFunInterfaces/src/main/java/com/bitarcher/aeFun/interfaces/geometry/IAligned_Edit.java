package com.bitarcher.aeFun.interfaces.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 25/03/15.
 */
public interface IAligned_Edit extends IAligned {
    void setAlignStyle(EnumAlignStyle alignStyle);

    void addAlignStyledListener(IAlignedListener alignedListener);
    void removeAlignStyledListener(IAlignedListener alignedListener);
}
