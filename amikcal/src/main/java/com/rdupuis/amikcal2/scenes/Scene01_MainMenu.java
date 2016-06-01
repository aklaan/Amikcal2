package com.rdupuis.amikcal2.scenes;


import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.rdupuis.amikcal2.MainActivity;
import com.rdupuis.amikcal2.MainMenu;
import com.rdupuis.amikcal2.R;
import com.rdupuis.gamingtools.animations.AnimationFadeOut;
import com.rdupuis.gamingtools.components.ColorRGBA;
import com.rdupuis.gamingtools.components.GameObject;
import com.rdupuis.gamingtools.components.OpenGLActivity;
import com.rdupuis.gamingtools.components.Scene;
import com.rdupuis.gamingtools.components.button.ButtonWithText;
import com.rdupuis.gamingtools.components.button.GLButtonListener;
import com.rdupuis.gamingtools.components.fonts.FontConsolas;
import com.rdupuis.gamingtools.components.fonts.GlString;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;
import com.rdupuis.gamingtools.utils.Tools;


/**
 * GLES20Renderer: the OGLES 2.0 Thread.
 */
public class Scene01_MainMenu extends Scene {

    private final String TAG_BACKGROUND = "scene1:background";

    public Scene01_MainMenu(OpenGLActivity activity) {
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
        background.setTexture(this.getTexManager().getTextureById(R.string.menuprincipal));
        this.addToScene(background);

        initButtonStart();
        initButtonOption();

    }


    @Override
    public void loadProgramShader() {
        super.loadProgramShader();

    }

    @Override
    public void loadTextures() {
        this.getTexManager().add(R.string.menuprincipal);
        this.getTexManager().add(R.string.texture_bouton_bleu);
        this.getTexManager().add(R.string.texture_bouton_rouge);
    }


    private void initButtonStart() {
        Texture textureUp = this.getTexManager().getTextureById(R.string.texture_bouton_bleu);
        Texture textureDown = this.getTexManager().getTextureById(R.string.texture_bouton_rouge);
        ButtonWithText bt = new ButtonWithText(0, 400, 400, 50, textureUp, textureDown, new FontConsolas());
        bt.getText().setFontSize(40f);
        bt.setText("START");
        ColorRGBA red = new ColorRGBA(1f, 0f, 0f, 1f);
        bt.getText().setAmbiantColor(red);
        Tools.horizontalAllignOnScene(this, bt);


        bt.addGLButtonListener(new GLButtonListener() {
            @Override
            public void onClick(Bundle bundle) {
                Log.e("debug", "click");

                //je crée un message pour l'utilisation du Handler
                // qui est géré par la MainActivity.
                //ceci me permet de pouvoir mettre à jour les éléments UI tel qu'une textView.
                //Autrement c'est impossible car seul le thead Mainactivity peu modifier les UI
                // dont il a la gestion

                Message completeMessage =
                        Scene01_MainMenu.this.getActivity().mHandler.obtainMessage(MainMenu.LAUNCH_XX);

                //sendToTarget va actionner la fonction handleMessage du Handle géré par l'activity
                completeMessage.sendToTarget();
            }

            @Override
            public void onLongClick() {//NOTHING TO DO
            }

        });

        this.addToScene(bt);


    }


    private void initButtonOption() {
        Texture textureUp = this.getTexManager().getTextureById(R.string.texture_bouton_bleu);
        Texture textureDown = this.getTexManager().getTextureById(R.string.texture_bouton_rouge);
        ButtonWithText bt = new ButtonWithText(0, 300, 400, 50, textureUp, textureDown, new FontConsolas());
        bt.getText().setFontSize(40f);
        bt.setText("OPTIONS");
        ColorRGBA red = new ColorRGBA(1f, 0f, 0f, 1f);
        bt.getText().setAmbiantColor(red);
        Tools.horizontalAllignOnScene(this, bt);
        this.addToScene(bt);
    }

}
