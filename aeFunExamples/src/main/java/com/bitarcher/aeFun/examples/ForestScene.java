package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.drawables.animatedMeshed.nature.flowers.WindElasticFlower0;
import com.bitarcher.aeFun.drawables.animatedMeshed.nature.flowers.WindElasticFlower1;
import com.bitarcher.aeFun.drawables.animatedMeshed.nature.trees.WindTreeFactory;
import com.bitarcher.aeFun.drawables.animatedMeshed.tools.WindElasticCompositeMeshes;
import com.bitarcher.aeFun.geometry.PositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.sceneManagement.ManagedGameScene;
import com.bitarcher.aeFun.widgetToolkit.widget.Label;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.Random;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class ForestScene extends ManagedGameScene{

    BannerCtrl bannerCtrl;
    float windStrength = 0;
    Label windStrengthLabel;
    double totalElapsedTime = 0;
    Random random = new Random();

    ArrayList<WindElasticCompositeMeshes> windElasticCompositeMesheses = new ArrayList<>();



	public ForestScene(ITSceneManager sceneManager) {
        super(sceneManager, 0); // no loading screen
	}

    @Override
    public void onShowScene() {

    }

    @Override
    public void onHideScene() {

    }



    // No loading screen means no reason to use the following methods.
	@Override
	public IScene onLoadingScreenLoadAndShown() {
		return null;
	}
	@Override
	public void onLoadingScreenUnloadAndHidden() {
	}

	@Override
    public void onLoadScene() {
        this.setBackgroundEnabled(true);
        this.setBackground(new Background(0.3f, 1, 0.3f));

        ITheme theme = this.getSceneManager().getTheme();
        IResourceManager resourceManager = theme.getThemeManager().getResourceManager();
        Camera camera = theme.getThemeManager().getResourceManager().getEngine().getCamera();
        VertexBufferObjectManager vertexBufferObjectManager = theme.getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager();

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() * 5 / 6, camera.getWidth(), camera.getHeight() / 3, "Forest");



        WindTreeFactory treeFactory = new WindTreeFactory(vertexBufferObjectManager);


        float screenWidth = 800;
        float treeWidth = 200, treeHeight = 300;
        float screenHeight = 480;
        int numOfSteps = 20;
        float heightStep = (screenHeight - treeHeight ) / numOfSteps;


        for(int i = 0 ; i < numOfSteps ; i++)
        {
            float x = random.nextInt((int)(screenWidth - treeWidth));
            float y = screenHeight - i * heightStep - treeHeight / 2;

            PositionAndSizeOwner positionAndSizeOwner = new PositionAndSizeOwner(x, y, treeWidth, treeHeight);

            WindElasticCompositeMeshes tree = treeFactory.make(positionAndSizeOwner);
            this.attachChild(tree);
            this.windElasticCompositeMesheses.add(tree);
        }


        this.windStrengthLabel = new Label(theme, 400, 300, 400, 100, "");
        this.attachChild(this.windStrengthLabel);

        this.attachChild(this.bannerCtrl); // two columns span
    }

    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        this.totalElapsedTime += pSecondsElapsed;

        //this.windStrength = 0;
        this.windStrength = (float)(Math.abs(Math.cos(Math.cos(this.totalElapsedTime) * Math.PI))) / 3;

        for(WindElasticCompositeMeshes windElasticCompositeMeshes:this.windElasticCompositeMesheses)
        {
            if(this.random.nextBoolean()) {
                float r = (this.random.nextFloat() - 0.5f) / 15;
                float ws = this.windStrength + r;

                if (ws > 0.4f) {
                    ws = 0.4f;
                }
                if (ws < 0) {
                    ws = 0;
                }

                windElasticCompositeMeshes.setWindStrength(ws);
            }
        }



        //this.treeB.setWindStrength(this.windStrength);

        this.windStrengthLabel.setTranslatedLabel(String.format("S = %.01f", this.windStrength));
    }

}
