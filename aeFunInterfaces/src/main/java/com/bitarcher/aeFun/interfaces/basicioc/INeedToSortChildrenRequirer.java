package com.bitarcher.aeFun.interfaces.basicioc;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 30/04/15.
 */
public interface INeedToSortChildrenRequirer {
    void addNeedToSortChildrenListener(INeedToSortChildrenListener needToSortChildrenListener);
    void removeNeedToSortChildrenListener(INeedToSortChildrenListener needToSortChildrenListener);
}
