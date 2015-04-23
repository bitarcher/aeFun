package com.bitarcher.aeFun.drawables.animatedMeshed.Tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.PositionAndSizeOwner;
import com.bitarcher.aeFun.geometry.Size;

import com.bitarcher.aeFun.geometry.Vector;
import com.bitarcher.aeFun.geometry.pointsTransformation.Pipeline;
import com.bitarcher.aeFun.geometry.pointsTransformation.SizeAdapterFunction;
import com.bitarcher.aeFun.geometry.primitives.BezierEllipsoid;
import com.bitarcher.aeFun.geometry.primitives.BezierFilledEllipsoid;
import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.geometry.primitives.helpers.OneColumnMeshedRectangleHelper;
import com.bitarcher.aeFun.geometry.primitives.helpers.TexturedMeshHelper;
import com.bitarcher.aeFun.geometry.primitives.nazgees.TexturedMesh;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.geometry.IVector;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceRequirementsStackUser;


import org.andengine.entity.Entity;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Gradient;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.list.SmartList;

import java.util.List;


/**
 * Created by michel on 17/04/15.
 */
public abstract class CompositeMeshesBase extends Entity implements IResourceRequirementsStackUser{
    protected ISize paperModelSize;
    protected VertexBufferObjectManager vertexBufferObjectManager;
    boolean resourcesPushed = false;

    public boolean isResourcesPushed() {
        return resourcesPushed;
    }

    protected CompositeMeshesBase(VertexBufferObjectManager vertexBufferObjectManager, ISize paperModelSize) {
        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.paperModelSize = paperModelSize;
    }

    protected CompositeMeshesBase(float pX, float pY, VertexBufferObjectManager vertexBufferObjectManager, ISize paperModelSize) {
        super(pX, pY);
        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.paperModelSize = paperModelSize;
    }

    protected CompositeMeshesBase(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, ISize paperModelSize) {
        super(pX, pY, pWidth, pHeight);

        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.paperModelSize = paperModelSize;
    }

    protected SizeAdapterFunction getNewPaperToOutputSizeAdapterFunction()
    {
        SizeAdapterFunction retval = new SizeAdapterFunction(this.paperModelSize, new Size(this.getWidth(), this.getHeight()));

        return retval;
    }

    protected Pipeline getNewPipeline()
    {
        Pipeline retval = new Pipeline();

        retval.appendFunction(this.getNewPaperToOutputSizeAdapterFunction());

        return retval;
    }



    protected Mesh getNewMesh(Color meshColor, List<IPoint> twoDPaperCoordinates, DrawMode drawMode)
    {
        Mesh retval =null;

        Pipeline pipeline = this.getNewPipeline();
        float[] transformedPoints = pipeline.applyAndGet3VfPoints(twoDPaperCoordinates);

        retval = new Mesh(0, 0, transformedPoints, transformedPoints.length / 3, drawMode, this.vertexBufferObjectManager);
        retval.setColor(meshColor);

        return retval;
    }

    /**
     *
     * @param meshColor
     * @param twoDPaperCoordinates ordered like TRIANGLE_STRIP
     * @return
     */
    protected Mesh getNewMesh(Color meshColor, List<IPoint> twoDPaperCoordinates)
    {
        return this.getNewMesh(meshColor, twoDPaperCoordinates, DrawMode.TRIANGLE_STRIP);
    }

    /*
    protected TexturedMesh getNewTexturedMesh(ITextureRegion textureRegion, List<IPoint> twoDPaperCoordinates) {

        return this.getNewTexturedMesh(this.getNewPipeline(), textureRegion, twoDPaperCoordinates);
    }



    protected TexturedMesh getNewTexturedMesh(Pipeline pipeline, ITextureRegion textureRegion, List<IPoint> twoDPaperCoordinates)
    {
        TexturedMesh retval =null;

        float[] transformedPoints = pipeline.applyAndGet2VfPoints(twoDPaperCoordinates);

        retval = new TexturedMesh(0, 0, transformedPoints, transformedPoints.length / 3, DrawMode.TRIANGLE_STRIP, textureRegion, this.vertexBufferObjectManager);

        return retval;
    }
    */

