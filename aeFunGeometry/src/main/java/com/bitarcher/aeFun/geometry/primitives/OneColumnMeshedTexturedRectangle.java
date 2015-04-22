package com.bitarcher.aeFun.geometry.primitives;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.PositionAndSizeOwner;

import com.bitarcher.aeFun.geometry.primitives.helpers.OneColumnMeshedRectangleHelper;
import com.bitarcher.aeFun.geometry.primitives.helpers.TexturedMeshHelper;
import com.bitarcher.aeFun.geometry.primitives.nazgees.TexturedMesh;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;

import org.andengine.entity.Entity;

import org.andengine.entity.primitive.DrawMode;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by michel on 21/04/15.
 */

@Deprecated
/**
 * FIXME not working as expected
 */
public class OneColumnMeshedTexturedRectangle  extends Entity {
    int numOfTriangles;
    VertexBufferObjectManager vertexBufferObjectManager;
    TexturedMesh texturedMesh;
    ITextureRegion textureRegion;

    public OneColumnMeshedTexturedRectangle(float x, float y, float width, float height, int numOfTriangles, VertexBufferObjectManager vertexBufferObjectManager, ITextureRegion textureRegion)
    {
        this(new PositionAndSizeOwner(x, y, width, height), numOfTriangles, vertexBufferObjectManager, textureRegion);
    }

    public OneColumnMeshedTexturedRectangle(IPositionAndSizeOwner positionAndSizeOwner, int numOfTriangles, VertexBufferObjectManager vertexBufferObjectManager, ITextureRegion textureRegion)
    {
        this.textureRegion = textureRegion;
        this.numOfTriangles = numOfTriangles;
        this.vertexBufferObjectManager = vertexBufferObjectManager;

        OneColumnMeshedRectangleHelper helper = new OneColumnMeshedRectangleHelper(positionAndSizeOwner);
        float[] verticesPoints = helper.getVerticesBufferData2VForTrianglesMode(numOfTriangles);

        TexturedMeshHelper texturedMeshHelper = new TexturedMeshHelper();
        float[] data = texturedMeshHelper.getMeshBufferDataFromVertices(verticesPoints);


        this.texturedMesh = new TexturedMesh(0f, 0f, data, verticesPoints.length / 2, DrawMode.TRIANGLES, vertexBufferObjectManager);
        this.attachChild(this.texturedMesh);
    }
}

