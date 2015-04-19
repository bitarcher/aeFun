package com.bitarcher.aeFun.drawables.animatedMeshed;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.animatedMeshed.Tools.CompositeMeshesBase;
import com.bitarcher.aeFun.drawables.animatedMeshed.Tools.WindMillPales;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.pointsTransformation.SizeAdapterFunction;
import com.bitarcher.aeFun.interfaces.drawables.animatedMeshed.IWindStrength;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.entity.primitive.Gradient;
import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;

/**
 * Created by michel on 18/04/15.
 */
public class WindMill extends CompositeMeshesBase implements IWindStrength {

    float windStrength = 0;
    Color stonesGradientColor1 = new Color(0.9f, 0.85f, 0.8f);
    Color stonesGradientColor2 = new Color(0.7f, 0.7f, 0.7f);
    Color roofColor = new Color(0.95f, 0.4f, 0.45f);
    Color bladesColor = new Color(0.804f, 0.522f, 0.247f); // peru beige
    Gradient stones;
    Mesh roof;
    WindMillPales pales;


    ArrayList<IPoint> roofPaperCoords = new ArrayList<>();

    public WindMill(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(20, 25));

        this.setRoofPaperCoords();
        this.computeStableMeshes();
    }

    public WindMill(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, Color stonesGradientColor1, Color stonesGradientColor2, Color roofColor, Color bladesColor) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(20, 25));
        this.stonesGradientColor1 = stonesGradientColor1;
        this.stonesGradientColor2 = stonesGradientColor2;
        this.roofColor = roofColor;
        this.bladesColor = bladesColor;

        this.setRoofPaperCoords();
        this.computeStableMeshes();
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

        if(this.pales != null)
        {
            this.detachChild(this.pales);
            this.pales = null;
        }

        /*SizeAdapterFunction sizeAdapterFunction = new SizeAdapterFunction(new Size(20, 25), new Size(this.getWidth(), this.getHeight()));
        IPoint stonesCenter = sizeAdapterFunction.getYByX(new Point(10, 9));
        IPoint stonesSize = sizeAdapterFunction.getYByX(new Point(20, 18));
*/
  /*      this.stones = new Gradient(stonesCenter.getX(), stonesCenter.getY(), stonesSize.getX(), stonesSize.getY(), vertexBufferObjectManager);
        this.stones.setFromColor(this.stonesGradientColor1);
        this.stones.setToColor(this.stonesGradientColor2);*/

        this.stones = this.getNewGradient(this.stonesGradientColor1, this.stonesGradientColor2, 10, 9, 20, 18, 1, 0);
        this.attachChild(this.stones);

        this.roof = this.getNewMesh(this.roofColor, this.roofPaperCoords);
        this.attachChild(this.roof);

        //this.pales = new WindMillPales(10)
    }

    @Override
    public float getWindStrength()
    {
        return this.pales != null?this.pales.getWindStrength():this.windStrength;
    }

    @Override
    public void setWindStrength(float windStrength) {

        this.windStrength = windStrength;

        if(this.pales != null)
        {
            this.pales.setWindStrength(windStrength);
        }
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
}
