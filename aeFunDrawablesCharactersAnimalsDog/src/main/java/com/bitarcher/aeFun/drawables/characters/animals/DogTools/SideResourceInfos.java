package com.bitarcher.aeFun.drawables.characters.animals.DogTools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.characters.CharacterSidedImage;

import com.bitarcher.aeFun.drawables.characters.RIBase;
import com.bitarcher.aeFun.drawables.characters.animals.Dog;
import com.bitarcher.aeFun.interfaces.drawables.characters.EnumSide;
import com.bitarcher.aeFun.interfaces.drawables.characters.ICharacterSidedImage;

import java.util.NoSuchElementException;

/**
 * Created by michel on 16/03/15.
 */
public class SideResourceInfos extends RIBase {

    EnumSide side;
    RunResourceInfos run;
    StraightResourceInfos straightResourceInfos;

    CharacterSidedImage lookPlayer;
    CharacterSidedImage uTurn;
    CharacterSidedImage walk1;
    CharacterSidedImage walk2;
    CharacterSidedImage sit;


    public CharacterSidedImage getSit() {
        return sit;
    }

    public StraightResourceInfos getStraightResourceInfos() {
        return straightResourceInfos;
    }

    public EnumSide getSide() {
        return side;
    }

    public RunResourceInfos getRun() {
        return run;
    }

    public CharacterSidedImage getLookPlayer() {
        return lookPlayer;
    }

    public CharacterSidedImage getUTurn() {
        return uTurn;
    }

    public CharacterSidedImage getWalk1() {
        return walk1;
    }

    public CharacterSidedImage getWalk2() {
        return walk2;
    }

    public SideResourceInfos(Dog dog, EnumSide side) {
        super(dog);
        this.side = side;
        this.run = new RunResourceInfos(dog, side);
        this.straightResourceInfos = new StraightResourceInfos(dog, side);

        this.lookPlayer = this.getNewSidedBitmapImageByResId("lookPlayer", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_look_player, side);
        this.uTurn = this.getNewSidedBitmapImageByResId("uTurn", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_uturn, side);
        this.walk1 = this.getNewSidedBitmapImageByResId("walk1", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_walk1, side);
        this.walk2 = this.getNewSidedBitmapImageByResId("walk2", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_walk2, side);
        this.sit = this.getNewSidedBitmapImageByResId("sit", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_sit, side);

    }

    public ICharacterSidedImage getNextWalkSidedImage(int counter)
    {
        ICharacterSidedImage retval;

        int modulo = counter % 3;

        switch (modulo)
        {
            case 0:
                retval = this.getStraightResourceInfos().getStraightMiddleMouthOpened();
                break;
            case 1:
                retval = this.getWalk1();
                break;
            case 2:
                retval = this.getWalk2();
                break;
            default:
                throw new NoSuchElementException();
        }

        return retval;
    }

    public ICharacterSidedImage getNextTalkSidedImage(int counter)
    {
        ICharacterSidedImage retval;

        int modulo = counter % 3;

        switch (modulo)
        {
            case 0:
                retval = this.getStraightResourceInfos().getStraightFrontClosedEyes();
                break;
            case 1:
                retval = this.getStraightResourceInfos().getStraightFrontClosedEyesSmileTailLifted();
                break;
            case 2:
                retval = this.getStraightResourceInfos().getStraightFrontMouthWideOpened();
                break;
            default:
                throw new NoSuchElementException();
        }

        return retval;
    }

    public ICharacterSidedImage getNextStraightSidedImage(int counter)
    {
        ICharacterSidedImage retval;

        int modulo = counter % 3;

        switch (modulo)
        {
            case 0:
                retval = this.getStraightResourceInfos().getStraightFrontClosedEyes();
                break;
            case 1:
                retval = this.getStraightResourceInfos().getStraightFrontClosedEyesSmileTailLifted();
                break;
            case 2:
                retval = this.getStraightResourceInfos().getStraightFrontMouthWideOpened();
                break;
            default:
                throw new NoSuchElementException();
        }

        return retval;
    }
}

