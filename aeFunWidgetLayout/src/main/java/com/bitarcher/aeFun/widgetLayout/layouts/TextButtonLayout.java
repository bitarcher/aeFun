package com.bitarcher.aeFun.widgetLayout.layouts;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */


import com.bitarcher.aeFun.interfaces.geometry.EnumAlignStyle;
import com.bitarcher.aeFun.interfaces.gui.theme.context.ITextButtonContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.ITextButtonLayout;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IButtonSection;
import com.bitarcher.aeFun.interfaces.gui.widgets.ITextButton;

import org.andengine.entity.Entity;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.util.adt.align.HorizontalAlign;

/**
 * Created by michel on 09/03/15.
 */
public class TextButtonLayout extends ButtonLayout<ITextButtonContext> implements ITextButtonLayout, ITextButtonContext {
    ITextButton textButton;
    Entity textLayer;


    @Override
    public ITextButton getWidget() {
        return this.textButton;
    }

    @Override
    public void onPopulate() {
        super.onPopulate();

        // I have to do so since Text.setText has problem
        this.textLayer = new Entity();
        this.textButton.attachChild(this.textLayer);
    }

    @Override
    public void onInit() {
        this.setText(this.textButton.getTranslatedLabel());

        super.onInit();
    }


    @Override
    public void setAlignStyle(EnumAlignStyle dockStyle) {
        this.setText(this.textButton.getTranslatedLabel());
    }

    void setText(String label) {

        this.textLayer.detachChildren();

        if (!(label.isEmpty()))
        {
            float midWidth = this.textButton.getWidth() / 2;
            float midHeight = this.textButton.getHeight() / 2;

            Font font = this.textButton.getTheme().getWidgetSections().getTextButtonSection().getTextButtonFont();
            Text text = new Text(midWidth, midHeight, font, label, this.textButton.getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager(), DrawType.DYNAMIC);
            text.setAutoWrap(AutoWrap.WORDS);
            text.setAutoWrapWidth(this.textButton.getWidth() - this.getBorderSize() * 2);

            switch (this.textButton.getAlignStyle())
            {
                case Fill:
                case Bottom:
                case Top:
                case Center:
                    text.setHorizontalAlign(HorizontalAlign.CENTER);
                    break;
                case Left:
                    text.setHorizontalAlign(HorizontalAlign.LEFT);
                    break;
                case Right:
                    text.setHorizontalAlign(HorizontalAlign.RIGHT);
                    break;
            }

            this.textLayer.attachChild(text);
        }
    }



    public TextButtonLayout(ITextButton textButton)
    {
        super(textButton);
        this.textButton = textButton;
    }


    @Override
    public ITextButtonContext getContext() {
        return this;
    }

    @Override
    protected void doSizeAndPadding()
    {
        super.doSizeAndPadding();

        this.setText(this.textButton.getTranslatedLabel());
    }

    @Override
    protected IButtonSection getButtonSection() {
        return this.getWidget().getTheme().getWidgetSections().getTextButtonSection();
    }


    @Override
    public void setTranslatedLabel(String translatedLabel) {
        this.setText(translatedLabel);
    }

    @Override
    protected float getBorderSize() {
        return this.getWidget().getTheme().getWidgetSections().getTextButtonSection().getBorderSize();
    }
}
