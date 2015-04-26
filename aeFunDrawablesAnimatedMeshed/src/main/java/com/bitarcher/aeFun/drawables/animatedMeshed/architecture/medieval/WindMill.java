package com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.animatedMeshed.tools.CompositeMeshesBase;
import com.bitarcher.aeFun.drawables.animatedMeshed.tools.WindMillPales;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.interfaces.drawables.animatedMeshed.IWindStrength;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceInfoListGotter;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;


import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Gradient;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michel on 18/04/15.
 */
public class WindMill extends CompositeMeshesBase implements IWindStrength, IResourceInfoListGotter {

    IResourceManager resourceManager;
    float windStrength = 0;
    Color stonesGradientColor1 = new Color(0.9f, 0.85f, 0.8f);
    Color stonesGradientColor2 = new Color(0.7f, 0.7f, 0.7f);
    Color roofColor = new Color(0.95f, 0.4f, 0.45f);
    //Color bladesColor = new Color(0.804f, 0.522f, 0.247f); // peru beige
    Gradient stones;
    Mesh roof;
    Entity palesSpriteLayers;
    Sprite palesSprite;
    //WindMillPales pales;
    int woodDoorNum;
    Entity doorLayer;
    Sprite doorSprite;
    Entity stoneSpriteLayer;
    Sprite stoneSprite;
    float angle = 0;

    public int getWoodDoorNum() {
        return woodDoorNum;
    }

    public IResourceManager getResourceManager() {
        return resourceManager;
    }

    ArrayList<IPoint> roofPaperCoords = new ArrayList<>();

    public WindMill(IResourceManager resourceManager, float pX, float pY, float pWidth, float pHeight, int woodDoorNum) {
        super(pX, pY, pWidth, pHeight, resourceManager.getEngine().getVertexBufferObjectManager(), new Size(20, 25));

        this.resourceManager = resourceManager;

        this.setRoofPaperCoords();
        this.computeStableMeshes();
        this.woodDoorNum = woodDoorNum;
    }

    public WindMill(IResourceManager resourceManager, float pX, float pY, float pWidth, float pHeight, Color stonesGradientColor1, Color stonesGradientColor2, Color roofColor, int woodDoorNum) {
        super(pX, pY, pWidth, pHeight, resourceManager.getEngine().getVertexBufferObjectManager(), new Size(20, 25));
        this.resourceManager = resourceManager;

        this.stonesGradientColor1 = stonesGradientColor1;
        this.stonesGradientColor2 = stonesGradientColor2;
        this.roofColor = roofColor;

        this.setRoofPaperCoords();
        this.computeStableMeshes();

        this.woodDoorNum = woodDoorNum;
    }

    void setRoofPaperCoords()
    {
        this.roofPaperCoords = new ArrayList<>();
        this.roofPaperCoords.add(new Point(0, 18));
        this.roofPaperCoords.add(new Point(20, 18));
        this.roofPaperCoords.add(new Point(10, 25));
    }

    void computeStableMeshes()
    {
        if(this.stones != null)
        {
            this.detachChild(this.stones);
            this.stones = null;
        }

        if(this.roof != null)
        {
            this.detachChild(this.roof);
            this.roof = null;
        }


        this.unloadSprites();

        /*
        // redondant
        if(this.doorLayer != null)
        {
            this.detachChild(this.doorLayer);
            this.doorLayer = null;
        }*/

        this.stones = this.getNewGradient(this.stonesGradientColor1, this.stonesGradientColor2, 10, 9, 20, 18, 1, 0);
        this.attachChild(this.stones);

        this.stoneSpriteLayer = new Entity();
        this.attachChild(this.stoneSpriteLayer);

        this.roof = this.getNewMesh(this.roofColor, this.roofPaperCoords);
        this.attachChild(this.roof);

        IPositionAndSizeOwner palePositionAndSize = this.getTransformedPositionAndSize(10, 15, 20, 20);

        this.doorLayer = new Entity();
        this.attachChild(this.doorLayer);

        if(this.isResourcesPushed()) {
            this.loadSprites();
        }

        this.palesSpriteLayers = new Entity();
        this.attachChild(this.palesSpriteLayers);

        /*this.pales = new WindMillPales(palePositionAndSize.getPosition().getX(),
                palePositionAndSize.getPosition().getY(),
                palePositionAndSize.getSize().getWidth(),
                palePositionAndSize.getSize().getHeight(),
                this.vertexBufferObjectManager,
                this.bladesColor);

        this.attachChild(this.pales);*/
    }

