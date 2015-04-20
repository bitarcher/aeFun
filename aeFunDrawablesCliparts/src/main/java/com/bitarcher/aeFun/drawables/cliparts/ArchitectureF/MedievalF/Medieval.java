package com.bitarcher.aeFun.drawables.cliparts.ArchitectureF.MedievalF;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.cliparts.ArchitectureF.Architecture;
import com.bitarcher.aeFun.drawables.cliparts.ArchitectureF.MedievalF.Door.Doors;

/**
 * Created by michel on 20/04/15.
 */
public class Medieval {

    Architecture architecture;
    Doors doors;

    public Doors getDoors() {
        if(this.doors == null)
        {
            this.doors = new Doors(this);
        }

        return this.doors;
    }

    public Architecture getArchitecture() {
        return architecture;
    }



    public Medieval(Architecture architecture) {
        this.architecture = architecture;
    }
}
