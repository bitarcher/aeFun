package com.bitarcher.aeFun.geometry;

import com.bitarcher.aeFun.interfaces.geometry.EnumAlignStyle;
import com.bitarcher.aeFun.interfaces.geometry.IContainedPositionAndSizeComputerByContainedSize;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.geometry.ISizeOwner;


/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 25/03/15.
 */
public class ContainedPositionAndSizeComputerByContainedSize extends ContainedPositionAndSizeComputer implements IContainedPositionAndSizeComputerByContainedSize {

    @Override
    public IPositionAndSizeOwner compute(ISize container, EnumAlignStyle alignStyle, ISizeOwner containedAsSizeOwner, float layoutBorder) {
        return this.compute(container, alignStyle, containedAsSizeOwner.getSize(), layoutBorder);
    }

    @Override
    public IPositionAndSizeOwner compute(ISize container, EnumAlignStyle alignStyle, ISize containedSize, float layoutBorder) {
        return this.computeByContainedWidthAndHeight(container, alignStyle, layoutBorder, containedSize.getWidth(), containedSize.getHeight());
    }

    @Override
    public IPositionAndSizeOwner compute(ISize container, EnumAlignStyle alignStyle, ISize containedSize) {
        return this.compute(container, alignStyle, containedSize, 0);
    }

    @Override
    public IPositionAndSizeOwner compute(ISize container, ISize containedSize) {
        return this.compute(container, EnumAlignStyle.Center, containedSize);
    }
}
