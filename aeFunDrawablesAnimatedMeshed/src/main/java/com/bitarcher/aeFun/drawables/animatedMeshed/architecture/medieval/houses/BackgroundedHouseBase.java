package com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses;

import com.bitarcher.aeFun.drawables.animatedMeshed.tools.CompositeMeshesBase;
import com.bitarcher.aeFun.geometry.pointsTransformation.FlipYAxisFunction;
import com.bitarcher.aeFun.geometry.pointsTransformation.Pipeline;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceInfoListGotter;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 25/04/15.
 */
public abstract class BackgroundedHouseBase extends CompositeMeshesBase implements IResourceInfoListGotter  {
    IResourceManager resourceManager;
    Entity spriteLayer;
    Sprite sprite;
    Color backgroundColor;
    Mesh backgroundMesh;

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public BackgroundedHouseBase(IResourceManager resourceManager, float pX, float pY, float pWidth, float pHeight, Color backgroundColor, ISize paperDesignSize) {
        super(pX, pY, pWidth, pHeight, resourceManager.getEngine().getVertexBufferObjectManager(), paperDesignSize);

        this.resourceManager = resourceManager;
        this.backgroundColor = backgroundColor;

        this.computeStableMeshes();
    }

    @Override
    protected Pipeline getNewPipeline() {
        Pipeline retval = super.getNewPipeline();

        retval.prependFunction(new FlipYAxisFunction(this.paperModelSize.getHeight()));

        return retval;
    }

    void computeStableMeshes()
    {
        this.unloadSprites();

        if(this.backgroundMesh != null)
        {
            this.detachChild(this.backgroundMesh);
            this.backgroundMesh = null;
        }

        this.backgroundMesh = this.getNewMesh(this.backgroundColor, this.getBackgroundMeshPoints(), this.getBackgroundMeshDrawMode());
        this.attachChild(this.backgroundMesh);

        this.spriteLayer = new Entity();
        this.attachChild(this.spriteLayer);

        if(this.isResourcesPushed()) {
            this.loadSprites();
        }


    }

    protected abstract List<IPoint> getBackgroundMeshPoints();

    protected DrawMode getBackgroundMeshDrawMode()
    {
        return DrawMode.TRIANGLE_STRIP;
    }

    void unloadSprites()
    {
        if(this.spriteLayer != null)
        {
            if(this.sprite != null) {
                this.spriteLayer.detachChild(this.sprite);
            }
        }

        this.sprite = null;
    }

    void loadSprites()
    {
        if(this.spriteLayer != null)
        {
            ISize paperDesignSize = this.paperModelSize;

            this.sprite = this.getNewSprite(this.resourceManager, this.getImage(), false, paperDesignSize.getWidth() / 2f, paperDesignSize.getHeight() / 2f, paperDesignSize.getWidth(), paperDesignSize.getHeight());
            this.spriteLayer.attachChild(this.sprite);
        }
    }

    @Override
    public List<IResourceInfo> getResourceInfoList() {
        ArrayList<IResourceInfo> retval = new ArrayList<>();

        IImage image = this.getImage();
        retval.add(image.getTextureSetResourceInfo());

        return retval;
    }


    protected abstract IImage getImage();

    @Override
    public void pushResourceRequirements() {
        super.pushResourceRequirements();

        this.resourceManager.pushRequirement(this);
    }

    @Override
    public void popResourceRequirements() {
        super.popResourceRequirements();

        this.resourceManager.popRequirement(this);
    }


    void onSizeChanged()
    {
        this.computeStableMeshes();
    }

    @Override
    public void setSize(float pWidth, float pHeight) {
        super.setSize(pWidth, pHeight);
        this.onSizeChanged();
    }

    @Override
    public void setWidth(float pWidth) {
        super.setWidth(pWidth);
        this.onSizeChanged();
    }

    @Override
    public void setHeight(float pHeight) {
        super.setHeight(pHeight);
        this.onSizeChanged();
    }

    @Override
    public void onAttached() {
        super.onAttached();

        this.loadSprites();
    }

    @Override
    public void onDetached() {
        this.unloadSprites();

        super.onDetached();
    }
}

