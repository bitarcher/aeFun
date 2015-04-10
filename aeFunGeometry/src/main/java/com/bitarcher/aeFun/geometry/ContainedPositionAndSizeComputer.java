package com.bitarcher.aeFun.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.EnumAlignStyle;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.geometry.ISize;

/**
 * Created by michel on 26/03/15.
 */
public abstract class ContainedPositionAndSizeComputer {
    protected IPositionAndSizeOwner computeByContainedWidthAndHeight(ISize container, EnumAlignStyle alignStyle, float layoutBorder,
        float containedWidth, float containedHeight) {

        float containedX;
        float containedY;

        switch (alignStyle)
        {
            case  Fill:
                containedWidth = container.getWidth() - 2 * layoutBorder;
                containedHeight = container.getHeight()  - 2 * layoutBorder;
                containedX = layoutBorder;
                containedY = layoutBorder;
                break;
            case  Bottom:
                containedY = layoutBorder + containedHeight / 2;
                containedX = container.getWidth() / 2;
                break;
            case  Top:
                containedY = container.getHeight() - layoutBorder - containedHeight / 2;
                containedX = container.getWidth() / 2;
                break;
            case  Left:
                containedX = layoutBorder + containedWidth / 2;
                containedY = container.getHeight() / 2;
                break;
            case  Right:
                containedX = container.getWidth() - layoutBorder - containedWidth / 2;
                containedY = container.getHeight() / 2;
                break;
            case  Center:
                containedX = container.getWidth() / 2;
                containedY = container.getHeight() / 2;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        PositionAndSizeOwner retval = new PositionAndSizeOwner(containedX, containedY, containedWidth, containedHeight);

        return retval;
    }
}
