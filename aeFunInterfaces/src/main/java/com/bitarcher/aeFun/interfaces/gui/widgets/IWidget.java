package com.bitarcher.aeFun.interfaces.gui.widgets;

import com.bitarcher.aeFun.interfaces.basicioc.IEnabled;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.gui.theme.ILayoutOwner;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.IPaddable;
import com.bitarcher.aeFun.interfaces.geometry.IPositionable;
import com.bitarcher.aeFun.interfaces.geometry.ISizable;

import org.andengine.entity.IEntity;

/**
 * Created by michel on 21/01/15.
 */
public interface IWidget<TContext extends IContext> extends IEntity, IPositionable, ISizable, IPositionAndSizeOwner, IEnabled, IPaddable, ILayoutOwner<TContext> {
    ITheme getTheme();

    void addWidgetListener(IWidgetListener widgetListener);
    void removeWidgetListener(IWidgetListener widgetListener);

    void onDebugModeChanged(boolean debugModeEnabled);
}
