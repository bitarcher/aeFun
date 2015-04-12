package com.bitarcher.aeFun.drawables.characters.animals.DogTools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.characters.CharacterSidedImage;
import com.bitarcher.aeFun.drawables.characters.RIBase;
import com.bitarcher.aeFun.drawables.characters.animals.Dog;
import com.bitarcher.aeFun.interfaces.drawables.characters.ICharacterSidedImage;

import com.bitarcher.aeFun.interfaces.drawables.characters.EnumSide;

/**
 * Created by michel on 16/03/15.
 */
public class RunResourceInfos extends RIBase {

    EnumSide side;
    CharacterSidedImage runs[];

    public CharacterSidedImage[] getRuns() {
        return runs;
    }

    public RunResourceInfos(Dog dog, EnumSide side) {
        super(dog);
        this.side = side;

        this.runs = new CharacterSidedImage[5];

        this.runs[0] = this.getNewSidedBitmapImageByResId("run1", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_run1, side);
        this.runs[1] = this.getNewSidedBitmapImageByResId("run2", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_run2, side);
        this.runs[2] = this.getNewSidedBitmapImageByResId("run3", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_run3, side);
        this.runs[3] = this.getNewSidedBitmapImageByResId("run4", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_run4, side);
        this.runs[4] = this.getNewSidedBitmapImageByResId("run5", com.bitarcher.aeFun.drawables.characters.animals.dog.R.drawable.dog_right_run5, side);

    }

    public EnumSide getSide() {
        return side;
    }

    /**
     *
     * @param sidedImage
     * @return -1 if not one of those or the indice if found
     */
    public int isOneOfThose(ICharacterSidedImage sidedImage)
    {
        int retval = -1;

        for(int i = 0 ; i < this.runs.length ; i++)
        {
            if(this.runs[i] == sidedImage)
            {
                retval = i;
                break;
            }
        }

        return retval;
    }

    public ICharacterSidedImage getNextSidedImage(int counter)
    {
        return this.runs[counter % this.runs.length];
    }
}
