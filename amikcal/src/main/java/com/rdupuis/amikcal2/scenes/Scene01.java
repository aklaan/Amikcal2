package com.rdupuis.amikcal2.scenes;


import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.rdupuis.amikcal2.R;
import com.rdupuis.amikcal2.energy.Act_Food_Editor;
import com.rdupuis.gamingtools.animations.AnimationFadeOut;
import com.rdupuis.gamingtools.animations.AnimationFadeOutMoveUp;
import com.rdupuis.gamingtools.components.GameObject;
import com.rdupuis.gamingtools.components.button.ButtonA;
import com.rdupuis.gamingtools.components.button.GLButtonListener;
import com.rdupuis.gamingtools.components.OpenGLActivity;
import com.rdupuis.gamingtools.components.Scene;
import com.rdupuis.gamingtools.components.keyboard.GLKeyboardListener;
import com.rdupuis.gamingtools.components.fonts.FontConsolas;
import com.rdupuis.gamingtools.components.keyboard.GLKeyboard;
import com.rdupuis.gamingtools.components.fonts.GlString;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.enums.DrawingMode;


/**
 * GLES20Renderer: the OGLES 2.0 Thread.
 */
public class Scene01 extends Scene {

    private final String TAG_KEYBOARD = "scene1:keyboard";
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


        //-----------------------------------------------

        initButtonQty();
        initButtonOk();

        //-------------------------------------------------

        GLKeyboard GLKeyboard = new GLKeyboard(0, 300, this.getWidth(), 400
                , this.getTexManager().getTextureById(R.string.texture_bouton_bleu)
                , this.getTexManager().getTextureById(R.string.texture_bouton_rouge)
                , new FontConsolas());
        GLKeyboard.setTagName(TAG_KEYBOARD);

        this.addToScene(GLKeyboard);

        GlString testGlString = new GlString(new FontConsolas());
        testGlString.setCoord(10, 900);
        testGlString.setTagName("matextbox");
        testGlString.setText("Clément & Emilie");
        this.addToScene(testGlString);

        //TODO : il faut que la texBox écoute le clavier pour se mettre à jour

        GLKeyboard.addGLKeyboardListener(new GLKeyboardListener() {
            @Override
            public void onClick(Bundle bundle) {
                Act_Food_Editor activity = (Act_Food_Editor) Scene01.this.getActivity();

                Message completeMessage =
                        activity.getHandler().obtainMessage(activity.GET_KEYPRESSED);

                completeMessage.setData(bundle);
                completeMessage.sendToTarget();

            }
        })
        ;



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
            public void onClick(Bundle bundle) {
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
                        activity.getHandler().obtainMessage();

                //sendToTarget va actionner la fonction handleMessage du Handle géré par Mainactivity
                // ici mon message n'est pas transféré via cette technique
                // pour faire simple, j'ai géré une variable texte directement dans le MainActivity,
                // mais c'est pas bien de faire comme ça.
                completeMessage.sendToTarget();


            }

            @Override
            public void onLongClick() {
                Log.e("Scene01", "Bouton QTY >> long click");
                GameObject keyb = Scene01.this.getGOManager().getGameObjectByTag(TAG_KEYBOARD);
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
            public void onClick(Bundle bundle) {
                Log.e("debug", "click");
                //       Shape bug = (Shape) Scene01.this.getGOManager().getGameObjectByTag(TAG_BUG);
                //       Scene01.this.getAnimationManager().addAnimation(new AnimationRotate(bug));


                Act_Food_Editor activity = (Act_Food_Editor) Scene01.this.getActivity();

                //**   toto.texte = "Click:" + String.valueOf(Math.random());

                //je crée un message vide juste pour forcer l'utilisation du Handler
                // qui est géré par la MainActivity.
                //ceci me permet de pouvoir mettre à jour les éléments UI tel qu'une textView.
                //Autrement c'est impossible car seul le thead Mainactivity peu modifier les UI
                // dont il a la gestion

                Message completeMessage =
                        activity.getHandler().obtainMessage(activity.GET_KEYPRESSED);

                //sendToTarget va actionner la fonction handleMessage du Handle géré par Mainactivity
                // ici mon message n'est pas transféré via cette technique
                // pour faire simple, j'ai géré une variable texte directement dans le MainActivity,
                // mais c'est pas bien de faire comme ça.
                Bundle data = new Bundle();
                data.putChar("CHAR", 'A');
                completeMessage.setData(data);
                completeMessage.sendToTarget();
            }

            @Override
            public void onLongClick() {
                Log.e("debug", "long click");

                Log.e("Scene01", "Bouton QTY >> long click");
                GameObject keyb = Scene01.this.getGOManager().getGameObjectByTag(TAG_KEYBOARD);
                Scene01.this.getAnimationManager().addAnimation(new AnimationFadeOutMoveUp(keyb));


            }

        };
        button.addGLButtonListener(toto);
        this.addToScene(button);

    }

 /**
    @Override
    public void loadProgramShader() {
        super.loadProgramShader();


    }
*/
    @Override
    public void loadTextures() {

        this.getTexManager().add(R.string.bugalive);
        this.getTexManager().add(R.string.bugdead);
        this.getTexManager().add(R.string.circle);
        this.getTexManager().add(R.string.spaceship);
        this.getTexManager().add(R.string.emptycircle);
        this.getTexManager().add(R.string.grille);
        this.getTexManager().add(R.string.texture_bouton_bleu);
        this.getTexManager().add(R.string.texture_bouton_rouge);
    }


}
