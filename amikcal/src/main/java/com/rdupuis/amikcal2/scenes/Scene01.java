package com.rdupuis.amikcal2.scenes;


import android.os.Message;
import android.util.Log;

import com.rdupuis.amikcal2.R;
import com.rdupuis.gamingtools.animations.AnimationFadeOut;
import com.rdupuis.gamingtools.components.AbstractGameObject;
import com.rdupuis.gamingtools.components.button.ButtonA;
import com.rdupuis.gamingtools.components.button.GLButtonListener;
import com.rdupuis.gamingtools.components.OpenGLActivity;
import com.rdupuis.gamingtools.components.Scene;
import com.rdupuis.gamingtools.components.keyboard.Keyboard;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.enums.DrawingMode;
import com.rdupuis.gamingtools.shaders.ProgramShader;
import com.rdupuis.gamingtools.shaders.ProgramShader_forLines;
import com.rdupuis.gamingtools.shaders.ProgramShader_noTexture;
import com.rdupuis.gamingtools.shaders.ProgramShader_simple;


/**
 * GLES20Renderer: the OGLES 2.0 Thread.
 */
public class Scene01 extends Scene {

    private final String TAG_BUG = "scene1:bug";
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

        Keyboard keyboard = new Keyboard(100, 200, 400, 400);

        this.addToScene(keyboard);

    }


    /*********************************************************************
     * Bouton pour les quantités
     **********************************************************************/
    private void initButtonQty() {

        //BUTTON
        //Button(float x, float y, float witdth, float hight, Texture textureUp, Texture textureDown)


        ButtonA button = new ButtonA(150, 600, 200, 200,
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
                Log.e("debug", "long click");
                AbstractGameObject bug = Scene01.this.getGOManager().getGameObjectByTag(TAG_BUG);
                Scene01.this.getAnimationManager().addAnimation(new AnimationFadeOut(bug));

                //**    MainActivity toto = (MainActivity) Scene01.this.getActivity();

                //**    toto.texte = "Long Click:" +
                String.valueOf(Math.random());

                //je crée un message vide juste pour focer l'utilisation du Handler
                // qui est géré par la MainActivity.
                //ceci me permet de pouvoir mettre à jour les éléments UI, autrement c'est impossible
                //seul le thead Maiactivity peu modifier les UI dont il a la gerstion

                /**
                 *

                 Message completeMessage =
                 toto.mHandler.obtainMessage();
                 completeMessage.sendToTarget();
                 */
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

        ButtonA button = new ButtonA(300, 600, 300, 100,
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
        this.getPSManager().setDefaultSader(notext);


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
