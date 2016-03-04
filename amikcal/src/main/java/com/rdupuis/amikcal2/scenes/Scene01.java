package com.rdupuis.amikcal2.scenes;


import android.os.Message;
import android.util.Log;

import com.rdupuis.amikcal2.R;
import com.rdupuis.gamingtools.animations.AnimationFadeOut;
import com.rdupuis.gamingtools.animations.AnimationFadeOutMoveUp;
import com.rdupuis.gamingtools.components.AbstractGameObject;
import com.rdupuis.gamingtools.components.GameObject;
import com.rdupuis.gamingtools.components.button.ButtonA;
import com.rdupuis.gamingtools.components.button.ButtonWithText;
import com.rdupuis.gamingtools.components.button.GLButtonListener;
import com.rdupuis.gamingtools.components.OpenGLActivity;
import com.rdupuis.gamingtools.components.Scene;
import com.rdupuis.gamingtools.components.keyboard.Keyboard;
import com.rdupuis.gamingtools.components.shapes.GlFont;
import com.rdupuis.gamingtools.components.shapes.GlString;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;
import com.rdupuis.gamingtools.shaders.ProgramShader;
import com.rdupuis.gamingtools.shaders.ProgramShader_forLines;
import com.rdupuis.gamingtools.shaders.ProgramShader_noTexture;
import com.rdupuis.gamingtools.shaders.ProgramShader_simple;


/**
 * GLES20Renderer: the OGLES 2.0 Thread.
 */
public class Scene01 extends Scene {

    private final String TAG_KEYBORD = "scene1:keyboard";
    private final String TAG_BACKGROUND = "scene1:background";
    private final String TAG_BUTTON = "scene1:button";