    protected TexturedMesh getNewOneColumnTextureMeshed(ITextureRegion textureRegion, float x, float y, float width, float height, int numOfTriangles)
    {
        OneColumnMeshedRectangleHelper helper = new OneColumnMeshedRectangleHelper(x, y, width, height);

        Pipeline pipeline = this.getNewPipeline();

        List<IPoint> notTransformedVertices = helper.getTrianglesModePoints(numOfTriangles);
        SmartList<IPoint> transformedList = pipeline.applyOnList(notTransformedVertices);

        float[] verticesPoints = new float[transformedList.size() * 2];

        int i = 0;
        for(IPoint point : transformedList)
        {
            verticesPoints[i] = point.getX();
            i++;
            verticesPoints[i] = point.getY();
            i++;
        }

        TexturedMeshHelper texturedMeshHelper = new TexturedMeshHelper();
        float[] data = texturedMeshHelper.getMeshBufferDataFromVertices(verticesPoints);

        TexturedMesh retval = new TexturedMesh(0, 0, data, verticesPoints.length / 2, DrawMode.TRIANGLES, textureRegion, vertexBufferObjectManager);

        return retval;
    }

    protected BezierFilledEllipsoid getBezierFilledEllipsoid(Color meshColor, List<IPoint> twoDPaperCoordinates, IPoint ellipsoidTriangleFanCenterDesign, int numOfAdditionnalPointsBetweenPoints)
    {
        Pipeline pipeline = this.getNewPipeline();

        IPoint centerTransformed = pipeline.getYByX(ellipsoidTriangleFanCenterDesign);
        SmartList<IPoint> transformedList = pipeline.applyOnList(twoDPaperCoordinates);

        BezierFilledEllipsoid retval = new BezierFilledEllipsoid(0, 0, centerTransformed, transformedList, numOfAdditionnalPointsBetweenPoints, this.vertexBufferObjectManager);

        retval.setColor(meshColor);

        return retval;
    }

    protected IPositionAndSizeOwner getTransformedPositionAndSize(float centerX, float centerY, float width, float height)
    {
        Pipeline pipeline = this.getNewPipeline();

        return this.getTransformedPositionAndSize(pipeline, centerX, centerY, width, height);
    }

    protected IPositionAndSizeOwner getTransformedPositionAndSize(Pipeline pipeline, float centerX, float centerY, float width, float height)
    {
        IPoint centerTransformed = pipeline.getYByX(new Point(centerX, centerY));
        float w2 = width / 2;
        float h2 = height / 2;


        float startX = centerX - w2;
        float startY = centerY - h2;
        float endX = centerX + w2;
        float endY = centerY + h2;

        IPoint tStartPoint = pipeline.getYByX(new Point(startX, startY));
        IPoint tEndPoint = pipeline.getYByX(new Point(endX, endY));

        float tWidth = Math.abs(tEndPoint.getX() - tStartPoint.getX());
        float tHeight = Math.abs(tEndPoint.getY() - tStartPoint.getY());

        PositionAndSizeOwner retval = new PositionAndSizeOwner(centerTransformed.getX(), centerTransformed.getY(), tWidth, tHeight);

        return retval;
    }

    protected DiskOrXGon getNewDiskOrXGon(Color meshColor, float centerX, float centerY, float width, float height, int numOfFanSectors)
    {
        Pipeline pipeline = this.getNewPipeline();

        IPositionAndSizeOwner transformedPositionAndSize = this.getTransformedPositionAndSize(pipeline, centerX, centerY, width, height);
        float tw = transformedPositionAndSize.getSize().getWidth();
        float th = transformedPositionAndSize.getSize().getHeight();
        float length = (float)Math.sqrt(tw * tw + th * th);

        DiskOrXGon retval = new DiskOrXGon(transformedPositionAndSize.getPosition().getX(), transformedPositionAndSize.getPosition().getY(),
                length, length,
                numOfFanSectors, this.vertexBufferObjectManager);

        retval.setColor(meshColor);

        return retval;
    }

