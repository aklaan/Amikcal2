package com.rdupuis.gamingtools.components.keyboard;

import com.rdupuis.gamingtools.components.GroupOfGameObject;
import com.rdupuis.gamingtools.components.button.ButtonWithText;
import com.rdupuis.gamingtools.components.button.GLKeyboardListener;
import com.rdupuis.gamingtools.components.fonts.GlFont;
import com.rdupuis.gamingtools.components.texture.Texture;

import java.util.ArrayList;

/**
 * Created by rodol on 04/02/2016.
 */
public class Keyboard extends GroupOfGameObject {
    private final ArrayList<GLKeyboardListener> eventListenerList = new ArrayList<GLKeyboardListener>();


    public Keyboard(float x, float y, float width, float height, Texture texUp, Texture texDown,GlFont glFont) {
        super();
        int nbcases = 6;
        float spaceX = x;
        float spaceY = y;
        float offset = 100.f;

        String text = "ABCDEF";
        for (int i = 0; i < nbcases; i++) {

            ButtonWithText bt = new ButtonWithText(spaceX, spaceY, 100, 100, texUp, texDown, glFont);

            bt.setText(String.valueOf(text.charAt(i)));
            /**
             *

             //Button(float x, float y, float witdth, float height)
             ArrayList<Vector2D> txtCoord = Tools.Compute2DCoord(texUp, 3, 1, 57, 123);
             Button button = new Button(spaceX, spaceY, 57, 123, texUp, texDown);
             button.setTexCoord(txtCoord.get(0), txtCoord.get(1), txtCoord.get(2), txtCoord.get(3));
             button.textureEnabled = true;
             button.setTagName("KeyB:A");

             */
            this.add(bt);

            spaceX += offset;
        }

    }

    public void addGLKeyboardListener(GLKeyboardListener glKeyboardListener) {
        this.eventListenerList.add(glKeyboardListener);
    }

    /**
     * pour tous les objets qui écoutent le onClick(), on leur passe
     * l'info
     */
    public void onClick() {
        for (GLKeyboardListener listener : eventListenerList) {
            listener.onClick();

        }
    }

}
