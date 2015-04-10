/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 *//*


package com.bitarcher.aeFun.widgetToolkit.widget;

import android.util.Log;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.widgets.IScrollingMenu;
import com.bitarcher.aeFun.interfaces.gui.widgets.IScrollingMenuListener;
import com.bitarcher.aeFun.interfaces.mvc.IImagedAndLabeled;
import com.bitarcher.aeFun.interfaces.resourcemanagement.EResourceNotFound;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.ScrollingMenu.OverClippedContainer;


import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ClickDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by michel on 23/02/15.
 * adapted from https://github.com/gorlok/AndEngineMenuScrollDemo/blob/master/src/com/example/menuscrolldemo/MenuScrollDemo.java
 * which is also adapted from
 * @see <a href="http://www.andengine.org/forums/tutorials/menu-scroll-example-t5740.html">original source</a>
 *//*

public class ScrollingMenu extends Widget implements IScrollingMenu, ScrollDetector.IScrollDetectorListener, IOnSceneTouchListener,
        ClickDetector.IClickDetectorListener {

    OverClippedContainer overClippedContainer;
    ArrayList<IImagedAndLabeled> imagedAndLabeledList = new ArrayList<>();
    ArrayList<IScrollingMenuListener> scrollingMenuListenersList = new ArrayList<>();
    private ITextureRegion menuLeftTextureRegion;
    private ITextureRegion menuRightTextureRegion;
    private Sprite menuLeft;
    private Sprite menuRight;
    private Scene mScene;
    // Scrolling
    private SurfaceScrollDetector mScrollDetector;
    private ClickDetector mClickDetector;
    private float mMinX = 0;
    private float mMaxX = 0;
    private float mCurrentX = 0;
    private int iItemClicked = -1;
    private Rectangle scrollBar;
    protected static int PADDING = 50;

    @Override
    public List<IImagedAndLabeled> getImagedAndLabeledList() {
        return this.imagedAndLabeledList;
    }

    @Override
    public void addScrollingMenuListener(IScrollingMenuListener scrollingMenuListener) {
        this.scrollingMenuListenersList.add(scrollingMenuListener);
    }

    @Override
    public void removeScrollingMenuListener(IScrollingMenuListener scrollingMenuListener) {
        this.scrollingMenuListenersList.remove(scrollingMenuListener);
    }

    public ScrollingMenu(ITheme theme, float pX, float pY, float pWidth, float pHeight) {
        super(theme, pX, pY, pWidth, pHeight);


        this.mScrollDetector = new SurfaceScrollDetector(this);
        this.mClickDetector = new ClickDetector(this);

    }

    @Override
    public void setWidth(float pWidth) {
        super.setWidth(pWidth);
    }

    protected int getGoLeftOrRightWidthOrHeight()
    {
        return 120;
    }

    private void createMenuBoxes() {
        int spriteX = PADDING;
        // current item counter
        int iItem = 1;


        IResourceManager resourceManager = this.getTheme().getThemeManager().getResourceManager();

        this.menuLeftTextureRegion = resourceManager.getTextureRegionFromTextureSetByName(this.getTheme().getArrows().getArrowsTexturesSetResourceInfo(), "left");
        this.menuRightTextureRegion = resourceManager.getTextureRegionFromTextureSetByName(this.getTheme().getArrows().getArrowsTexturesSetResourceInfo(), "right");

        final Camera camera = resourceManager.getEngine().getCamera();

        int listSize = this.getImagedAndLabeledList().size();

        if(listSize == 0)
        {
            Log.w("Widget:ScrollingMenu", "Image and labeled list size was null on box creation");
        }

        this.overClippedContainer = new OverClippedContainer(this.getTheme(), resourceManager.getEngine().getScene(), 0, 0, this.getWidth(), this.getHeight() / 2, imagedAndLabeledList);
        this.attachChild(this.overClippedContainer);

        mMaxX = spriteX - this.getWidth();
        // set the size of the scrollbar
        float scrollbarSize = this.getWidth() / ((mMaxX + this.getWidth()) / this.getWidth());
        scrollBar = new Rectangle(scrollbarSize / 2, 10, scrollbarSize, 20, resourceManager.getEngine().getVertexBufferObjectManager());
        scrollBar.setColor(1, 0, 0);
        //this.mScene.attachChild(scrollBar);
        final ScrollingMenu scrollingMenu = this;
        menuLeft = new Sprite(32, this.getWidth() / 2, this.getGoLeftOrRightWidthOrHeight(), this.getGoLeftOrRightWidthOrHeight(),
                menuLeftTextureRegion, resourceManager.getEngine().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX,
                                         float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    scrollingMenu.onScroll(mScrollDetector, 0, this.getWidth()/2, 0);
                    return true;
                }
                return false;
            }
        };
        this.mScene.registerTouchArea(menuLeft);
        menuRight = new Sprite(this.getWidth() - 32,this.getHeight() / 2, this.getGoLeftOrRightWidthOrHeight(), this.getGoLeftOrRightWidthOrHeight(),
                menuRightTextureRegion, resourceManager.getEngine().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX,
                                         float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    scrollingMenu.onScroll(mScrollDetector, 0, -camera.getWidth()/2, 0);
                    return true;
                }
                return false;
            }
        };
        this.mScene.registerTouchArea(menuRight);
        this.mScene.attachChild(menuRight);
        menuLeft.setVisible(false);
        this.mScene.attachChild(menuLeft);

    }

    @Override
    protected void pushResourceRequirements() {

        super.pushResourceRequirements();

        IResourceManager resourceManager = this.getTheme().getThemeManager().getResourceManager();
        ITexturesSetResourceInfo arrowsTexturesSetResourceInfo = this.getTheme().getArrows().getArrowsTexturesSetResourceInfo();
        resourceManager.pushRequirement(arrowsTexturesSetResourceInfo);

        for(IImagedAndLabeled imagedAndLabeled : this.getImagedAndLabeledList())
        {
            resourceManager.pushRequirement(imagedAndLabeled.getTextureSetResourceInfo());
        }
    }

    @Override
    protected void popResourceRequirements() throws EResourceNotFound {

        super.popResourceRequirements();

        this.getTheme().getThemeManager().getResourceManager().popRequirement(this.getTheme().getArrows().getArrowsTexturesSetResourceInfo());

        for(IImagedAndLabeled imagedAndLabeled : this.getImagedAndLabeledList())
        {
            this.getTheme().getThemeManager().getResourceManager().popRequirement(imagedAndLabeled.getTextureSetResourceInfo());
        }
    }

    @Override
    public void onClick(ClickDetector pClickDetector, int pPointerID, float pSceneX, float pSceneY) {
        IImagedAndLabeled imagedAndLabeled = null;

        if(iItemClicked >= 0)
        {
            imagedAndLabeled = this.imagedAndLabeledList.get(iItemClicked);

            for(IScrollingMenuListener scrollingMenuListener : this.scrollingMenuListenersList)
            {
                scrollingMenuListener.onItemClicked(this, imagedAndLabeled);
            }
        }
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        this.mClickDetector.onTouchEvent(pSceneTouchEvent);
        this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
        return true;
    }


    @Override
    public void onScroll(ScrollDetector scrollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
        Camera camera = this.getTheme().getThemeManager().getResourceManager().getEngine().getCamera();
        // Disable the menu arrows left and right (15px padding)
        if (camera.getXMin() <= 15)
            menuLeft.setVisible(false);
        else
            menuLeft.setVisible(true);
        if (camera.getXMin() > mMaxX - 15)
            menuRight.setVisible(false);
        else
            menuRight.setVisible(true);
        // Return if ends are reached
        if (((mCurrentX - pDistanceX) < mMinX)) {
            return;
        } else if ((mCurrentX - pDistanceX) > mMaxX) {
            return;
        }
        // Center camera to the current point
        camera.offsetCenter(-pDistanceX, 0);
        mCurrentX -= pDistanceX;
        // Set the scrollbar with the camera
        float tempX = camera.getCenterX() - camera.getWidth() / 2;
        // add the % part to the position
        tempX += (tempX / (mMaxX + camera.getWidth())) * camera.getWidth();
        // set the position
        scrollBar.setPosition(tempX + scrollBar.getWidth()/2, 10);
        // set the arrows for left and right
        menuRight.setPosition(camera.getCenterX() + camera.getWidth() / 2 - 32,
                camera.getHeight() / 2);
        menuLeft.setPosition(camera.getCenterX() - camera.getWidth() / 2 + 32,
                camera.getHeight() / 2);
        // Because Camera can have negativ X values, so set to 0
        if (camera.getXMin() < 0) {
            camera.offsetCenter(0, 0);
            mCurrentX = 0;
        }

    }

    @Override
    public void onScrollFinished(ScrollDetector pScrollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
        //
    }

    @Override
    public void onScrollStarted(ScrollDetector pScrollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
        //
    }

    @Override
    public void onAttached() {
        super.onAttached();

        this.mScene = this.getTheme().getThemeManager().getResourceManager().getEngine().getScene();
        //this.createMenuBoxes();
    }

    @Override
    public void onDetached() {
        super.onDetached();
    }
}
*/
