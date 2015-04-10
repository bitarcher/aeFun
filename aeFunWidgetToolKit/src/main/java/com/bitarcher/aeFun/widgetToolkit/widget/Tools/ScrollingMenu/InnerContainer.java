/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.widgetToolkit.widget.Tools.ScrollingMenu;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.mvc.IImageAndLabeled;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;

import java.util.ArrayList;

/**
 * Created by michel on 27/02/15.
 */
class InnerContainer extends Entity {

    ArrayList<IButtonListener> buttonListenerArrayList = new ArrayList<>();
    static final int INITIAL_LEFT_MARGIN = 128;
    static final int PADDING = 10;

    public InnerContainer(ITheme theme, Scene scene, float pX, float pY, float pHeight, ArrayList<IImageAndLabeled> imagedAndLabeledList) {
        super(pX, pY, imagedAndLabeledList.size() * (pHeight + PADDING), pHeight);

        int listSize = imagedAndLabeledList.size();
        int iItem = 1;
        int spriteX = PADDING;

        for (int x = 0; x < listSize; x++) {
            // On Touch, save the clicked item in case it's a click and not a
            // scroll.
            final int itemToLoad = iItem;
            IImageAndLabeled imagedAndLabeled = imagedAndLabeledList.get(x);



            SCButton scButton = new SCButton(scene, this.INITIAL_LEFT_MARGIN + this.getHeight() + spriteX, this.getHeight(), this.getHeight(), this.getHeight(), imagedAndLabeled, theme);

            scButton.addButtonListener(new IButtonListener() {
                @Override
                public void onClicked(IImageAndLabeled imagedAndLabeled) {
                    for(IButtonListener buttonListener : buttonListenerArrayList)
                    {
                        buttonListener.onClicked(imagedAndLabeled);
                    }
                }
            });



            iItem++;
            this.attachChild(scButton);

            spriteX += PADDING + scButton.getWidth();
        }
    }

    public void addButtonListener(IButtonListener buttonListener) {
        this.buttonListenerArrayList.add(buttonListener);

    }

    public void removeButtonListener(IButtonListener buttonListener) {
        this.buttonListenerArrayList.remove(buttonListener);
    }


}
