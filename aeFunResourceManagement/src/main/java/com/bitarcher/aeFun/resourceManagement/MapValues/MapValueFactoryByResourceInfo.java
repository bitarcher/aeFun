package com.bitarcher.aeFun.resourceManagement.MapValues;

import com.bitarcher.aeFun.interfaces.resourcemanagement.EResourceCreationError;
import com.bitarcher.aeFun.resourceManagement.MapValues.Font.FontCreateFromAssetMapValue;
import com.bitarcher.aeFun.resourceManagement.MapValues.Font.FontCreateStrokeFromAssetMapValue;
import com.bitarcher.aeFun.resourceManagement.MapValues.Font.FontCreateStrokeFromTypeFaceMapValue;
import com.bitarcher.aeFun.interfaces.basicioc.ITFactory;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontCreateFromAssetResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontCreateFromTypeFaceResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontCreateStrokeFromAssetResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontCreateStrokeFromTypeFaceResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBitmapAnimationResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBitmapTexturesSetFromAssetResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBitmapTexturesSetFromResIdsResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBuildableBitmapTextureAtlasResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IMusicResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ISoundResourceInfo;
import com.bitarcher.aeFun.resourceManagement.MapValues.Font.FontCreateFromTypeFaceMapValue;
import com.bitarcher.aeFun.resourceManagement.ResourceManager;




/**
 * Created by michel on 08/01/15.
 */
public class MapValueFactoryByResourceInfo implements ITFactory<MapValue, IResourceInfo>{
    protected ResourceManager resourceManager;

    public MapValueFactoryByResourceInfo(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;


    }

    @Override
    public MapValue make(IResourceInfo resourceInfo)  throws EResourceCreationError {
        MapValue retval = null;

        if(resourceInfo == null)
        {
            throw new NullPointerException("resourceInfo is null");
        }

        if (resourceInfo instanceof IBuildableBitmapTextureAtlasResourceInfo)
        {
            IBuildableBitmapTextureAtlasResourceInfo buildableBitmapTextureAtlasResourceInfo = (IBuildableBitmapTextureAtlasResourceInfo) resourceInfo;
            BuildableBitmapTextureAtlasMapValue buildableBitmapTextureAtlasMapValue = new BuildableBitmapTextureAtlasMapValue(this.resourceManager,  buildableBitmapTextureAtlasResourceInfo);
            retval = buildableBitmapTextureAtlasMapValue;
        }
        else if(resourceInfo instanceof IBitmapTexturesSetFromAssetResourceInfo)
        {
            BitmapTextureSetFromAssetMapValue textureSetMapValue = new BitmapTextureSetFromAssetMapValue(this.resourceManager, (IBitmapTexturesSetFromAssetResourceInfo) resourceInfo);

            retval = textureSetMapValue;
        }
        /*
        else if(resourceInfo instanceof ISvgTexturesSetFromAssetResourceInfo)
        {
            SvgTextureSetFromAssetMapValue textureSetMapValue = new SvgTextureSetFromAssetMapValue(this.resourceManager, (ISvgTexturesSetFromAssetResourceInfo) resourceInfo);

            retval = textureSetMapValue;
        }
        */
        else if(resourceInfo instanceof IBitmapTexturesSetFromResIdsResourceInfo)
        {
            BitmapTextureSetFromResMapValue textureSetMapValue = new BitmapTextureSetFromResMapValue(this.resourceManager, (IBitmapTexturesSetFromResIdsResourceInfo) resourceInfo);

            retval = textureSetMapValue;
        }
        /*
        else if(resourceInfo instanceof ISvgTexturesSetFromResIdsResourceInfo)
        {
            SvgTextureSetFromResMapValue textureSetMapValue = new SvgTextureSetFromResMapValue(this.resourceManager, (ISvgTexturesSetFromResIdsResourceInfo) resourceInfo);

            retval = textureSetMapValue;
        }
        */
        else if(resourceInfo instanceof IBitmapAnimationResourceInfo)
        {
            BitmapAnimationMapValue bitmapAnimationMapValue = new BitmapAnimationMapValue(this.resourceManager, (IBitmapAnimationResourceInfo) resourceInfo);

            retval = bitmapAnimationMapValue;
        }
        /*
        else if(resourceInfo instanceof ISvgAnimationResourceInfo)
        {
            SvgAnimationMapValue svgAnimationMapValue = new SvgAnimationMapValue(this.resourceManager, (ISvgAnimationResourceInfo) resourceInfo);

            retval = svgAnimationMapValue;
        }
        */
        else if(resourceInfo instanceof IFontCreateFromAssetResourceInfo) {
            retval = new FontCreateFromAssetMapValue(this.resourceManager, (IFontCreateFromAssetResourceInfo) resourceInfo);
        }
        else if(resourceInfo instanceof IFontCreateFromTypeFaceResourceInfo)
        {
            retval = new FontCreateFromTypeFaceMapValue(this.resourceManager, (IFontCreateFromTypeFaceResourceInfo) resourceInfo);
        }
        else if(resourceInfo instanceof IFontCreateStrokeFromAssetResourceInfo)
        {
            retval = new FontCreateStrokeFromAssetMapValue(this.resourceManager, (IFontCreateStrokeFromAssetResourceInfo) resourceInfo);
        }
        else if(resourceInfo instanceof IFontCreateStrokeFromTypeFaceResourceInfo)
        {
            retval = new FontCreateStrokeFromTypeFaceMapValue(this.resourceManager, (IFontCreateStrokeFromTypeFaceResourceInfo) resourceInfo);
        }
        else if(resourceInfo instanceof ISoundResourceInfo)
        {
            retval = new SoundMapValue(this.resourceManager, (ISoundResourceInfo) resourceInfo);
        }
        else if(resourceInfo instanceof IMusicResourceInfo)
        {
            retval = new MusicMapValue(this.resourceManager, (IMusicResourceInfo) resourceInfo);
        }
        else
        {
            throw new EResourceCreationError(String.format("Unsupported resource type %s", resourceInfo.getClass().getName().toString()));
        }

        return retval;
    }
}

