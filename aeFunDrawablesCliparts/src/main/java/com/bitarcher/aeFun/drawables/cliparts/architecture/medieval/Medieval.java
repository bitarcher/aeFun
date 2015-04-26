package com.bitarcher.aeFun.drawables.cliparts.architecture.medieval;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.cliparts.architecture.Architecture;
import com.bitarcher.aeFun.drawables.cliparts.architecture.medieval.houses.Houses;

/**
 * Created by michel on 20/04/15.
 */
public class Medieval {

    Architecture architecture;
    Doors doors;
    Houses houses;

    public Doors getDoors() {
        if(this.doors == null)
        {
            this.doors = new Doors(this);
        }

        return this.doors;
    }

    public Houses getHouses() {
        if(this.houses == null)
        {
            this.houses = new Houses(this);
        }

        return this.houses;
    }

    public Architecture getArchitecture() {
        return architecture;
    }



    public Medieval(Architecture architecture) {
        this.architecture = architecture;
    }
}
