package com.bitarcher.aeFun.drawables.characters;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.drawables.characters.EnumSide;
import com.bitarcher.aeFun.interfaces.drawables.characters.ICharacter;
import com.bitarcher.aeFun.interfaces.drawables.characters.ICharacterSidedImage;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;

/**
 * Created by michel on 19/03/15.
 */
public class CharacterSidedImage implements ICharacterSidedImage {
    IImage image;
    EnumSide side;
    ICharacter character;

    public IImage getImage() {
        return image;
    }

    @Override
    public EnumSide getSide() {
        return side;
    }

    @Override
    public ICharacter getCharacter() {
        return this.character;
    }

    @Override
    public ITexturesSetResourceInfo getTextureSetResourceInfo() {
        return this.image.getTextureSetResourceInfo();
    }

    @Override
    public float getAspectRatio() {
        return this.character.getAspectRatio();
    }

    @Override
    public String getTextureName() {
        return this.image.getTextureName();
    }

    public CharacterSidedImage(IImage image, EnumSide side, ICharacter character) {
        this.image = image;
        this.side = side;
        this.character = character;
    }
}
