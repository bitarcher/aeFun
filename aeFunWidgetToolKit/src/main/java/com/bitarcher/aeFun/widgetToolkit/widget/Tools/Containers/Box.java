package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.INoneContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.EnumOrientation;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.IBox;

import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.ISpaceUsage;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.IVBox;


import org.andengine.util.IMatcher;
import org.andengine.util.adt.list.SmartList;

/**
 * Created by michel on 28/02/15.
 */
public class Box extends Container<INoneContext> implements IBox {

    boolean shouldFixedSpaceUsageBeResizedOnResize = false;

    SmartList<WidgetAndSpaceUsageTupleForBox> widgetAndSpaceUsageTupleForBoxes = new SmartList<>();

    protected Box(ITheme theme, float pX, float pY, float pWidth, float pHeight) {
        super(theme, pX, pY, pWidth, pHeight);
    }

    @Override
    protected void doAttachWidget(IWidget widget) {
        this.packStart(widget, new PercentSpaceUsage(0, 100f));
    }

    @Override
    public EnumOrientation getOrientation() {
        EnumOrientation retval = EnumOrientation.Horizontal;

        if(this instanceof IVBox)
        {
            retval = EnumOrientation.Vertical;
        }

        return retval;
    }

    @Override
    public boolean isShouldFixedSpaceUsageBeResizedOnResize() {
        return shouldFixedSpaceUsageBeResizedOnResize;
    }

    @Override
    public void setShouldFixedSpaceUsageBeResizedOnResize(boolean shouldFixedSpaceUsageBeResizedOnResize) {
        this.shouldFixedSpaceUsageBeResizedOnResize = shouldFixedSpaceUsageBeResizedOnResize;
    }

    @Override
    public void packStart(IWidget widget, ISpaceUsage spaceUsage) {
        if(this.getOrientation() == EnumOrientation.Horizontal) {
            this.widgetAndSpaceUsageTupleForBoxes.addLast(new WidgetAndSpaceUsageTupleForBox(widget, spaceUsage));
        }
        else
        {
            this.widgetAndSpaceUsageTupleForBoxes.addFirst(new WidgetAndSpaceUsageTupleForBox(widget, spaceUsage));
        }
        this.entityAttachChild(widget);

        this.recomputeWidgetsSizeAndPositions();
    }

    @Override
    public void packEnd(IWidget widget, ISpaceUsage spaceUsage) {
        if(this.getOrientation() == EnumOrientation.Horizontal) {
            this.widgetAndSpaceUsageTupleForBoxes.addFirst(new WidgetAndSpaceUsageTupleForBox(widget, spaceUsage));
        }
        else {
            this.widgetAndSpaceUsageTupleForBoxes.addLast(new WidgetAndSpaceUsageTupleForBox(widget, spaceUsage));
        }
        this.entityAttachChild(widget);

        this.recomputeWidgetsSizeAndPositions();
    }

    @Override
    protected void recomputeAllContainedPositionAndSize() {
        this.recomputeWidgetsSizeAndPositions();
    }

    @Override
    public void doDetachChild(IWidget widget) {
        WidgetAndSpaceUsageTupleForBox foundTuple = null;
        final IWidget widget2 = widget;
        foundTuple = this.widgetAndSpaceUsageTupleForBoxes.get(new IMatcher<WidgetAndSpaceUsageTupleForBox>() {
            @Override
            public boolean matches(WidgetAndSpaceUsageTupleForBox pObject) {
                return pObject.getWidget() == widget2;
            }
        });


        if(foundTuple != null)
        {
            this.widgetAndSpaceUsageTupleForBoxes.remove(foundTuple);
            this.entityDetachChild(widget);
            this.recomputeWidgetsSizeAndPositions();
        }
    }

    public void resetOriginalWidthAndHeight(float originalWidth, float originalHeight)
    {
        this.setOriginalWidth(originalWidth);
        this.setOriginalHeight(originalHeight);
    }

    void recomputeWidgetsSizeAndPositions()
    {
        float availableSpace = 0;

        if(this.getOrientation() == EnumOrientation.Horizontal)
        {
            availableSpace = this.getWidth();
        }
        else
        {
            availableSpace =  this.getHeight();
        }

        ScalarComputerBySpaceUsage scalarComputerBySpaceUsage = new ScalarComputerBySpaceUsage();

        scalarComputerBySpaceUsage.compute(this.widgetAndSpaceUsageTupleForBoxes, availableSpace, this.getPadding(), this.isShouldFixedSpaceUsageBeResizedOnResize());


        // set position and size

        float startX = this.getPadding();
        float startY = this.getPadding();

        float currentX = startX;
        float currentY = startY;

        if(this.getOrientation() == EnumOrientation.Horizontal)
        {
            float ch = this.getHeight() - 2 * this.getPadding();
            float cy = this.getHeight() / 2;

            for (WidgetAndSpaceUsageTupleForBox tuple:this.widgetAndSpaceUsageTupleForBoxes) {
                float cw = tuple.getScalarWithoutTwoMargin();
                float cx = currentX + tuple.getScalar()/2;


                tuple.getWidget().setPosition(cx, cy);
                tuple.getWidget().setSize(cw, ch);


                currentX += tuple.getScalar();
            }
        }
        else
        {
            // vertical

            float cw = this.getWidth() - 2 * this.getPadding();
            float cx = this.getWidth() / 2;

            for (WidgetAndSpaceUsageTupleForBox tuple:this.widgetAndSpaceUsageTupleForBoxes) {
                float ch = tuple.getScalarWithoutTwoMargin();
                float cy = currentY + tuple.getScalar() /2;


                tuple.getWidget().setPosition(cx, cy);
                tuple.getWidget().setSize(cw, ch);

                currentY += tuple.getScalar();
            }
        }

        // raise recomputed

        this.raiseChildrenPositionRecomputed();
    }

    @Override
    protected void onSizeChanged() {
        super.onSizeChanged();

        this.recomputeWidgetsSizeAndPositions();
    }

    @Override
    protected void onPaddingChanged() {
        super.onPaddingChanged();

        this.recomputeWidgetsSizeAndPositions();
    }



}

