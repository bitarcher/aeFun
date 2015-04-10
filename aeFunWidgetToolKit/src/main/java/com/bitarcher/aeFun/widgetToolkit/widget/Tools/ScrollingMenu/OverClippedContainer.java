/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.widgetToolkit.widget.Tools.ScrollingMenu;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.mvc.IImageAndLabeled;


import org.andengine.entity.clip.ClipEntity;
import org.andengine.entity.scene.Scene;


import java.util.ArrayList;


public class OverClippedContainer extends ClipEntity {

    ArrayList<IButtonListener> buttonListenerArrayList = new ArrayList<>();
    InnerContainer innerContainer;

    public OverClippedContainer(ITheme theme, Scene scene, float pX, float pY, float pWidth, float pHeight, ArrayList<IImageAndLabeled> imagedAndLabeledList) {
        super(pX, pY, pWidth, pHeight);

        this.innerContainer = new InnerContainer(theme, scene, pX, pY, pHeight, imagedAndLabeledList);

        this.innerContainer.addButtonListener(new IButtonListener() {
            @Override
            public void onClicked(IImageAndLabeled imagedAndLabeled) {
                for(IButtonListener buttonListener : buttonListenerArrayList)
                {
                    buttonListener.onClicked(imagedAndLabeled);
                }
            }
        });


        this.attachChild(this.innerContainer);
    }

    public void addButtonListener(IButtonListener buttonListener) {
        this.buttonListenerArrayList.add(buttonListener);

    }

    public void removeButtonListener(IButtonListener buttonListener) {
        this.buttonListenerArrayList.remove(buttonListener);
    }

}

