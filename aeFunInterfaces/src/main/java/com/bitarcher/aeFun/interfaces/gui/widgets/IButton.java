package com.bitarcher.aeFun.interfaces.gui.widgets;

import com.bitarcher.aeFun.interfaces.gui.theme.context.IButtonContext;


/**
 * Created by michel on 22/01/15.
 */
public interface IButton<TContext extends IButtonContext> extends IWidget<TContext> {
    boolean isMousePressed();

    void addButtonListener(IButtonListener buttonListener);
    void removeButtonListener(IButtonListener buttonListener);
}
