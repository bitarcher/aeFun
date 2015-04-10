package com.bitarcher.aeFun.widgetToolkit.widget.Tools;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IButtonContext;
import com.bitarcher.aeFun.interfaces.gui.theme.context.setter.EnumMouseEffect;
import com.bitarcher.aeFun.interfaces.gui.widgets.IButton;
import com.bitarcher.aeFun.interfaces.gui.widgets.IButtonListener;
import com.bitarcher.aeFun.widgetToolkit.widget.Widget;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import java.util.ArrayList;

/**
 * Created by michel on 22/01/15.
 */
public abstract class Button<TContext extends IButtonContext> extends Widget<TContext> implements IButton<TContext>{

    protected boolean isTouchAreaRegistered = false;
    protected boolean isMousePressedPred;

    @Override
    public boolean isMousePressed() {
        return this.isMousePressedPred;
    }

    protected ArrayList<IButtonListener> buttonListenerArrayList=new ArrayList<>();

    protected Button(ITheme theme, float pX, float pY, float pWidth, float pHeight) {
        super(theme, pX, pY, pWidth, pHeight);
    }

    @Override
    public void addButtonListener(IButtonListener buttonListener) {
        this.buttonListenerArrayList.add(buttonListener);
    }

    @Override
    public void removeButtonListener(IButtonListener buttonListener) {
        this.buttonListenerArrayList.remove(buttonListener);
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

        boolean retval = false;
        if(this.isEnabled()) {
            retval = true;

            if (pSceneTouchEvent.isActionDown()) {
                this.isMousePressedPred = true;

                if(this.getLayout() != null)
                {
                    this.getLayout().getContext().setMouseEffect(EnumMouseEffect.MousePressed);
                }

                this.onButtonClicked();
            }
            else if(pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel() || pSceneTouchEvent.isActionOutside())
            {
                this.isMousePressedPred = false;

                if(this.getLayout() != null)
                {
                    this.getLayout().getContext().setMouseEffect(EnumMouseEffect.None);
                }
            }
        }

        return retval;
    }

    private void onButtonClicked()
    {

        for(IButtonListener buttonListener : this.buttonListenerArrayList)
        {
            buttonListener.onClicked(this);
        }

        this.onClicked();

        if(this.getRootEntity() == null || this.getRootEntity() != this.getTheme().getThemeManager().getResourceManager().getEngine().getScene())
        {
            this.resetMousePressed();
        }
    }

    protected void onClicked()
    {

    }

    @Override
    public void onAttached() {
        super.onAttached();

        if(!this.isTouchAreaRegistered)
        {
            Scene scene = this.getTheme().getThemeManager().getResourceManager().getEngine().getScene();

            scene.registerTouchArea(this);
            this.isTouchAreaRegistered = true;
        }
    }

    @Override
    public void onDetached() {
        super.onDetached();

        if(this.isTouchAreaRegistered)
        {
            Scene scene = this.getTheme().getThemeManager().getResourceManager().getEngine().getScene();

            scene.unregisterTouchArea(this);

            this.isTouchAreaRegistered = false;
        }

        this.resetMousePressed();
    }

    void resetMousePressed()
    {
        this.isMousePressedPred = false;
        if(this.getLayout() != null)
        {
            this.getLayout().getContext().setMouseEffect(EnumMouseEffect.None);
        }

    }

    @Override
    public void dispose() {
        super.dispose();

        this.buttonListenerArrayList.clear();
    }
}
