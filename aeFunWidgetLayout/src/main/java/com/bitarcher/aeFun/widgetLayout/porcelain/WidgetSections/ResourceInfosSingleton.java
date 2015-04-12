package com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import android.content.Context;

import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.mvc.MvcImageTuple;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SingleBitmapTextureSetFromResIdsResourceInfo;
import com.bitarcher.aefun.widgetLayout.R;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 12/04/15.
 */
public class ResourceInfosSingleton {
    Context context;

    private static ResourceInfosSingleton ourInstance;



    public static ResourceInfosSingleton getInstance(Context context) {

        if(ourInstance == null)
        {
            ourInstance = new ResourceInfosSingleton(context);
        }

        return ourInstance;
    }

    private ResourceInfosSingleton(Context context) {
        this.context = context;
        this.analogOnScreenControlSectionBaseImage = new SingleBitmapTextureSetFromResIdsResourceInfo("@default/analogOnScreenControlSectionBaseImage", 128, 128, BitmapTextureFormat.RGBA_4444, TextureOptions.DEFAULT, this.context, R.drawable.onscreen_control_base, 1);
        this.analogOnScreenControlSectionKnobImage = new SingleBitmapTextureSetFromResIdsResourceInfo("@default/analogOnScreenControlSectionKnobImage", 64, 64, BitmapTextureFormat.RGBA_4444, TextureOptions.DEFAULT, this.context, R.drawable.onscreen_control_knob, 1);
    }

    SingleBitmapTextureSetFromResIdsResourceInfo analogOnScreenControlSectionBaseImage;
    SingleBitmapTextureSetFromResIdsResourceInfo analogOnScreenControlSectionKnobImage;

    public IImage getAnalogOnScreenControlSectionBaseImage() {
        return this.analogOnScreenControlSectionBaseImage;
    }


    public IImage getAnalogOnScreenControlSectionKnobImage() {
        return this.analogOnScreenControlSectionKnobImage;
    }
}

