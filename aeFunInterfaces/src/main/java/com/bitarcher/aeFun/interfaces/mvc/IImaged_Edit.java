package com.bitarcher.aeFun.interfaces.mvc;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 12/03/15.
 */
public interface IImaged_Edit extends IImaged {
    void setImage(IImage image);

    void addImagedListener(IImagedListener imagedListener);
    void removeImagedListener(IImagedListener imagedListener);
}
