package com.bitarcher.aeFun.widgetToolkit.widget;


import com.bitarcher.aeFun.interfaces.geometry.EnumAlignStyle;
import com.bitarcher.aeFun.interfaces.geometry.IAlignedListener;
import com.bitarcher.aeFun.interfaces.gui.theme.ENoLayoutFound;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IImageContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.IImage;
import com.bitarcher.aeFun.interfaces.mvc.IImagedListener;


import java.util.ArrayList;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**-
 * Created by michel on 22/01/15.
 */
public final class Image extends Widget<IImageContext> implements IImage {
    protected com.bitarcher.aeFun.interfaces.mvc.IImage currentImage;
    ArrayList<IImagedListener> imagedListenerArrayList = new ArrayList<>();
    EnumAlignStyle alignStyle = EnumAlignStyle.Center;
    ArrayList<IAlignedListener> alignedListenerArrayList = new ArrayList<>();

    @Override
    public void setAlignStyle(EnumAlignStyle alignStyle) {
        this.alignStyle = alignStyle;

        this.onAlignStyleChanged();

        for(IAlignedListener dockStyledListener : this.alignedListenerArrayList)
        {
            dockStyledListener.onAlignStyleChanged(this, this.alignStyle);
        }
    }

    @Override
    public void dispose() {
        super.dispose();

        this.imagedListenerArrayList.clear();
        this.alignedListenerArrayList.clear();
    }

    protected void onAlignStyleChanged()
    {
        if(this.layout != null)
        {
            this.layout.getContext().setAlignStyle(this.alignStyle);
        }
    }

    @Override
    public void addAlignStyledListener(IAlignedListener alignedListener) {
        this.alignedListenerArrayList.add(alignedListener);
    }

    @Override
    public void removeAlignStyledListener(IAlignedListener alignedListener) {
        this.alignedListenerArrayList.remove(alignedListener);
    }

    @Override
    public EnumAlignStyle getAlignStyle() {
        return this.alignStyle;
    }

    public Image(ITheme theme, float pX, float pY, float pWidth, float pHeight, com.bitarcher.aeFun.interfaces.mvc.IImage image) {
        super(theme, pX, pY, pWidth, pHeight);

        this.setImage(image, false);

        if(this.getLayout() != null)
        {
            this.getLayout().onInit();
        }
        else
        {
            throw new ENoLayoutFound(this);
        }
    }

    void setImage(com.bitarcher.aeFun.interfaces.mvc.IImage image, boolean shouldRaise)
    {
        if(this.currentImage != null)
        {
            this.getTheme().getThemeManager().getResourceManager().popRequirement(image.getTextureSetResourceInfo());
        }

        this.currentImage = image;

        if(this.currentImage != null)
        {
            this.getTheme().getThemeManager().getResourceManager().pushRequirement(image.getTextureSetResourceInfo());
        }

        if(shouldRaise)
        {
            for(IImagedListener imagedListener : this.imagedListenerArrayList)
            {
                imagedListener.onImageChanged(this);
            }

            this.onImageChanged(image);
        }
    }



    @Override
    public void addImagedListener(IImagedListener imagedListener) {
        this.imagedListenerArrayList.add(imagedListener);
    }

    @Override
    public void removeImagedListener(IImagedListener imagedListener) {
        this.imagedListenerArrayList.remove(imagedListener);
    }

    @Override
    public void setImage(com.bitarcher.aeFun.interfaces.mvc.IImage image) {

        this.setImage(image, true);
    }

    protected void onImageChanged(com.bitarcher.aeFun.interfaces.mvc.IImage image)
    {
        if(this.getLayout() != null)
        {
            this.getLayout().getContext().setImage(this.getImage());
        }

    }

    @Override
    public com.bitarcher.aeFun.interfaces.mvc.IImage getImage() {
        return this.currentImage;
    }
    
}

