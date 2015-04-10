package com.bitarcher.aeFun.interfaces.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */



/**
 * Created by michel on 25/03/15.
 */
public interface IContainedPositionAndSizeComputerByContainedSize {
    IPositionAndSizeOwner compute(ISize container, EnumAlignStyle alignStyle, ISizeOwner containedAsSizeOwner, float layoutBorder);
    IPositionAndSizeOwner compute(ISize container, EnumAlignStyle alignStyle, ISize containedSize, float layoutBorder);
    IPositionAndSizeOwner compute(ISize container, EnumAlignStyle alignStyle, ISize containedSize);
    IPositionAndSizeOwner compute(ISize container, ISize containedSize);
}
