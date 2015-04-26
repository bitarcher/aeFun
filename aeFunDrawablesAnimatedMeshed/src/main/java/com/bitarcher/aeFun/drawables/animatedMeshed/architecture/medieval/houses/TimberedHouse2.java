package com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses;

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;

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
public class TimberedHouse2 extends BackgroundedHouseBase
{

    public TimberedHouse2(IResourceManager resourceManager, float pX, float pY, float pWidth, float pHeight, Color backgroundColor) {
        super(resourceManager, pX, pY, pWidth, pHeight, backgroundColor, new Size(1752, 1008));
    }

    @Override
    protected IImage getImage()
    {
        IImage retval = com.bitarcher.aeFun.drawables.cliparts.ResourceInfosSingleton.getInstance().getArchitecture().getMedieval().getHouses().getTimbered().getTimbered(2);

        return retval;
    }

    @Override
    protected List<IPoint> getBackgroundMeshPoints() {
        ArrayList<IPoint> retval = new ArrayList<>();

        retval.add(new Point(42, 952));
        retval.add(new Point(44, 754));

        retval.add(new Point(724, 922));
        retval.add(new Point(717, 676));

        retval.add(new Point(1696, 898));
        retval.add(new Point(1688, 711));

        return retval;
    }
}
