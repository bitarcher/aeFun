package com.bitarcher.aeFun.drawables.cliparts.architecture.medieval.houses;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.cliparts.architecture.medieval.Medieval;

/**
 * Created by michel on 26/04/15.
 */
public class Houses {
    Medieval medieval;
    Timbered timbered;

    public Houses(Medieval medieval) {
        this.medieval = medieval;
        this.timbered = new Timbered(this);
    }

    public Timbered getTimbered() {
        return timbered;
    }

    public Medieval getMedieval() {
        return medieval;
    }
}
