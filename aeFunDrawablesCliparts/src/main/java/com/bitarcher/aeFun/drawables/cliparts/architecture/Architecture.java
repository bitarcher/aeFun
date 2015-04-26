package com.bitarcher.aeFun.drawables.cliparts.architecture;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.cliparts.architecture.medieval.Medieval;

/**
 * Created by michel on 20/04/15.
 */
public class Architecture {

    Medieval medieval;

    public Medieval getMedieval() {
        if(this.medieval == null)
        {
            this.medieval = new Medieval(this);
        }

        return medieval;
    }
}
