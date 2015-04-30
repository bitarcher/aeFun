/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.drawables.animatedMeshed.nature.clouds;

import com.bitarcher.aeFun.interfaces.basicioc.INeedToSortChildrenListener;
import com.bitarcher.aeFun.interfaces.basicioc.INeedToSortChildrenRequirer;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.math.MathUtils;

import java.util.ArrayList;

/**
 * Created by michel on 05/02/15.
 */
public class CloudSprite extends Sprite implements INeedToSortChildrenRequirer{
    float mainMenuWidth;
    float mainMenuScaleX;
    float mainMenuHeight;
    float mainMenuScaleY;
    IResourceManager resourceManager;

    ArrayList<INeedToSortChildrenListener> needToSortChildrenListeners = new ArrayList<>();

    private float XSpeed = MathUtils.random(0.2f, 2f);
    private boolean initialized = false;

    public CloudSprite(float mainMenuWidth, float mainMenuScaleX,
                       float mainMenuHeight,float mainMenuScaleY,
                       IResourceManager resourceManager, ITextureRegion cloudTextureRegion)
    {
        super(
                MathUtils.random(-(mainMenuWidth * mainMenuScaleX) / 2, resourceManager.getCameraWidth() + (mainMenuWidth * mainMenuScaleX) / 2),
                CloudSprite.getNewYRandom(mainMenuHeight, mainMenuScaleY, resourceManager),
                cloudTextureRegion, resourceManager.getEngine().getVertexBufferObjectManager());

        this.mainMenuWidth = mainMenuWidth;
        this.mainMenuScaleX = mainMenuScaleX;
        this.mainMenuHeight = mainMenuHeight;
        this.mainMenuScaleY = mainMenuScaleY;
        this.resourceManager = resourceManager;

        this.setVisible(false);
    }

    static float getNewYRandom(float mainMenuHeight,float mainMenuScaleY,
                               IResourceManager resourceManager)
    {
        float retval = MathUtils.random(-(mainMenuHeight * mainMenuScaleY)/2,
                resourceManager.getCameraHeight() + (mainMenuHeight * mainMenuScaleY )/2);

        return retval;
    }

    float getNewY()
    {
        float retval = 0;

        float randomPart = CloudSprite.getNewYRandom(this.mainMenuHeight, this.mainMenuScaleY, this.resourceManager) / 4;
        float f = this.XSpeed / 2; // from 0.1 to 1

        float h = resourceManager.getCameraHeight();
        float h2 = h/ 2;
        float h3 = h/ 3;
        float dh3 = 2 * h3;
        float distancePart = h3 + f * dh3;

        retval = randomPart + distancePart;

        return retval;
    }

    void raiseNeedToSortChildren()
    {
        for(INeedToSortChildrenListener needToSortChildrenListener : this.needToSortChildrenListeners)
        {
            needToSortChildrenListener.onNeedToSortChildren(this);
        }
    }

    @Override
    protected void onManagedUpdate(final float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
        if(!initialized) {
            initialized = true;
            this.setScale(XSpeed/2);
            this.setZIndex(-4000+Math.round(XSpeed*1000f));
            this.setY(this.getNewY());
            this.setVisible(true);

            this.raiseNeedToSortChildren();
        }
        if(this.getX()<-(this.getWidth()*this.getScaleX())/2) {
            XSpeed = MathUtils.random(0.2f, 2f);
            this.setScale(XSpeed/2);
            this.setPosition(this.resourceManager.getCameraWidth()+(this.getWidth()*this.getScaleX())/2,
                    this.getNewY());

            // MathUtils.random(-(this.getHeight()*this.getScaleY())/2, mainMenu.getSceneManager().getResourceManager().getCameraHeight() + (this.getHeight()*this.getScaleY())/2)
            this.setZIndex(-4000+Math.round(XSpeed*1000f));

            this.raiseNeedToSortChildren();
        }
        this.setPosition(this.getX()-(XSpeed*(pSecondsElapsed/0.016666f)), this.getY());
    }

    @Override
    public void addNeedToSortChildrenListener(INeedToSortChildrenListener needToSortChildrenListener) {
        this.needToSortChildrenListeners.add(needToSortChildrenListener);
    }

    @Override
    public void removeNeedToSortChildrenListener(INeedToSortChildrenListener needToSortChildrenListener) {
        this.needToSortChildrenListeners.remove(needToSortChildrenListener);
    }
}
