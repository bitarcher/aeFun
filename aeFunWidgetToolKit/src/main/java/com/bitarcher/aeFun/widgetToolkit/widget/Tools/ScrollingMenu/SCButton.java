/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.widgetToolkit.widget.Tools.ScrollingMenu;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;

import com.bitarcher.aeFun.interfaces.mvc.IImageAndLabeled;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;


import java.util.ArrayList;



class SCButton extends Sprite {


    private IImageAndLabeled imagedAndLabeled;
    private ArrayList<IButtonListener> buttonListenerArrayList = new ArrayList<>();

    public IImageAndLabeled getImagedAndLabeled() {
        return imagedAndLabeled;
    }


    public SCButton(Scene scene, float pX, float pY, float pWidth, float pHeight, IImageAndLabeled imagedAndLabeled, ITheme theme) {
        super(pX, pY, pWidth, pHeight,
                theme.getThemeManager().getResourceManager().getTextureRegionFromTextureSetByName(imagedAndLabeled.getTextureSetResourceInfo(), imagedAndLabeled.getTextureName()),
                theme.getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());

        this.imagedAndLabeled = imagedAndLabeled;
        scene.registerTouchArea(this);
    }

    public void addButtonListener(IButtonListener buttonListener) {
        this.buttonListenerArrayList.add(buttonListener);

    }

    public void removeButtonListener(IButtonListener buttonListener) {
        this.buttonListenerArrayList.remove(buttonListener);
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

        for(IButtonListener buttonListener:this.buttonListenerArrayList)
        {
            buttonListener.onClicked(this.imagedAndLabeled);
        }

        return true;
    }
}

