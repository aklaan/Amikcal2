package com.rdupuis.amikcal2.scenes;


import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;

import com.rdupuis.amikcal2.Activ02_EditUser;
import com.rdupuis.amikcal2.R;
import com.rdupuis.gamingtools.animations.AnimationConstructIn;
import com.rdupuis.gamingtools.animations.AnimationFadeIn;
import com.rdupuis.gamingtools.components.GameObject;
import com.rdupuis.gamingtools.components.OpenGLActivity;
import com.rdupuis.gamingtools.components.Scene;
import com.rdupuis.gamingtools.components.button.ButtonWithText;
import com.rdupuis.gamingtools.components.fonts.FontConsolas;
import com.rdupuis.gamingtools.components.fonts.GlString;
import com.rdupuis.gamingtools.components.numericpad.GLNumericPad;
import com.rdupuis.gamingtools.components.numericpad.GLNumericPadForDate;
import com.rdupuis.gamingtools.components.numericpad.GLNumericPadListener;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;
import com.rdupuis.gamingtools.enums.NumericPadKey;

import javax.microedition.khronos.opengles.GL10;


/**
 * GLES20Renderer: the OGLES 2.0 Thread.
 */
public class Scene02_EditUser extends Scene {
    GLNumericPadForDate numericPad;


    public static final String BUNDLE_ENTRY_VALUE = "1";

    private final String TAG_BACKGROUND = "scene1:background";
    private int nbFrame;
    private float startDrawingTime = 0f;

    public Scene02_EditUser(OpenGLActivity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void loadGameObjects() {

        //mise en place d'un background
        //    createBackground();
        //mise en place du titre
        createTitle();
        //mise en place d'un pavénumérique
        numericPad = createNumericPad();
        //ajout d'un FadeIn sur le pavé numérique
        this.getAnimationManager().addAnimation(new AnimationFadeIn(numericPad, 300));

        for (GameObject gameObject:numericPad.getGameObjectsList()){
            this.getAnimationManager().addAnimation(new AnimationConstructIn(gameObject, 800));
        }



    }




    @Override
    public void loadProgramShader() {
        super.loadProgramShader();

    }

    @Override
    public void loadTextures() {
        this.getTexManager().add(R.string.grille);
        this.getTexManager().add(R.string.texture_bouton_bleu);
        this.getTexManager().add(R.string.texture_bouton_rouge);
    }


    private void createBackground() {
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
        background.setTexture(this.getTexManager().getTextureById(R.string.grille));
        this.addToScene(background);
    }


    private void createTitle() {
        GlString title = new GlString(new FontConsolas());
        title.setFontSize(150f);
        title.setCoord(100, 500);
        title.setTagName("TITLE");
        title.setText("Date de naissance");
        this.addToScene(title);

    }

    private GLNumericPadForDate createNumericPad() {
        Texture textureUp = this.getTexManager().getTextureById(R.string.texture_bouton_bleu);
        Texture textureDown = this.getTexManager().getTextureById(R.string.texture_bouton_rouge);

        // public GLNumericPad(float x, float y, float width, float height, Texture texUp, Texture texDown, GlFont glFont)
        int padWidth = 440;
        int positionX = this.getWidth() - padWidth;
        GLNumericPadForDate numericPad = new GLNumericPadForDate(positionX, 0, padWidth, 1, textureUp, textureDown, new FontConsolas());


        numericPad.addGLNumericPadListener(new GLNumericPadListener() {
            @Override
            public void onClick(Bundle bundle) {
                Message completeMessage =
                        Scene02_EditUser.this.getActivity().getHandler().obtainMessage(Activ02_EditUser.ON_CLICK);
                Bundle myBundle = new Bundle();
                myBundle.putChar(Scene02_EditUser.BUNDLE_ENTRY_VALUE, bundle.getChar(GLNumericPad.KEY_PRESSED));
                completeMessage.setData(myBundle);
                completeMessage.sendToTarget();


            }

            @Override
            public void onClickOk() {
                //NOTHING TO DO


            }

            @Override
            public void onClickClear() {
                Message completeMessage =
                        Scene02_EditUser.this.getActivity().getHandler().obtainMessage(Activ02_EditUser.ON_CLICK_CLEAR);
                completeMessage.sendToTarget();
            }

        });

        this.addToScene(numericPad);

        ButtonWithText bt = (ButtonWithText)numericPad.getButton(NumericPadKey.FIVE);
        bt.setAlpha(.1f);


        return numericPad;
    }


}
