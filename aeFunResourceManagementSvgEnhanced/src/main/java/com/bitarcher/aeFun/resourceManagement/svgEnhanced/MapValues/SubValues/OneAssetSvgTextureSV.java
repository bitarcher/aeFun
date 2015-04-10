package com.bitarcher.aeFun.resourceManagement.svgEnhanced.MapValues.SubValues;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneAssetSvgTexture;

/* Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
*/





import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;

import com.bitarcher.aeFun.resourceManagement.MapValues.SubValues.ITextureSetMapValue;
import com.bitarcher.aeFun.resourceManagement.MapValues.TextureSetFromAssetMapValue;

import org.andengine.extension.svg.SVGParser;
import org.andengine.extension.svg.adt.SVG;
import org.andengine.extension.svg.opengl.texture.atlas.bitmap.SVGBitmapTextureAtlasTextureRegionFactory;

import org.andengine.opengl.texture.region.ITextureRegion;

import java.io.IOException;

 /*
 * Created by michel on 26/01/15.
 */

public class OneAssetSvgTextureSV extends OneSvgTextureSV<IOneAssetSvgTexture> {
    public OneAssetSvgTextureSV(IResourceManager resourceManager, TextureSetFromAssetMapValue textureSetMapValue, IOneAssetSvgTexture oneTextureResourceInfo) {
        super(resourceManager, textureSetMapValue, oneTextureResourceInfo);
    }

    @Override
    protected ITextureRegion createTextureRegionFromResourceInfo(IResourceManager resourceManager, ITextureSetMapValue textureSetMapValue, IOneAssetSvgTexture oneTextureResourceInfo) {
        ITextureRegion retval = null;

        SVG svg;

        /*doesn't work !!!
        using other approach, @see http://stackoverflow.com/questions/19256340/loading-svg-scalable-vector-graphics-into-andengine

        if (oneTextureResourceInfo.getSvgColorMapper() != null) {
            retval = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(textureSetMapValue.getTexture(),
                    resourceManager.getContext(), ((IOneAssetSvgTexture) oneTextureResourceInfo).getFilename(),
                    oneTextureResourceInfo.getWidth(),
                    oneTextureResourceInfo.getHeight(),
                    oneTextureResourceInfo.getSvgColorMapper());
        } else {
            retval = SVGBitmapTextureAtlasTextureRegionFactory.createFromAsset(textureSetMapValue.getTexture(),
                    resourceManager.getContext(), ((IOneAssetSvgTexture)oneTextureResourceInfo).getFilename(),
                    oneTextureResourceInfo.getWidth(),
                    oneTextureResourceInfo.getHeight());
        }
        */


        //String file =                ((IAssetsBase)this.textureSetMapValue.getTexturesSetResourceInfo()).getAssetsBase() + "/" +
        //        ((IOneAssetSvgTexture) oneTextureResourceInfo).getFilename();

        // FIXME, assetbase.length != 0

        String file = ((IOneAssetSvgTexture) oneTextureResourceInfo).getFilename();

        try {
            if (oneTextureResourceInfo.getSvgColorMapper() != null) {
                svg = SVGParser.parseSVGFromAsset(resourceManager.getContext().getAssets(), file,
                        oneTextureResourceInfo.getSvgColorMapper());
            }else {
                svg = SVGParser.parseSVGFromAsset(resourceManager.getContext().getAssets(), file);
            }
        } catch (IOException e) {
            e.printStackTrace();

            throw new RuntimeException("Could not find asset " + file);

        }


        retval = SVGBitmapTextureAtlasTextureRegionFactory.createFromSVG(textureSetMapValue.getTexture(),
                svg, oneTextureResourceInfo.getWidth(),
                oneTextureResourceInfo.getHeight());


        return retval;
    }
}
