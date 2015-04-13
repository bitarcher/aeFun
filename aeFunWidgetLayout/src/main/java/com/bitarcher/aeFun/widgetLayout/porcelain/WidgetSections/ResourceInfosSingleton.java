package com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import android.content.Context;

import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.mvc.MvcImageTuple;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SingleBitmapTextureSetFromAssetResourceInfo;
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
        // TODO,
        // use of asset inside a library limit the use to gradle based ide
        // but there is a bug in the resource version, maybe the context should not be the context of the main application but from a class that derivates from Application inside the lib @see http://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context/4391811#4391811

      /*  this.analogOnScreenControlSectionBaseImage = new SingleBitmapTextureSetFromResIdsResourceInfo("@default/analogOnScreenControlSectionBaseImage", 128, 128, BitmapTextureFormat.RGBA_8888, TextureOptions.DEFAULT, this.context, R.drawable.onscreen_control_base, 1);
        this.analogOnScreenControlSectionKnobImage = new SingleBitmapTextureSetFromResIdsResourceInfo("@default/analogOnScreenControlSectionKnobImage", 64, 64, BitmapTextureFormat.RGBA_8888, TextureOptions.DEFAULT, this.context, R.drawable.onscreen_control_knob, 1);*/

        this.analogOnScreenControlSectionBaseImage = new SingleBitmapTextureSetFromAssetResourceInfo("onscreen_control_base.png", 128, 128, BitmapTextureFormat.RGBA_8888, TextureOptions.DEFAULT, "gfx/" , 1);
        this.analogOnScreenControlSectionKnobImage = new SingleBitmapTextureSetFromAssetResourceInfo("onscreen_control_knob.png", 64, 64, BitmapTextureFormat.RGBA_8888, TextureOptions.DEFAULT, "gfx/", 1);
    }



    //SingleBitmapTextureSetFromResIdsResourceInfo analogOnScreenControlSectionBaseImage;
    //SingleBitmapTextureSetFromResIdsResourceInfo analogOnScreenControlSectionKnobImage;

    SingleBitmapTextureSetFromAssetResourceInfo analogOnScreenControlSectionBaseImage;
    SingleBitmapTextureSetFromAssetResourceInfo analogOnScreenControlSectionKnobImage;

    public IImage getAnalogOnScreenControlSectionBaseImage() {
        return this.analogOnScreenControlSectionBaseImage;
    }


    public IImage getAnalogOnScreenControlSectionKnobImage() {
        return this.analogOnScreenControlSectionKnobImage;
    }
}

