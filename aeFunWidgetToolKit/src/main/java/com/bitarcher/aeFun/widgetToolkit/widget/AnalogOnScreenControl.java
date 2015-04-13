package com.bitarcher.aeFun.widgetToolkit.widget;

import com.bitarcher.aeFun.interfaces.gui.theme.ENoLayoutFound;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IAnalogOnScreenControlContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.IAnalogOnScreenControlLayout;
import com.bitarcher.aeFun.interfaces.gui.widgets.IAnalogOnScreenControl;

import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;

import java.util.ArrayList;

/**
 * Created by michel on 13/04/15.
 */
public class AnalogOnScreenControl extends Widget<IAnalogOnScreenControlContext> implements IAnalogOnScreenControl, org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener{
    ArrayList<org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener> analogOnScreenControlListenerArrayList = new ArrayList<>();

    public AnalogOnScreenControl(ITheme theme, float pX, float pY, float pWidth, float pHeight) {
        super(theme, pX, pY, pWidth, pHeight);

        if(this.getLayout() != null)
        {
            IAnalogOnScreenControlLayout layout = (IAnalogOnScreenControlLayout)this.getLayout();
            layout.onInit();
            layout.setAnalogOnScreenControlListener(this);
        }
        else
        {
            throw new ENoLayoutFound(this);
        }
    }

    @Override
    public void addAnalogOnScreenControlListener(org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener analogOnScreenControlListener) {
        this.analogOnScreenControlListenerArrayList.add(analogOnScreenControlListener);
    }

    @Override
    public void removeAnalogOnScreenControlListener(org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener analogOnScreenControlListener) {
        this.analogOnScreenControlListenerArrayList.remove(analogOnScreenControlListener);
    }

    @Override
    public void onControlClick(org.andengine.engine.camera.hud.controls.AnalogOnScreenControl pAnalogOnScreenControl) {
        if(this.isEnabled()) {
            for (org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener analogOnScreenControlListener : this.analogOnScreenControlListenerArrayList) {
                analogOnScreenControlListener.onControlClick(pAnalogOnScreenControl);
            }
        }
    }

    @Override
    public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) {
        if(this.isEnabled()) {
            for (org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener analogOnScreenControlListener : this.analogOnScreenControlListenerArrayList) {
                analogOnScreenControlListener.onControlChange(pBaseOnScreenControl, pValueX, pValueY);
            }
        }
    }
}


