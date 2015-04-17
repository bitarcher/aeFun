package com.bitarcher.aeFun.drawables.characters.animals.DogTools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.characters.animals.Dog;
import com.bitarcher.aeFun.interfaces.drawables.characters.EnumMainPosition;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.drawables.characters.IMainPositionSwitchIntermediatesGenerator;
import com.bitarcher.aeFun.interfaces.drawables.characters.ICharacterSidedImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michel on 18/03/15.
 */
public class MainPositionSwithIntermediatesGenerator implements IMainPositionSwitchIntermediatesGenerator {

    Dog dog;

    public Dog getDog() {
        return dog;
    }

    public MainPositionSwithIntermediatesGenerator(Dog dog) {
        this.dog = dog;
    }

    @Override
    public List<ICharacterSidedImage> getTransitions(ICharacterSidedImage currentImage, EnumSide newSide, EnumMainPosition newMainPosition) {
        ArrayList<ICharacterSidedImage> retval;
        ArrayList<ICharacterSidedImage> sidedImages = new ArrayList<>();

        SideResourceInfos currentSideResourceInfos =this.getDog().getResourceInfos().getSide(currentImage.getSide());
        RunResourceInfos runRI = currentSideResourceInfos.getRun();

        int runInd = runRI.isOneOfThose(currentImage);

        if(runInd >= 0)
        {
            // currentImage is a run, finish run cycle

            for(int i = runInd ; i < runRI.getRuns().length ; i++)
            {
                sidedImages.add(runRI.getRuns()[i]);
            }
        }

        if(currentImage.getSide() != newSide)
        {
            SideResourceInfos newSideResourceInfos =this.getDog().getResourceInfos().getSide(newSide);
            sidedImages.add(currentSideResourceInfos.getUTurn());
            sidedImages.add(currentSideResourceInfos.getLookPlayer());
            sidedImages.add(newSideResourceInfos.getLookPlayer());
            sidedImages.add(newSideResourceInfos.getUTurn());
        }


        int maxNumOfTransitionImages = 5;
        int sidedImagesLength = sidedImages.size();

        if(sidedImagesLength > maxNumOfTransitionImages)
        {
            retval = new ArrayList<>();

            int div = sidedImagesLength / maxNumOfTransitionImages;

            for(int i = 0; i < sidedImagesLength ; i++)
            {
                if(i%div == 0)
                {
                    retval.add(sidedImages.get(i));
                }
            }
        }
        else
        {
            retval = sidedImages;
        }


        return sidedImages;
    }
}

