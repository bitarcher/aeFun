package com.bitarcher.aeFun.widgetLayout.layouts;

import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IButtonContext;
import com.bitarcher.aeFun.interfaces.gui.theme.context.setter.EnumMouseEffect;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.IButtonLayout;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IButtonSection;
import com.bitarcher.aeFun.interfaces.gui.widgets.IButton;

import org.andengine.entity.primitive.Gradient;
import org.andengine.entity.primitive.Rectangle;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 09/03/15.
 */



public abstract class ButtonLayout<TButtonContext extends IButtonContext>  implements IButtonLayout<TButtonContext>, IButtonContext {
    IButton button;
    Rectangle backRectangle;
    Gradient gradient;



    @Override
    public IButton getWidget() {
        return this.button;
    }

    @Override
    public void onPopulate() {
        this.backRectangle = new Rectangle(0, 0, 10, 10, this.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());
        this.backRectangle.setColor(this.getWidget().getTheme().getWidgetSections().getTextButtonSection().getBorderColor());
        this.button.attachChild(this.backRectangle);


        this.gradient = new Gradient(0, 0, 10, 10, this.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());
        this.button.attachChild(this.gradient);
    }

    @Override
    public void onInit() {
        this.doSizeAndPadding();
        this.doGradientColor();
    }

    @Override
    public void pushResourceRequirements() {
    }

    @Override
    public void popResourceRequirements() {
    }

    public ButtonLayout(IButton button) {
        this.button = button;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.doGradientColor();
    }

    protected abstract IButtonSection getButtonSection();

    @Override
    public void setMouseEffect(EnumMouseEffect mouseEffect) {
        this.doGradientColor();
    }

    @Override
    public void setPadding(float padding) {
        this.doSizeAndPadding();
    }

    @Override
    public void setSize(ISize size) {
        this.doSizeAndPadding();
    }

    protected abstract float getBorderSize();

    protected void doSizeAndPadding()
    {
        float tw = this.button.getWidth();
        float th = this.button.getHeight();

        float midWidth = tw / 2;
        float midHeight = th / 2;

        float wmp = tw - 2 * this.button.getPadding();
        float hmp = th - 2 * this.button.getPadding();

        float border = this.getBorderSize();
        float wb = wmp - 2 * border;
        float hb = hmp - 2 * border;


        this.backRectangle.setSize(wmp, hmp);
        this.backRectangle.setPosition(midWidth, midHeight);

        this.gradient.setSize(wb, hb);
        this.gradient.setPosition(midWidth, midHeight);

    }

    protected void doGradientColor()
    {
        IButtonSection buttonSection = this.getButtonSection();

        if(this.button.isEnabled()) {
            if(this.button.isMousePressed())
            {
                gradient.setFromColor(buttonSection.getActivatedColor1());
                gradient.setToColor(buttonSection.getActivatedColor2());
            }
            else {
                gradient.setFromColor(buttonSection.getNormalColor1());
                gradient.setToColor(buttonSection.getNormalColor2());
            }
        }
        else
        {
            gradient.setFromColor(buttonSection.getDisabledColor1());
            gradient.setToColor(buttonSection.getDisabledColor2());
        }
    }

}

