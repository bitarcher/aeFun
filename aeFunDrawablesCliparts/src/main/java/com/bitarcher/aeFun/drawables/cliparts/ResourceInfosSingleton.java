package com.bitarcher.aeFun.drawables.cliparts;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.cliparts.ArchitectureF.Architecture;

import org.andengine.opengl.texture.TextureManager;

/**
 * Created by michel on 20/04/15.
 */
public class ResourceInfosSingleton {
    private static ResourceInfosSingleton ourInstance = new ResourceInfosSingleton();

    public static ResourceInfosSingleton getInstance() {
        return ourInstance;
    }

    private ResourceInfosSingleton() {
    }

    Architecture architecture;

    public Architecture getArchitecture()
    {
        if(architecture == null)
        {
            this.architecture = new Architecture();
        }

        return this.architecture;
    }
}
