package com.bitarcher.aeFun.drawables.characters.animals;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.characters.animals.DogTools.MainPositionSwithIntermediatesGenerator;
import com.bitarcher.aeFun.drawables.characters.animals.DogTools.ResourceInfos;
import com.bitarcher.aeFun.drawables.characters.animals.DogTools.SideResourceInfos;
import com.bitarcher.aeFun.interfaces.drawables.characters.EnumMainPosition;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.drawables.characters.IMainPositionSwitchIntermediatesGenerator;
import com.bitarcher.aeFun.interfaces.drawables.characters.ICharacterSidedImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceInfoListGotter;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michel on 18/03/15.
 */
public class Dog extends com.bitarcher.aeFun.drawables.characters.Character implements IResourceInfoListGotter {

    ResourceInfos resourceInfos;
    ArrayList<EnumMainPosition> supportedMainPositions;

    public ResourceInfos getResourceInfos()
    {
        return this.resourceInfos;
    }

    @Override
    protected IMainPositionSwitchIntermediatesGenerator getNewMainPositionSwitchIntermediatesGenerator() {
        return new MainPositionSwithIntermediatesGenerator(this);
    }

    @Override
    public List<IResourceInfo> getResourceInfoList() {

        ArrayList<IResourceInfo> retval = new ArrayList<>();


        for(EnumSide side : EnumSide.values()) {
            SideResourceInfos sideResourceInfos = this.resourceInfos.getSide(side);

            if(this.supportedMainPositions.contains(EnumMainPosition.Run)) {
                for (int i = 0; i < 5; i++) {
                    retval.add(sideResourceInfos.getRun().getRuns()[i].getTextureSetResourceInfo());
                }
            }

            if(this.supportedMainPositions.contains(EnumMainPosition.Straight)) {
                retval.add(sideResourceInfos.getStraightResourceInfos().getStraightBackMouthOpened().getTextureSetResourceInfo());
                retval.add(sideResourceInfos.getStraightResourceInfos().getStraightFrontClosedEyes().getTextureSetResourceInfo());
                retval.add(sideResourceInfos.getStraightResourceInfos().getStraightFrontClosedEyesSmileTailLifted().getTextureSetResourceInfo());
                retval.add(sideResourceInfos.getStraightResourceInfos().getStraightFrontMouthWideOpened().getTextureSetResourceInfo());
            }

            if(this.supportedMainPositions.contains(EnumMainPosition.Straight) || this.supportedMainPositions.contains(EnumMainPosition.Walk))
            {
                retval.add(sideResourceInfos.getStraightResourceInfos().getStraight().getTextureSetResourceInfo());
            }

            if(this.supportedMainPositions.contains(EnumMainPosition.Walk) || this.supportedMainPositions.contains(EnumMainPosition.Straight)) {
                retval.add(sideResourceInfos.getStraightResourceInfos().getStraightMiddleMouthOpened().getTextureSetResourceInfo());
            }

            retval.add(sideResourceInfos.getLookPlayer().getTextureSetResourceInfo());
            retval.add(sideResourceInfos.getUTurn().getTextureSetResourceInfo());

            if(this.supportedMainPositions.contains(EnumMainPosition.Walk)) {
                retval.add(sideResourceInfos.getWalk1().getTextureSetResourceInfo());
                retval.add(sideResourceInfos.getWalk2().getTextureSetResourceInfo());
            }

            if(this.supportedMainPositions.contains(EnumMainPosition.Idle)) {
                retval.add(sideResourceInfos.getSit().getTextureSetResourceInfo());
            }
        }

        return retval;
    }

    public Dog(IResourceManager resourceManager, List<EnumMainPosition> supportedMainPositions) {
        super(resourceManager, "dog");

        this.resourceInfos = new ResourceInfos(this);
        this.supportedMainPositions = new ArrayList<>(supportedMainPositions);
    }

    @Override
    public void pushResourceRequirements() {
        this.getResourceManager().pushRequirement(this);
    }

    @Override
    public void popResourceRequirements() {
        this.getResourceManager().popRequirement(this);
    }

    @Override
    protected ICharacterSidedImage getInitialSidedImage() {
        ICharacterSidedImage retval = null;

        if(this.supportedMainPositions.contains(EnumMainPosition.Straight))
        {
            retval =this.resourceInfos.getRightSide().getStraightResourceInfos().getStraight();
        }

        if(this.supportedMainPositions.contains(EnumMainPosition.Idle))
        {
            retval = this.resourceInfos.getRightSide().getSit();
        }


        return retval;
    }

    @Override
    public float getAspectRatio() {
        return 500f/423f;
    }

    @Override
    protected ICharacterSidedImage getSidedImage(float secondsElapsedSinceMainPositionChanged, EnumSide side, EnumMainPosition mainPosition) {
        ICharacterSidedImage retval = null;

        SideResourceInfos sideResourceInfos = this.resourceInfos.getSide(side);

        switch (mainPosition)
        {
            case Run:
                // 6 frames / per seconds
                retval = sideResourceInfos.getRun().getNextSidedImage((int)(secondsElapsedSinceMainPositionChanged * 10));
                break;
            case Idle:
                retval = sideResourceInfos.getSit();
                break;
            case Straight:
                // 0,2 frame per second
                retval = sideResourceInfos.getNextTalkSidedImage((int)(secondsElapsedSinceMainPositionChanged * 0.2f));
                break;
            case Talk:
                // 1 frame per second
                retval = sideResourceInfos.getNextTalkSidedImage((int)secondsElapsedSinceMainPositionChanged);
                break;
            case Walk:
                // 4 frame per second
                retval = sideResourceInfos.getNextWalkSidedImage((int)(secondsElapsedSinceMainPositionChanged * 4));
                break;
        }

        if(retval == null)
        {
            throw new RuntimeException("No sided image for " + side + " " + mainPosition);
        }

        return retval;
    }
}

