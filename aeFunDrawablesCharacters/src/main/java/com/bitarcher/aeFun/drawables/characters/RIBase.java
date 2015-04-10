package com.bitarcher.aeFun.drawables.characters;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.drawables.characters.EnumSide;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SingleBitmapTextureSetFromResIdsResourceInfo;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by michel on 16/03/15.
 */

public class RIBase {
    com.bitarcher.aeFun.drawables.characters.Character character;

    Map<String, SingleBitmapTextureSetFromResIdsResourceInfo> map = new Hashtable<>();


    public RIBase(com.bitarcher.aeFun.drawables.characters.Character character)
    {
        this.character = character;
    }

    protected CharacterSidedImage getNewSidedBitmapImageByResId(String positionName, int resId, EnumSide side)
    {
        CharacterSidedImage retval = null;

        SingleBitmapTextureSetFromResIdsResourceInfo singleBitmapTextureSetFromResIdsResourceInfo;

        if(this.map.containsKey(positionName))
        {
            // texture is share for left and right, we use sprite set horizontal flip,
            // texture has to be provided with the character looking to the right side

            singleBitmapTextureSetFromResIdsResourceInfo = this.map.get(positionName);
        }
        else
        {
            singleBitmapTextureSetFromResIdsResourceInfo = new SingleBitmapTextureSetFromResIdsResourceInfo(this.character.getName() + "_" + positionName,
                    this.getTextureWidth(), this.getTextureHeight(), this.getBitmapTextureFormat(),
                    this.getTextureOptions(),
                    this.character.getResourceManager().getContext(),
                    resId, this.character.getAspectRatio());

            this.map.put(positionName, singleBitmapTextureSetFromResIdsResourceInfo);
        }


        retval = new CharacterSidedImage(singleBitmapTextureSetFromResIdsResourceInfo, side, this.character);

        return retval;
    }

    protected BitmapTextureFormat getBitmapTextureFormat()
    {
        return BitmapTextureFormat.RGBA_4444;
    }

    protected int getTextureWidth()
    {
        return 512;
    }

    protected int getTextureHeight()
    {
        return 512;
    }

    protected TextureOptions getTextureOptions()
    {
        return null;
    }
}
