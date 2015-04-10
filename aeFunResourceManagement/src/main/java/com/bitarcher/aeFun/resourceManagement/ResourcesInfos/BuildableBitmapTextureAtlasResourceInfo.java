package com.bitarcher.aeFun.resourceManagement.ResourcesInfos;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBuildableBitmapTextureAtlasResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;

import org.andengine.opengl.texture.TextureManager;

/**
 * Created by michel on 08/01/15.
 */
public class BuildableBitmapTextureAtlasResourceInfo extends ResourceInfo implements IBuildableBitmapTextureAtlasResourceInfo {

    int _pWidth;
    int _pHeight;
    String _name;

    public BuildableBitmapTextureAtlasResourceInfo(TextureManager textureManager, int pWidth, int pHeight, String name) {
        super(name);
        this._pWidth = pWidth;
        this._pHeight = pHeight;
        this._name = name;
    }


    @Override
    public int getPWidth() {
        return this._pWidth;
    }

    @Override
    public int getPHeight() {
        return this._pHeight;
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public boolean isSimilar(IResourceInfo ressourceInfo) {
        boolean retval = true;

        if(ressourceInfo instanceof IBuildableBitmapTextureAtlasResourceInfo) {
            retval &= this._name == ((IBuildableBitmapTextureAtlasResourceInfo) ressourceInfo).getName();
            retval &= this._pHeight == ((IBuildableBitmapTextureAtlasResourceInfo) ressourceInfo).getPHeight();
            retval &= this._pWidth == ((IBuildableBitmapTextureAtlasResourceInfo) ressourceInfo).getPWidth();
        }
        else {
            retval =false;
        }
        return retval;
    }

    @Override
    public int getHashCode() {
        return this._pHeight + this._pWidth + this._name.hashCode();
    }


}
