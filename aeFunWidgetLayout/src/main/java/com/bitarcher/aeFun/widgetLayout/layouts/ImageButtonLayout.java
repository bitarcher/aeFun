package com.bitarcher.aeFun.widgetLayout.layouts;

import com.bitarcher.aeFun.interfaces.geometry.EnumAlignStyle;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IImageButtonContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.IImageButtonLayout;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IButtonSection;
import com.bitarcher.aeFun.interfaces.gui.widgets.IImageButton;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.geometry.ContainedPositionAndSizeComputerByAspectRatio;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;


/*
 * Copyright (c) 2015. 
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 09/03/15.
 */
public class ImageButtonLayout extends ButtonLayout<IImageButtonContext> implements IImageButtonLayout, IImageButtonContext {
    IImageButton imageButton;
    Entity imageLayer;


    @Override
    public IImageButton getWidget() {
        return this.imageButton;
    }

    @Override
    public void onPopulate() {
        super.onPopulate();


        this.imageLayer = new Entity();
        this.imageButton.attachChild(this.imageLayer);
    }

    @Override
    public void onInit() {
        this._setImage(this.imageButton.getImage());

        super.onInit();
    }

    @Override
    public void setAlignStyle(EnumAlignStyle dockStyle) {
        this._setImage(this.imageButton.getImage());
    }


    void _setImage(IImage image) {

        this.imageLayer.detachChildren();

        if (image != null)
        {
            ContainedPositionAndSizeComputerByAspectRatio containedPositionAndSizeComputerByAspectRatio = new ContainedPositionAndSizeComputerByAspectRatio();

            IPositionAndSizeOwner spritePositionAndSize = containedPositionAndSizeComputerByAspectRatio.compute(
                    this.imageButton, this.imageButton.getAlignStyle(),
                    image, this.getButtonSection().getBorderSize());

            ITextureRegion textureRegion = this.getWidget().getTheme().getThemeManager().getResourceManager().getTextureRegionFromTextureSetByName(image.getTextureSetResourceInfo(), image.getTextureName());

            Sprite sprite = new Sprite(
                    spritePositionAndSize.getPosition().getX(), spritePositionAndSize.getPosition().getY(),
                    spritePositionAndSize.getSize().getWidth(), spritePositionAndSize.getSize().getHeight(),
                    textureRegion,
                    this.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());


            this.imageLayer.attachChild(sprite);
        }
    }



    public ImageButtonLayout(IImageButton imageButton)
    {
        super(imageButton);
        this.imageButton = imageButton;
    }


    @Override
    public IImageButtonContext getContext() {
        return this;
    }

    @Override
    protected void doSizeAndPadding()
    {
        super.doSizeAndPadding();

        //this.imageLayer.setSize(this.getWidget().getWidth(), this.getWidget().getHeight());
        //this.imageLayer.setPosition(this.getWidget().getWidth() / 2.0f, this.getWidget().getHeight() / 2.0f);

        this._setImage(this.imageButton.getImage());
    }

    @Override
    protected IButtonSection getButtonSection() {
        return this.getWidget().getTheme().getWidgetSections().getImageButtonSection();
    }

    @Override
    public void setImage(IImage image) {
        this._setImage(image);
    }

    @Override
    protected float getBorderSize() {
        return this.getWidget().getTheme().getWidgetSections().getImageButtonSection().getBorderSize();
    }
}
