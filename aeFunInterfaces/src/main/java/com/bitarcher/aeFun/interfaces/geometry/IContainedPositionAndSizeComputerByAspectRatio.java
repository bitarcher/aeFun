package com.bitarcher.aeFun.interfaces.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */



/**
 * Created by michel on 25/03/15.
 */
public interface IContainedPositionAndSizeComputerByAspectRatio {
    IPositionAndSizeOwner compute(ISize container, EnumAlignStyle dockStyle, IAspectRatioOwner aspectRatioOwner, float layoutBorder);
    IPositionAndSizeOwner compute(ISize container, EnumAlignStyle dockStyle, float aspectRatio, float layoutBorder);
    IPositionAndSizeOwner compute(ISize container, EnumAlignStyle dockStyle, float aspectRatio);
    IPositionAndSizeOwner compute(ISize container, float aspectRatio);
}

