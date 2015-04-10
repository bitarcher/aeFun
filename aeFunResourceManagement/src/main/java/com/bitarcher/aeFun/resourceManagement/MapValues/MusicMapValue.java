package com.bitarcher.aeFun.resourceManagement.MapValues;

import android.util.Log;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IMusicResourceInfo;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;

import java.io.IOException;

/**
 * Created by michel on 12/01/15.
 */
public class MusicMapValue extends TMapValue<Music> {

    IResourceManager resourceManager;

    public MusicMapValue(IResourceManager resourceManager, IMusicResourceInfo musicResourceInfo) {
        this.resourceManager = resourceManager;

        try {
            // Create the clickMusic object via the MusicFactory class
            this._tObj	= MusicFactory.createMusicFromAsset(resourceManager.getEngine().getMusicManager(), resourceManager.getContext(), musicResourceInfo.getAssetPath());
        } catch (final IOException e) {
            Log.v("Musics Load", "Exception:" + e.getMessage());
        }
    }

    @Override
    public void clean() {
        if(this._tObj!=null) {
            if (!this._tObj.isReleased()) {

                this._tObj.stop();
                this._tObj.release();
                this.resourceManager.getEngine().getMusicManager().remove(this._tObj);
                this._tObj = null;
            }
        }
    }
}
