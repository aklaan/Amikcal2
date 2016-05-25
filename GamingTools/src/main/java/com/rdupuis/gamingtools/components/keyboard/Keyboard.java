package com.rdupuis.gamingtools.components.keyboard;

import android.os.Bundle;
import android.util.Log;

import com.rdupuis.gamingtools.components.GroupOfGameObject;
import com.rdupuis.gamingtools.components.button.Button;
import com.rdupuis.gamingtools.components.button.ButtonWithText;
import com.rdupuis.gamingtools.components.button.GLButtonListener;
import com.rdupuis.gamingtools.components.fonts.GlFont;
import com.rdupuis.gamingtools.components.texture.Texture;

import java.util.ArrayList;

/**
 * Created by rodol on 04/02/2016.
 */
public class Keyboard extends GroupOfGameObject {
    private final ArrayList<GLKeyboardListener> eventListenerList = new ArrayList<GLKeyboardListener>();
    public static String KEYPRESSED = "KEYPRESSED";
    public Keyboard(float x, float y, float width, float height, Texture texUp, Texture texDown, GlFont glFont) {
        super();
        //initialisation de base pour le clavier
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);

        //initialisation des touches
        float spaceX = this.getX();
        float spaceY = this.getY();


        String text = "ABCDEFG";
        float button_width = width / text.length();
        for (int i = 0; i < text.length(); i++) {
            ButtonWithText bt = new ButtonWithText(spaceX, spaceY, button_width, button_width, texUp, texDown, glFont);
            bt.setText(String.valueOf(text.charAt(i)));

            //ajout d'un listener sur le bouton
            bt.addGLButtonListener(new GLButtonListener() {
                @Override
                public void onClick(Bundle bundle) {
                    Log.e("debug", "click");

                   String  button_Value = bundle.getString(ButtonWithText.TEXT_VALUE);
                    //       Shape bug = (Shape) Scene01.this.getGOManager().getGameObjectByTag(TAG_BUG);
                    //       Scene01.this.getAnimationManager().addAnimation(new AnimationRotate(bug));
                Keyboard.this.onClick(button_Value.charAt(0));
                }

                public void onLongClick() {
                    Log.e("debug", "click");
                    //       Shape bug = (Shape) Scene01.this.getGOManager().getGameObjectByTag(TAG_BUG);
                    //       Scene01.this.getAnimationManager().addAnimation(new AnimationRotate(bug));

                }

            });


            this.add(bt);
            spaceX += button_width;
        }

        spaceX = this.getX();
        spaceY -= button_width;
        text = "HIJKLMN";
        for (int i = 0; i < text.length(); i++) {
            ButtonWithText bt = new ButtonWithText(spaceX, spaceY, button_width, button_width, texUp, texDown, glFont);
            bt.setText(String.valueOf(text.charAt(i)));
            this.add(bt);
            spaceX += button_width;
        }


        spaceX = this.getX();
        spaceY -= button_width;
        text = "OPQRSTU";
        for (int i = 0; i < text.length(); i++) {
            ButtonWithText bt = new ButtonWithText(spaceX, spaceY, button_width, button_width, texUp, texDown, glFont);
            bt.setText(String.valueOf(text.charAt(i)));
            this.add(bt);
            spaceX += button_width;
        }

        spaceX = this.getX();
        spaceY -= button_width;
        text = "VWXYZ";
        for (int i = 0; i < text.length(); i++) {
            ButtonWithText bt = new ButtonWithText(spaceX, spaceY, button_width, button_width, texUp, texDown, glFont);
            bt.setText(String.valueOf(text.charAt(i)));
            this.add(bt);
            spaceX += button_width;
        }

    }


    public void addGLKeyboardListener(GLKeyboardListener glKeyboardListener) {
        this.eventListenerList.add(glKeyboardListener);
    }

    /**
     * pour tous les objets qui écoutent le onClick(), on leur passe
     * l'info
     */
    public void onClick(char value) {
        for (GLKeyboardListener listener : eventListenerList) {
            Bundle bundle = new Bundle();
            bundle.putChar(Keyboard.KEYPRESSED,value);
            listener.onClick(bundle);

        }
    }

}
