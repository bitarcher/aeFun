package com.bitarcher.aeFun.interfaces.drawables.characters;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.basicioc.INamed;
import com.bitarcher.aeFun.interfaces.basicioc.IService;
import com.bitarcher.aeFun.interfaces.geometry.IAspectRatioOwner;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManagerLinked;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceRequirementsStackUser;

import org.andengine.entity.IEntity;

/**
 * Created by michel on 18/03/15.
 */
public interface ICharacter extends IEntity, INamed, IResourceManagerLinked, IResourceRequirementsStackUser, IService, IAspectRatioOwner {
    void setMainPosition(EnumSide side, EnumMainPosition mainPosition);

}