    public Scene01(OpenGLActivity activity) {
        super(activity);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void loadGameObjects() {

        //BACKGROUND
        Rectangle2D background = new Rectangle2D(DrawingMode.FILL);
        background.setCoord((float) this.getWidth() / 2, (float) this.getHeight() / 2);
        background.setHeight((float) this.getHeight());
        background.setWidth((float) this.getWidth());

        background.setTagName(TAG_BACKGROUND);
        background.disableCollisions();

        initButtonQty();
        initButtonOk();

        Texture keyUp = this.getTexManager().getTextureById(R.string.calibri);
        Texture fontmat = this.getTexManager().getTextureById(R.string.calibri);
        Keyboard keyboard = new Keyboard(100, 200, 400, 400, keyUp, keyUp,fontmat);
        keyboard.setTagName(TAG_KEYBORD);
           this.addToScene(keyboard);

        //TODO : quand on crée un GlString, il faut imposer une GlFont ou  en imposer un par defaut.

        GlString testGlString = new GlString();
        GlFont glFont = new GlFont();
        //TODO : intéger la map directement dans la font.
        glFont.setMap(this.getTexManager().getTextureById(R.string.calibri));

        testGlString.setGlFont(glFont);

        testGlString.setCoord(100, 800);

        testGlString.setText("Clement");

        this.addToScene(testGlString);
    }


    /*********************************************************************
     * Bouton pour les quantités
     **********************************************************************/
    private void initButtonQty() {

        //BUTTON
        //Button(float x, float y, float witdth, float hight, Texture textureUp, Texture textureDown)


        ButtonA button = new ButtonA(150, 600, 400, 400,
                this.getTexManager().getTextureById(R.string.circle),
                this.getTexManager().getTextureById(R.string.spaceship),
                this.getTexManager().getTextureById(R.string.emptycircle));
        button.setTagName(TAG_BUTTON);

        button.getAmbiantColor().setGreen(0.f);

        GLButtonListener toto = new GLButtonListener() {
            @Override
            public void onClick() {
                Log.e("debug", "click");
                //       Shape bug = (Shape) Scene01.this.getGOManager().getGameObjectByTag(TAG_BUG);
                //       Scene01.this.getAnimationManager().addAnimation(new AnimationRotate(bug));


                OpenGLActivity activity = Scene01.this.getActivity();

                //**   toto.texte = "Click:" + String.valueOf(Math.random());

                //je crée un message vide juste pour forcer l'utilisation du Handler
                // qui est géré par la MainActivity.
                //ceci me permet de pouvoir mettre à jour les éléments UI tel qu'une textView.
                //Autrement c'est impossible car seul le thead Mainactivity peu modifier les UI
                // dont il a la gestion

                Message completeMessage =
                        activity.mHandler.obtainMessage();

                //sendToTarget va actionner la fonction handleMessage du Handle géré par Mainactivity
                // ici mon message n'est pas transféré via cette technique
                // pour faire simple, j'ai géré une variable texte directement dans le MainActivity,
                // mais c'est pas bien de faire comme ça.
                completeMessage.sendToTarget();


            }

            @Override
            public void onLongClick() {
                Log.e("Scene01", "Bouton QTY >> long click");
                GameObject keyb = Scene01.this.getGOManager().getGameObjectByTag(TAG_KEYBORD);
                Scene01.this.getAnimationManager().addAnimation(new AnimationFadeOut(keyb));
            }

        };


        button.addGLButtonListener(toto);
        this.addToScene(button);


    }


    /*********************************************************************
     * Bouton ok
     **********************************************************************/
    private void initButtonOk() {

        //BUTTON
        //Button(float x, float y, float witdth, float hight, Texture textureUp, Texture textureDown)

        ButtonA button = new ButtonA(500, 600, 200, 200,
                this.getTexManager().getTextureById(R.string.circle),
                this.getTexManager().getTextureById(R.string.spaceship),
                this.getTexManager().getTextureById(R.string.emptycircle));
        button.setTagName(TAG_BUTTON);

        GLButtonListener toto = new GLButtonListener() {
            @Override
            public void onClick() {
                Log.e("debug", "click");
                //       Shape bug = (Shape) Scene01.this.getGOManager().getGameObjectByTag(TAG_BUG);
                //       Scene01.this.getAnimationManager().addAnimation(new AnimationRotate(bug));


                OpenGLActivity activity = Scene01.this.getActivity();

                //**   toto.texte = "Click:" + String.valueOf(Math.random());

                //je crée un message vide juste pour forcer l'utilisation du Handler
                // qui est géré par la MainActivity.
                //ceci me permet de pouvoir mettre à jour les éléments UI tel qu'une textView.
                //Autrement c'est impossible car seul le thead Mainactivity peu modifier les UI
                // dont il a la gestion

                Message completeMessage =
                        activity.mHandler.obtainMessage();

                //sendToTarget va actionner la fonction handleMessage du Handle géré par Mainactivity
                // ici mon message n'est pas transféré via cette technique
                // pour faire simple, j'ai géré une variable texte directement dans le MainActivity,
                // mais c'est pas bien de faire comme ça.
                completeMessage.sendToTarget();
            }

            @Override
            public void onLongClick() {
                Log.e("debug", "long click");

                Log.e("Scene01", "Bouton QTY >> long click");
                GameObject keyb = Scene01.this.getGOManager().getGameObjectByTag(TAG_KEYBORD);
                Scene01.this.getAnimationManager().addAnimation(new AnimationFadeOutMoveUp(keyb));


            }

        };
        button.addGLButtonListener(toto);
        this.addToScene(button);

    }


    @Override
    public void loadProgramShader() {
        this.getPSManager().catalogShader.clear();
        this.getPSManager().shaderList.clear();
        ProgramShader ps = new ProgramShader_simple();
        this.getPSManager().add(ps);
        ProgramShader notext = new ProgramShader_noTexture();
        this.getPSManager().add(notext);

        //on défini le simple comme shader par defaut.
        this.getPSManager().setDefaultSader(ps);


    }

    @Override
    public void loadTextures() {

        this.getTexManager().add(R.string.bugalive);
        this.getTexManager().add(R.string.bugdead);
        this.getTexManager().add(R.string.circle);
        this.getTexManager().add(R.string.spaceship);
        this.getTexManager().add(R.string.emptycircle);

    }


}
