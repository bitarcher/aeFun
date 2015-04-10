package com.bitarcher.aeFun.sceneManagement;

import com.bitarcher.aeFun.interfaces.sceneManagement.IManagedLayer;



public abstract class ManagedLayer extends HUD  implements IManagedLayer{
	// Is set TRUE if the layer is loaded.
    private boolean hasLoaded = false;
	// Set by the constructor, if true, the layer will be unloaded after being hidden.
    private boolean unloadOnHidden;
	// Convenience constructor. Creates a layer that does not unload when hidden.
	public ManagedLayer() {
		this(false);
	}
	// Constructor. Sets whether the layer will unload when hidden and ensures that there is no background on the layer.
	public ManagedLayer(boolean pUnloadOnHidden) {
		setUnloadOnHidden(pUnloadOnHidden);
		this.setBackgroundEnabled(false);
	}
	// If the layer is not loaded, load it. Ensure that the layer is not paused.
	public void onShowManagedLayer() {
		if(!getHasLoaded()) {
			setHasLoaded(true);
			onLoadLayer();
		}
		this.setIgnoreUpdate(false);
		onShowLayer();
	}
	// Pause the layer, hide it, and unload it if it needs to be unloaded.
	public void onHideManagedLayer() {
		this.setIgnoreUpdate(true);
		onHideLayer();
		if(isUnloadOnHidden()) {
			onUnloadLayer();
		}
	}
	// Methods to override in subclasses.
	public abstract void onLoadLayer();
	public abstract void onShowLayer();
	public abstract void onHideLayer();
	public abstract void onUnloadLayer();

    public boolean getHasLoaded() {
        return hasLoaded;
    }

    public void setHasLoaded(boolean hasLoaded) {
        this.hasLoaded = hasLoaded;
    }

    public boolean isUnloadOnHidden() {
        return unloadOnHidden;
    }

    public void setUnloadOnHidden(boolean unloadOnHidden) {
        this.unloadOnHidden = unloadOnHidden;
    }


}