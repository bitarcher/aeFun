package com.bitarcher.aeFun.drawables.characters;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.drawables.characters.EnumMainPosition;
import com.bitarcher.aeFun.interfaces.drawables.characters.EnumSide;
import com.bitarcher.aeFun.interfaces.drawables.characters.ICharacter;
import com.bitarcher.aeFun.interfaces.drawables.characters.IMainPositionSwitchIntermediatesGenerator;
import com.bitarcher.aeFun.interfaces.drawables.characters.ICharacterSidedImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;


import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by michel on 18/03/15.
 */
public abstract class Character extends Entity implements ICharacter {
    boolean isStarted = false;
    String name;
    IResourceManager resourceManager;
    EnumSide currentSide;
    EnumMainPosition currentMainPosition;
    ICharacterSidedImage currentSidedImage;
    float lastMainPositionChangedSecondsElapsed = -1; // if -1 just started
    Queue<ICharacterSidedImage> transitionImages = new LinkedBlockingQueue<>();
    Sprite sprite;
    int counter = 0;

    public EnumSide getCurrentSide() {
        return currentSide;
    }

    public EnumMainPosition getCurrentMainPosition() {
        return currentMainPosition;
    }

    @Override
    public IResourceManager getResourceManager() {
        return this.resourceManager;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Character(IResourceManager resourceManager, String name)
    {
        this.setIgnoreUpdate(true);
        this.resourceManager = resourceManager;
        this.name = name;
    }

    @Override
    public void setMainPosition(EnumSide side, EnumMainPosition mainPosition) {
        if(side != this.currentSide || mainPosition != this.currentMainPosition)
        {
            this.onMainPositionChanged(side, mainPosition);

            this.currentSide = side;
            this.currentMainPosition = mainPosition;
            this.lastMainPositionChangedSecondsElapsed = -1;
        }
    }

    protected void onMainPositionChanged(EnumSide side, EnumMainPosition mainPosition)
    {

        IMainPositionSwitchIntermediatesGenerator mainPositionSwitchIntermediatesGenerator = this.getNewMainPositionSwitchIntermediatesGenerator();

        List<ICharacterSidedImage> transitionList = mainPositionSwitchIntermediatesGenerator.getTransitions(this.currentSidedImage, side, mainPosition);

        this.transitionImages.clear();

        this.transitionImages.addAll(transitionList);
    }

    protected abstract ICharacterSidedImage getSidedImage(float secondsElapsedSinceMainPositionChanged, EnumSide side, EnumMainPosition mainPosition);

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if(this.lastMainPositionChangedSecondsElapsed < 0)
        {
            // service just started
            this.lastMainPositionChangedSecondsElapsed = pSecondsElapsed;
        }

        if(this.transitionImages.size() > 0)
        {
            ICharacterSidedImage characterSidedImage = this.transitionImages.poll();

            if(this.transitionImages.size() == 0)
            {
                // it was the last
                this.lastMainPositionChangedSecondsElapsed = pSecondsElapsed;
            }

            this.currentSidedImage = characterSidedImage;
            this.setSpriteImage();
        }
        else
        {
            // normal case

            ICharacterSidedImage characterSidedImage = this.getSidedImage(pSecondsElapsed - this.lastMainPositionChangedSecondsElapsed, this.currentSide, this.currentMainPosition);

            if(characterSidedImage != this.currentSidedImage)
            {
                this.currentSidedImage = characterSidedImage;
                this.setSpriteImage();
            }
        }

    }

    // precondition, isStarted = true
    // use this.currentSidedImage
    void setSpriteImage()
    {
        if(this.sprite != null)
        {
            this.detachChild(this.sprite);
            this.sprite.dispose();;
            this.sprite = null;
        }

        ITextureRegion textureRegion = this.resourceManager.getTextureRegionFromTextureSetByName(this.currentSidedImage.getTextureSetResourceInfo(), this.currentSidedImage.getTextureName());
        this.sprite = new Sprite(this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight(), textureRegion,  this.resourceManager.getEngine().getVertexBufferObjectManager());
        if(this.currentSidedImage.getSide() == EnumSide.Left) {
            this.sprite.setFlippedHorizontal(true);
        }
        this.attachChild(this.sprite);
    }

    @Override
    public void start() {

        if(this.isStarted)
            throw new RuntimeException("Already started");

        this.pushResourceRequirements();

        this.currentSidedImage = this.getInitialSidedImage();

        this.setSpriteImage();
        this.setIgnoreUpdate(false);

        this.isStarted = true;
    }

    protected abstract ICharacterSidedImage getInitialSidedImage();

    @Override
    public void stop() {
        if(!this.isStarted)
            throw new RuntimeException("Not started");


        this.setIgnoreUpdate(true);
        this.popResourceRequirements();
        if(this.sprite != null) {
            this.detachChild(this.sprite);
            this.sprite.dispose();
            this.sprite = null;
        }

        this.lastMainPositionChangedSecondsElapsed = -1;
        this.isStarted = false;
    }

    @Override
    public void tic(Date dateTime) {
        // not used

    }

    protected abstract IMainPositionSwitchIntermediatesGenerator getNewMainPositionSwitchIntermediatesGenerator();
}


