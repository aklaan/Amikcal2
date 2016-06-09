package com.rdupuis.amikcal2.scenes;


import com.rdupuis.amikcal2.R;
import com.rdupuis.gamingtools.components.OpenGLActivity;
import com.rdupuis.gamingtools.components.Scene;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.enums.DrawingMode;


/**
 * GLES20Renderer: the OGLES 2.0 Thread.
 */
public class Scene00_SplashScreen extends Scene {

    private final String TAG_BACKGROUND = "scene1:background";

    public Scene00_SplashScreen(OpenGLActivity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void loadGameObjects() {

        //BACKGROUND
        Rectangle2D background = new Rectangle2D(DrawingMode.FILL);
        //   background.setCoord((float) this.getWidth() / 2, (float) this.getHeight() / 2);
        background.setCoord(0f, 0f);
        background.setHeight((float) this.getHeight());
        background.setWidth((float) this.getWidth());

        background.setTagName(TAG_BACKGROUND);
        background.setVisibility(true);
        background.disableCollisions();
        background.enableTexturing();
        background.setTexture(this.getTexManager().getTextureById(R.string.splashscreen));
        this.addToScene(background);

    }


    @Override
    public void loadProgramShader() {
        super.loadProgramShader();

    }

    @Override
    public void loadTextures() {
        this.getTexManager().add(R.string.splashscreen);

    }


}
