package com.bitarcher.aeFun.drawables.characters.animals.DogTools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.characters.animals.Dog;
import com.bitarcher.aeFun.interfaces.drawables.characters.ICharacterSidedImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.drawables.characters.EnumSide;
import com.bitarcher.interfaces.drawables.characters.dog.EnumPosition;

/**
 * Created by michel on 16/03/15.
 */
public class ResourceInfos {

    SideResourceInfos leftSide;
    SideResourceInfos rightSide;

    Dog dog;

    public IResourceManager getResourceManager() {
        return dog.getResourceManager();
    }

    public SideResourceInfos getLeftSide() {
        return leftSide;
    }

    public SideResourceInfos getRightSide() {
        return rightSide;
    }

    public SideResourceInfos getSide(EnumSide side)
    {
        SideResourceInfos retval = null;

        if(side == EnumSide.Left)
            retval = this.leftSide;
        else
            retval = this.rightSide;

        return retval;
    }

    public ResourceInfos(Dog dog) {
        this.dog = dog;

        this.leftSide = new SideResourceInfos(dog, EnumSide.Left);
        this.rightSide = new SideResourceInfos(dog,EnumSide.Right);
    }

    public ICharacterSidedImage getTextureResourceInfo(EnumSide side, EnumPosition position)
    {
        ICharacterSidedImage retval = null;

        SideResourceInfos sideResourceInfos = null;

        switch (side)
        {
            case Left:
                sideResourceInfos = this.leftSide;
                break;
            case Right:
                sideResourceInfos = this.rightSide;
                break;
        }

        switch (position)
        {
            case  LookPlayer:
                retval = sideResourceInfos.getLookPlayer();
                break;
            case  Run1:
                retval = sideResourceInfos.getRun().getRuns()[0];
                break;
            case  Run2:
                retval = sideResourceInfos.getRun().getRuns()[1];
                break;
            case  Run3:
                retval = sideResourceInfos.getRun().getRuns()[2];
                break;
            case  Run4:
                retval = sideResourceInfos.getRun().getRuns()[3];
                break;
            case  Run5:
                retval = sideResourceInfos.getRun().getRuns()[4];
                break;
            case  Sit:
                retval = sideResourceInfos.getSit();
                break;
            case  StraightBackMouthOpened:
                retval = sideResourceInfos.getStraightResourceInfos().getStraightBackMouthOpened();
                break;
            case  StraightFrontClosedEyes:
                retval = sideResourceInfos.getStraightResourceInfos().getStraightFrontClosedEyes();
                break;
            case  StraightFrontClosedEyesSmileTailLifted:
                retval = sideResourceInfos.getStraightResourceInfos().getStraightFrontClosedEyesSmileTailLifted();
                break;
            case  StraightFrontMouthWideOpened:
                retval = sideResourceInfos.getStraightResourceInfos().getStraightFrontMouthWideOpened();
                break;
            case  StraightMiddleMouthOpened:
                retval = sideResourceInfos.getStraightResourceInfos().getStraightMiddleMouthOpened();
                break;
            case  Straight:
                retval = sideResourceInfos.getStraightResourceInfos().getStraight();
                break;
            case  UTurn:
                retval = sideResourceInfos.getUTurn();
                break;
            case  Walk1:
                retval = sideResourceInfos.getWalk1();
                break;
            case  Walk2:
                retval = sideResourceInfos.getWalk2();
                break;
        }

        if(retval == null)
        {
            throw  new UnsupportedOperationException(side.toString() + position.toString());
        }

        return retval;
    }
}
