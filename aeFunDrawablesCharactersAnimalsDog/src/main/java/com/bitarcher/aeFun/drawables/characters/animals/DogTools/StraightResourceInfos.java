package com.bitarcher.aeFun.drawables.characters.animals.DogTools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.characters.CharacterSidedImage;

import com.bitarcher.aeFun.drawables.characters.animals.Dog;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;

/**
 * Created by michel on 16/03/15.
 */
public class StraightResourceInfos extends DogRIBase {
    EnumSide side;
    CharacterSidedImage straightBackMouthOpened;
    CharacterSidedImage straightFrontClosedEyes;
    CharacterSidedImage straightFrontClosedEyesSmileTailLifted;
    CharacterSidedImage straightFrontMouthWideOpened;
    CharacterSidedImage straightMiddleMouthOpened;
    CharacterSidedImage straight;


    public EnumSide getSide() {
        return side;
    }

    public CharacterSidedImage getStraightBackMouthOpened() {
        return straightBackMouthOpened;
    }

    public CharacterSidedImage getStraightFrontClosedEyes() {
        return straightFrontClosedEyes;
    }

    public CharacterSidedImage getStraightFrontClosedEyesSmileTailLifted() {
        return straightFrontClosedEyesSmileTailLifted;
    }

    public CharacterSidedImage getStraightFrontMouthWideOpened() {
        return straightFrontMouthWideOpened;
    }

    public CharacterSidedImage getStraightMiddleMouthOpened() {
        return straightMiddleMouthOpened;
    }

    public CharacterSidedImage getStraight() {
        return straight;
    }

    public StraightResourceInfos(Dog dog, EnumSide side) {
        super(dog);
        this.side = side;
/*
        this.straightBackMouthOpened = this.getNewSidedBitmapImageByResId("straightBackMouthOpened", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_straight_back_mouth_opened, side);
        this.straightFrontClosedEyes = this.getNewSidedBitmapImageByResId("straightFrontClosedEyes", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_straight_front_closed_eyes, side);
        this.straightFrontClosedEyesSmileTailLifted = this.getNewSidedBitmapImageByResId("straightFrontClosedEyesSmileTailLifted", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_straight_front_closed_eyes_smile_tail_lifted, side);
        this.straightFrontMouthWideOpened = this.getNewSidedBitmapImageByResId("straightFrontMouthWideOpened", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_straight_front_mouth_wide_opened, side);
        this.straightMiddleMouthOpened = this.getNewSidedBitmapImageByResId("straightMiddleMouthOpened", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_straight_middle_mouth_opened, side);
        this.straight = this.getNewSidedBitmapImageByResId("straight", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_straight, side);*/


        this.straightBackMouthOpened = this.getNewSidedBitmapImageByAssetFileRadical("dog_right_straight_back_mouth_opened", side);
        this.straightFrontClosedEyes = this.getNewSidedBitmapImageByAssetFileRadical("dog_right_straight_front_closed_eyes", side);
        this.straightFrontClosedEyesSmileTailLifted = this.getNewSidedBitmapImageByAssetFileRadical("dog_right_straight_front_closed_eyes_smile_tail_lifted", side);
        this.straightFrontMouthWideOpened = this.getNewSidedBitmapImageByAssetFileRadical("dog_right_straight_front_mouth_wide_opened", side);
        this.straightMiddleMouthOpened = this.getNewSidedBitmapImageByAssetFileRadical("dog_right_straight_middle_mouth_opened", side);
        this.straight = this.getNewSidedBitmapImageByAssetFileRadical("dog_right_straight", side);
    }
}

