package com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;

import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michel on 25/04/15.
 */
public class TimberedHouse1 extends BackgroundedHouseBase
{

    public TimberedHouse1(IResourceManager resourceManager, float pX, float pY, float pWidth, float pHeight, Color backgroundColor) {
        super(resourceManager, pX, pY, pWidth, pHeight, backgroundColor, new Size(1386, 1494));
    }

    @Override
    protected IImage getImage()
    {
        IImage retval = com.bitarcher.aeFun.drawables.cliparts.ResourceInfosSingleton.getInstance().getArchitecture().getMedieval().getHouses().getTimbered().getTimbered(1);

        return retval;
    }

    @Override
    protected List<IPoint> getBackgroundMeshPoints() {
        ArrayList<IPoint> retval = new ArrayList<>();

        retval.add(new Point(164, 1460));
        retval.add(new Point(1282, 1473));

        retval.add(new Point(174, 724));
        retval.add(new Point(1263, 749));

        retval.add(new Point(624, 123));
        retval.add(new Point(785, 117));

        return retval;
    }
}
