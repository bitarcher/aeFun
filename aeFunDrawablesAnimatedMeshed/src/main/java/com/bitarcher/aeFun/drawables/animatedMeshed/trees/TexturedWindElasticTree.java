package com.bitarcher.aeFun.drawables.animatedMeshed.trees;

import com.bitarcher.aeFun.drawables.animatedMeshed.tools.WindElasticCompositeMeshes;
import com.bitarcher.aeFun.drawables.cliparts.ResourceInfosSingleton;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.primitives.nazgees.TexturedMesh;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceInfoListGotter;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;

import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 17/04/15.
 */
@Deprecated
/**
 * FIXME not working as expected
 */
public class TexturedWindElasticTree extends WindElasticCompositeMeshes implements IResourceInfoListGotter {

    IResourceManager resourceManager;
    TexturedMesh texturedMesh;
    int treeNum;



    public TexturedWindElasticTree(IResourceManager resourceManager, float pX, float pY, float pWidth, float pHeight, float windStrength, EnumSide windSide, int treeNum) {
        super(pX, pY, pWidth, pHeight, resourceManager.getEngine().getVertexBufferObjectManager(), new Size(100, 300), windStrength, windSide);
        this.resourceManager = resourceManager;
        this.treeNum = treeNum;

        this.computeMeshes();
    }

    @Override
    protected void computeMeshes()
    {
        if(this.texturedMesh != null)
        {
            this.detachChild(this.texturedMesh);
        }

        if(this.isResourcesPushed())
        {
            IImage image = ResourceInfosSingleton.getInstance().getNature().getTrees().getTree(this.treeNum);
            ITextureRegion textureRegion = this.resourceManager.getTextureRegionFromTextureSetByName(image.getTextureSetResourceInfo(), image.getTextureName());

            this.texturedMesh = this.getNewOneColumnTextureMeshed(textureRegion, 50, 150, 100, 300, 20);
            this.attachChild(this.texturedMesh);
        }
    }

    @Override
    public List<IResourceInfo> getResourceInfoList() {
        ArrayList<IResourceInfo> retval = new ArrayList<>();

        retval.add(ResourceInfosSingleton.getInstance().getNature().getTrees().getTree(this.treeNum).getTextureSetResourceInfo());

        return retval;
    }

    @Override
    public void onAttached() {
        super.onAttached();

        this.computeMeshes();
    }

    @Override
    public void onDetached() {
        if(this.texturedMesh != null)
        {
            this.detachChild(this.texturedMesh);
        }


        super.onDetached();
    }

    @Override
    public void pushResourceRequirements() {
        super.pushResourceRequirements();

        this.resourceManager.pushRequirement(this);
    }

    @Override
    public void popResourceRequirements() {
        this.resourceManager.popRequirement(this);

        super.popResourceRequirements();
    }
}
