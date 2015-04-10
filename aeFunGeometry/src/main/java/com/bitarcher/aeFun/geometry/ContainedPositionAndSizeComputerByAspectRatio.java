package com.bitarcher.aeFun.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.EnumAlignStyle;
import com.bitarcher.aeFun.interfaces.geometry.IAspectRatioOwner;
import com.bitarcher.aeFun.interfaces.geometry.IContainedPositionAndSizeComputerByAspectRatio;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.geometry.ISize;


/**
 * Created by michel on 25/03/15.
 */
public class ContainedPositionAndSizeComputerByAspectRatio extends ContainedPositionAndSizeComputer implements IContainedPositionAndSizeComputerByAspectRatio {
    @Override
    public IPositionAndSizeOwner compute(ISize container, EnumAlignStyle alignStyle, IAspectRatioOwner aspectRatioOwner, float layoutBorder) {
        return this.compute(container, alignStyle, aspectRatioOwner.getAspectRatio(), layoutBorder);
    }

    @Override
    public IPositionAndSizeOwner compute(ISize container, EnumAlignStyle alignStyle, float containedAspectRatio, float layoutBorder) {

        float containedWidth;
        float containedHeight;

        float containerAspectRatio = container.getWidth() / container.getHeight();

        if(containedAspectRatio > containerAspectRatio)
        {
            // consider width is bigger than height
            containedWidth = container.getWidth() - 2 * layoutBorder;
            containedHeight = containedWidth / containedAspectRatio;
        }
        else
        {
            containedHeight = container.getHeight()  - 2 * layoutBorder;
            containedWidth = containedHeight * containedAspectRatio;
        }

        IPositionAndSizeOwner retval = this.computeByContainedWidthAndHeight(container, alignStyle, layoutBorder, containedWidth, containedHeight);

        return retval;
    }

    @Override
    public IPositionAndSizeOwner compute(ISize container, EnumAlignStyle alignStyle, float aspectRatio) {
        return this.compute(container, alignStyle, aspectRatio, 0);
    }

    @Override
    public IPositionAndSizeOwner compute(ISize container, float aspectRatio) {
        return this.compute(container, EnumAlignStyle.Center, aspectRatio);
    }
}
