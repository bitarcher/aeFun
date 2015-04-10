package com.bitarcher.aeFun.widgetLayout.layouts;

import com.bitarcher.aeFun.interfaces.geometry.EnumAlignStyle;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IImageContext;

import com.bitarcher.aeFun.interfaces.gui.theme.layout.IImageLayout;
import com.bitarcher.aeFun.interfaces.gui.widgets.IImage;
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
public class ImageLayout implements IImageLayout, IImageContext {
    IImage image;
    Entity imageLayer;

    @Override
    public void setEnabled(boolean enabled) {
        // nothing to do
    }

    @Override
    public void pushResourceRequirements() {

    }

    @Override
    public void popResourceRequirements() {

    }

    @Override
    public IImage getWidget() {
        return this.image;
    }

    @Override
    public void onPopulate() {
        this.imageLayer = new Entity();
        this.image.attachChild(this.imageLayer);
    }

    @Override
    public void onInit() {
        this._setImage(this.image.getImage());
    }

    @Override
    public void setAlignStyle(EnumAlignStyle dockStyle) {
        this._setImage(this.image.getImage());
    }

    void _setImage(com.bitarcher.aeFun.interfaces.mvc.IImage image) {

        this.imageLayer.detachChildren();

        if (image != null)
        {
            ContainedPositionAndSizeComputerByAspectRatio containedPositionAndSizeComputerByAspectRatio = new ContainedPositionAndSizeComputerByAspectRatio();

            IPositionAndSizeOwner spritePositionAndSize = containedPositionAndSizeComputerByAspectRatio.compute(this.image, this.image.getAlignStyle(), image, 0);

            ITextureRegion textureRegion = this.getWidget().getTheme().getThemeManager().getResourceManager().getTextureRegionFromTextureSetByName(image.getTextureSetResourceInfo(), image.getTextureName());

            Sprite sprite = new Sprite(
                    spritePositionAndSize.getPosition().getX(), spritePositionAndSize.getPosition().getY(),
                    spritePositionAndSize.getSize().getWidth(), spritePositionAndSize.getSize().getHeight(),
                    textureRegion,
                    this.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());

            this.imageLayer.attachChild(sprite);
        }
    }



    public ImageLayout(IImage image)
    {
        this.image = image;
    }


    @Override
    public IImageContext getContext() {
        return this;
    }


    protected void doSizeAndPadding()
    {

        //this.imageLayer.setSize(this.getWidget().getWidth(), this.getWidget().getHeight());
        //this.imageLayer.setPosition(this.getWidget().getWidth() / 2.0f, this.getWidget().getHeight() / 2.0f);

        this._setImage(this.image.getImage());
    }


    public void setImage(com.bitarcher.aeFun.interfaces.mvc.IImage image) {
        this._setImage(image);
    }


    @Override
    public void setPadding(float padding) {
        this.doSizeAndPadding();
    }

    @Override
    public void setSize(ISize size) {
        this.doSizeAndPadding();
    }

}
