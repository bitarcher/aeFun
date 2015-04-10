package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */


import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.IFixedSpaceUsage;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.IPercentSpaceUsage;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.ISpaceUsage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michel on 04/03/15.
 */
public class ScalarComputerBySpaceUsage {
    public void compute(List<? extends ISpaceUsageOwnerAndScalar> spaceUsageOwnerAndScalarList, float availableSpace, float padding, boolean isShouldFixedSpaceUsageBeResizedOnResize)
    {
        ArrayList<ISpaceUsageOwnerAndScalar> fixedList = new ArrayList<>();
        ArrayList<ISpaceUsageOwnerAndScalar> percentList = new ArrayList<>();
        float percentListTotal = 0;
        float fixedListTotal = 0;

        for(ISpaceUsageOwnerAndScalar spaceUsageOwnerAndScalar: spaceUsageOwnerAndScalarList)
        {
            ISpaceUsage spaceUsage = spaceUsageOwnerAndScalar.getSpaceUsage();

            if(spaceUsage instanceof IPercentSpaceUsage)
            {
                IPercentSpaceUsage percentSpaceUsage = (IPercentSpaceUsage) spaceUsage;
                percentList.add(spaceUsageOwnerAndScalar);
                percentListTotal+= percentSpaceUsage.getPercentSpaceUsage();
            }
            else if(spaceUsage instanceof IFixedSpaceUsage)
            {
                IFixedSpaceUsage fixedSpaceUsage = (IFixedSpaceUsage)spaceUsage;
                fixedList.add(spaceUsageOwnerAndScalar);
                fixedListTotal+=fixedSpaceUsage.getFixedSpaceUsage();
            }
            else
            {
                throw new RuntimeException("unsupported spaceUsage");
            }
        }

        float variableScalarMinusTwoPadding = 0;




        variableScalarMinusTwoPadding = availableSpace - 2 * padding;
        float scalarThatCanBeSharedByPercents = variableScalarMinusTwoPadding - fixedListTotal;

        if(scalarThatCanBeSharedByPercents < 0)
            scalarThatCanBeSharedByPercents = 0;

        boolean needsPercentWidgetToBeConsidered = true;
        needsPercentWidgetToBeConsidered = fixedListTotal < variableScalarMinusTwoPadding;


        // set scalar (with margin)

        for(ISpaceUsageOwnerAndScalar spaceUsageOwnerAndScalar : percentList)
        {
            IPercentSpaceUsage percentSpaceUsage = (IPercentSpaceUsage) spaceUsageOwnerAndScalar.getSpaceUsage();
            float scalar = scalarThatCanBeSharedByPercents * (percentSpaceUsage.getPercentSpaceUsage() / percentListTotal);

            spaceUsageOwnerAndScalar.setScalar(scalar);
        }


        float consumedScalar = 0;
        boolean shouldScalarBeSetToZero = false;

        for (ISpaceUsageOwnerAndScalar spaceUsageOwnerAndScalar : fixedList) {
            IFixedSpaceUsage fixedSpaceUsage = (IFixedSpaceUsage) spaceUsageOwnerAndScalar.getSpaceUsage();
            float scalar = 0;


            if(isShouldFixedSpaceUsageBeResizedOnResize)
            {
                if(needsPercentWidgetToBeConsidered)
                {
                    // we have enough space for fixed AND percent, so we use the fixed size

                    scalar = fixedSpaceUsage.getFixedSpaceUsage();
                }
                else
                {
                    // we don't have enough space for fixed AND percent, so percent's scalars will be zero
                    scalar = scalarThatCanBeSharedByPercents * (fixedSpaceUsage.getFixedSpaceUsage() / fixedListTotal);
                }
            }
            else {
                if(shouldScalarBeSetToZero)
                {
                    scalar = 0;
                }
                else {
                    scalar = fixedSpaceUsage.getFixedSpaceUsage();

                    /*
                    Note : we don't use the following code, we prefer using clipping

                    if ((consumedScalar + scalar) > variableScalarMinusTwoPadding) {
                        // this one will be reduced and consecutive will be set to zero
                        scalar = consumedScalar + scalar - variableScalarMinusTwoPadding;
                        shouldScalarBeSetToZero = true;
                    }
                    */
                }
            }

            consumedScalar += scalar;
            spaceUsageOwnerAndScalar.setScalar(scalar);
        }
    }
}