    @Override
    public float getWindStrength()
    {
        return this.windStrength;
    }

    @Override
    public void setWindStrength(float windStrength) {

        this.windStrength = windStrength;
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
    public void pushResourceRequirements() {
        super.pushResourceRequirements();

        this.resourceManager.pushRequirement(this);
    }

    @Override
    public void popResourceRequirements() {
        super.popResourceRequirements();

        this.resourceManager.popRequirement(this);
    }

    IImage getDoorImage()
    {
        IImage retval = com.bitarcher.aeFun.drawables.cliparts.ResourceInfosSingleton.getInstance().getArchitecture().getMedieval().getDoors().getWoodDoor(this.woodDoorNum);

        return retval;
    }

    IImage getStoneImage()
    {
        IImage retval = com.bitarcher.aeFun.drawables.cliparts.ResourceInfosSingleton.getInstance().getArchitecture().getMedieval().getArchitecture().getMedieval().getWalls().getWall(0);

        return retval;
    }

    IImage getPalesImage()
    {
        IImage retval = com.bitarcher.aeFun.drawables.cliparts.ResourceInfosSingleton.getInstance().getArchitecture().getMedieval().getArchitecture().getMedieval().getWindMillFan();

        return retval;
    }


    @Override
    public List<IResourceInfo> getResourceInfoList() {
        ArrayList<IResourceInfo> retval = new ArrayList<>();

        retval.add(this.getDoorImage().getTextureSetResourceInfo());
        retval.add(this.getStoneImage().getTextureSetResourceInfo());
        retval.add(this.getPalesImage().getTextureSetResourceInfo());

        return retval;
    }

    void unloadSprites()
    {
        if(this.doorLayer != null)
        {
            if(this.doorSprite != null) {
                this.doorLayer.detachChild(this.doorSprite);
            }
        }

        this.doorSprite = null;

        if(this.stoneSpriteLayer != null)
        {
            if(this.stoneSprite != null)
            {
                this.stoneSpriteLayer.detachChild(this.stoneSprite);
            }
        }

        this.stoneSprite = null;

        if(this.palesSpriteLayers != null)
        {
            if(this.palesSprite != null)
            {
                this.palesSpriteLayers.detachChild(this.palesSprite);
            }
        }

        this.palesSprite = null;
    }

    void loadSprites()
    {
        if(this.doorLayer != null)
        {
            this.doorSprite = this.getNewSprite(this.resourceManager, this.getDoorImage(), true, 10, 4, 8, 6);
            this.doorLayer.attachChild(this.doorSprite);
        }

        if(this.stoneSpriteLayer != null)
        {
            this.stoneSprite = this.getNewSprite(this.resourceManager, this.getStoneImage(), false, 10, 9, 20, 18);
            this.stoneSpriteLayer.attachChild(this.stoneSprite);
            this.stoneSprite.setAlpha(0.8f);
        }

        if(this.palesSpriteLayers != null)
        {
            this.palesSprite = this.getNewSprite(this.resourceManager, this.getPalesImage(), false, 10, 17, 26, 26);
            this.palesSpriteLayers.attachChild(this.palesSprite);
        }
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

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        this.angle += - pSecondsElapsed * this.windStrength * 30;


        this.palesSprite.setRotation(this.angle);
    }


}
