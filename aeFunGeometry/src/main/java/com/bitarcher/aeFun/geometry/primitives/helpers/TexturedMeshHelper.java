package com.bitarcher.aeFun.geometry.primitives.helpers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.primitives.nazgees.TexturedMesh;

/**
 * Created by michel on 22/04/15.
 */
public class TexturedMeshHelper {
    public float[] getMeshBufferDataFromVertices(float[] vertices2V)
    {
        float[] retval = new float[TexturedMesh.VERTEX_SIZE * (vertices2V.length / 2)];

        for(int i=0 ; i < vertices2V.length / 2 ; i++)
        {
            retval[(i * TexturedMesh.VERTEX_SIZE) + TexturedMesh.VERTEX_INDEX_X] = vertices2V[i * 2];
            retval[(i * TexturedMesh.VERTEX_SIZE) + TexturedMesh.VERTEX_INDEX_Y] = vertices2V[i * 2 + 1];
        }

        return retval;
    }
}

