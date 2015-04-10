package com.bitarcher.aeFun.widgetLayout.layouts.tools;

import com.bitarcher.aeFun.interfaces.basicioc.IClickableEntity;
import com.bitarcher.aeFun.interfaces.basicioc.IClickableListener;
import com.bitarcher.aeFun.interfaces.gui.theme.context.ICheckableContext;
import com.bitarcher.aeFun.interfaces.gui.theme.context.setter.ICheckedSetter;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.ICheckableLayout;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.ICheckableSection;
import com.bitarcher.aeFun.interfaces.gui.widgets.Tools.IEnableSetterEntity;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 01/04/15.
 */
public abstract class CheckClickableEntityBase<TCheckableContext extends ICheckableContext> extends Entity implements IClickableEntity, ICheckedSetter{
    ICheckableLayout<TCheckableContext> checkableLayout;
    ArrayList<IClickableListener> clickableListenerArrayList = new ArrayList<>();

    IEntity entity1;
    IEntity entity2;
    IEnableSetterEntity checkSymbol;

    protected boolean isTouchAreaRegistered = false;

    public CheckClickableEntityBase(ICheckableLayout<TCheckableContext> checkableLayout) {

        this.checkableLayout = checkableLayout;

        this.doSizeAndPadding();
    }

    @Override
    public void setChecked(boolean checked) {
        this.checkSymbol.setVisible(checked);
    }

    @Override
    public void addClickableListener(IClickableListener clickableListener) {
        this.clickableListenerArrayList.add(clickableListener);
    }

    @Override
    public void removeClickableListener(IClickableListener clickableListener) {
        this.clickableListenerArrayList.remove(clickableListener);
    }

    float getWidthOrHighByLayout()
    {
        return this.getCheckableWidgetSection().getSideLength();
    }

    public void setEnableColors()
    {
        boolean enabled =this.checkableLayout.getWidget().isEnabled();
        if(enabled) {
            this.entity1.setColor(this.checkableLayout.getWidget().getTheme().getWidgetSections().getCheckButtonSection().getClickableEntityColor1());
        }
        else

        {
            this.entity1.setColor(this.checkableLayout.getWidget().getTheme().getColorsSection().getDisabledColor1());
        }

        this.checkSymbol.setEnabled(enabled);
    }

    public void onPopulate()
    {
        float side = this.getWidthOrHighByLayout();
        float side2 = side / 2;
        this.entity1 = this.getNewEntity1(side2, side2, side, side);

                //new Rectangle(side2, side2, side, side, this.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());

        this.attachChild(this.entity1);

        float borderSize = 3;
        float reducedSide = side - 2 * borderSize;

        this.entity2 = this.getNewEntity2(side2, side2, reducedSide, reducedSide);

                //new Rectangle(side2, side2, reducedSide, reducedSide, this.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());
        Color sColor = this.checkableLayout.getWidget().getTheme().getWidgetSections().getCheckButtonSection().getClickableEntityColor2();
        this.entity2.setColor(sColor);
        this.attachChild(this.entity2);

        this.checkSymbol = this.getNewCheckSymbol(side2, side2, reducedSide, reducedSide);
        this.setChecked(((com.bitarcher.aeFun.interfaces.basicioc.ICheckable) this.checkableLayout.getWidget()).isChecked());
        this.attachChild(this.checkSymbol);
    }

    protected abstract IEntity getNewEntity1(float x, float y, float width, float height);
    protected abstract IEntity getNewEntity2(float x, float y, float width, float height);

    protected abstract IEnableSetterEntity getNewCheckSymbol(float x, float y, float width, float height);

    @Override
    public void onAttached() {
        super.onAttached();

        if(!this.isTouchAreaRegistered)
        {
            Scene scene = this.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getScene();

            scene.registerTouchArea(this);
            this.isTouchAreaRegistered = true;
        }
    }

    @Override
    public void onDetached() {
        super.onDetached();

        if(this.isTouchAreaRegistered)
        {
            Scene scene = this.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getScene();

            scene.unregisterTouchArea(this);

            this.isTouchAreaRegistered = false;
        }

        this.resetMousePressed();
    }

    void resetMousePressed()
    {
        // nothing to do
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

        boolean retval = false;

        if(pSceneTouchEvent.isActionDown()) {
            if (this.checkableLayout.getWidget().isEnabled()) {
                retval = true;

                for (IClickableListener clickableListener : this.clickableListenerArrayList) {
                    clickableListener.onClick(this);
                }
            }
        }

        return retval;
    }


    public void doSizeAndPadding() {

        float side = this.getWidthOrHighByLayout();
        this.setPosition(
                side + 2,
                this.checkableLayout.getWidget().getHeight() / 2);



        this.setSize(side, side);
    }


    protected abstract ICheckableSection getCheckableWidgetSection();
}

