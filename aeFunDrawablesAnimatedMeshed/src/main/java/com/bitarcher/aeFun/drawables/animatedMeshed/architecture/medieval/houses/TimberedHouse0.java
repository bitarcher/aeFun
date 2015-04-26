package com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses;

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;

import org.andengine.opengl.vbo.VertexBufferObjectManager;
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
public class TimberedHouse0 extends BackgroundedHouseBase
{

    public TimberedHouse0(IResourceManager resourceManager, float pX, float pY, float pWidth, float pHeight, Color backgroundColor) {
        super(resourceManager, pX, pY, pWidth, pHeight, backgroundColor, new Size(547, 732));
    }

    @Override
    protected IImage getImage()
    {
        IImage retval = com.bitarcher.aeFun.drawables.cliparts.ResourceInfosSingleton.getInstance().getArchitecture().getMedieval().getHouses().getTimbered().getTimbered(0);

        return retval;
    }

    @Override
    protected List<IPoint> getBackgroundMeshPoints() {
        ArrayList<IPoint> retval = new ArrayList<>();

        retval.add(new Point(0, 732));
        retval.add(new Point(547, 732));

        retval.add(new Point(4, 276));
        retval.add(new Point(529, 225));

        retval.add(new Point(241, 0));
        retval.add(new Point(321, 0));

        return retval;
    }
}