    /**
     *
     * @param resourceManager
     * @param image
     * @param conserveAspectRatio
     * @param centerX
     * @param centerY
     * @param width
     * @param height not used if conserveAspectRatio = true
     * @return
     */
    protected Sprite getNewSprite(IResourceManager resourceManager, IImage image, boolean conserveAspectRatio, float centerX, float centerY, float width, float height)
    {
        Pipeline pipeline = this.getNewPipeline();

        IPositionAndSizeOwner transformedPositionAndSize = this.getTransformedPositionAndSize(pipeline, centerX, centerY, width, height);
        float tw = transformedPositionAndSize.getSize().getWidth();
        float th = transformedPositionAndSize.getSize().getHeight();

        ITextureRegion textureRegion = resourceManager.getTextureRegionFromTextureSetByName(image.getTextureSetResourceInfo(), image.getTextureName());

        float usedHeight = th;

        if(conserveAspectRatio)
        {
            usedHeight = tw / image.getAspectRatio();
        }

        Sprite retval = new Sprite(
                transformedPositionAndSize.getPosition().getX(),
                transformedPositionAndSize.getPosition().getY(),
                tw, usedHeight, textureRegion, resourceManager.getEngine().getVertexBufferObjectManager()
                );

        return retval;
    }

    protected Gradient getNewGradient(Color fromColor, Color toColor, float centerX, float centerY, float width, float height, float vectorX, float vectorY)
    {
        Pipeline pipeline = this.getNewPipeline();

        IPositionAndSizeOwner transformedPositionAndSize = this.getTransformedPositionAndSize(pipeline, centerX, centerY, width, height);

        Gradient retval = new Gradient(transformedPositionAndSize.getPosition().getX(), transformedPositionAndSize.getPosition().getY(),
                transformedPositionAndSize.getSize().getWidth(), transformedPositionAndSize.getSize().getHeight(),
                this.vertexBufferObjectManager);




        float epsilon = 0.0000001f;
        Vector originalVector = new Vector(vectorX, vectorY);
        IVector reducedNormVector = originalVector.getNewVectorWithSameDirectionAndWuthTheFollowingNorm(epsilon);
        Point oCenter = new Point(centerX, centerY);

        IPoint oCenterPlusVector = oCenter.translate(reducedNormVector);

        IPoint tCenter = pipeline.getYByX(oCenter);
        IPoint tCenterPlusVector = pipeline.getYByX(oCenterPlusVector);

        IVector tVector = tCenterPlusVector.substract(tCenter);

        retval.setGradientVector(tVector.getX(), tVector.getY());

        retval.setFromColor(fromColor);
        retval.setToColor(toColor);

        return retval;
    }



    protected BezierEllipsoid getNewBezierEllipsoid(Color meshColor, List<IPoint> twoDPaperCoordinates, int numOfAdditionnalPointsBetweenPoints)
    {
        Pipeline pipeline = this.getNewPipeline();

        SmartList<IPoint> transformedList = pipeline.applyOnList(twoDPaperCoordinates);

        BezierEllipsoid retval = new BezierEllipsoid(0, 0, transformedList, numOfAdditionnalPointsBetweenPoints, this.vertexBufferObjectManager);

        retval.setColor(meshColor);

        return retval;
    }

    @Override
    public void pushResourceRequirements() {

    }

    @Override
    public void popResourceRequirements() {

    }

    @Override
    public void onAttached() {
        super.onAttached();

        if(!this.resourcesPushed)
        {
            this.pushResourceRequirements();
            this.resourcesPushed = true;
        }
    }

    @Override
    public void onDetached() {
        super.onDetached();

        if(this.resourcesPushed)
        {
            this.popResourceRequirements();
            this.resourcesPushed = false;
        }
    }

    @Override
    public void dispose() {
        super.dispose();

        if(this.resourcesPushed)
        {
            this.popResourceRequirements();
            this.resourcesPushed = false;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        if(this.resourcesPushed)
        {
            this.popResourceRequirements();
            this.resourcesPushed = false;
        }
    }
}
