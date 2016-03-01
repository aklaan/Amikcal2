package com.rdupuis.gamingtools.components.shapes;


import com.rdupuis.gamingtools.components.Composition;
import com.rdupuis.gamingtools.components.GroupOfGameObject;

import java.util.ArrayList;

/**
 * Created by rodol on 18/02/2016.
 */
public class GlString extends GroupOfGameObject implements Composition {

    private String mText;
    private GlFont mGlFont;

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    private float size;

    /**
     * getter & setter
     */

    public GlFont getGlFont() {
        return mGlFont;
    }

    public void setGlFont(GlFont mGlFont) {
        this.mGlFont = mGlFont;
        this.updateFont();
    }


    public ArrayList<GlChar> getGlChar() {
        ArrayList<GlChar> result = new ArrayList<GlChar>();
        for (Composition composition : this.getComponent()) {
            if (composition instanceof GlChar) {
                result.add((GlChar) composition);
            }

        }
        return result;
    }


    public String getText() {
        return this.mText;
    }


    public void setText(String string) {
        this.mText = string;
        this.getList().clear();
        //la première lettre et à l'emplacement du début de la chaine
        float xPosition = this.getX();
        for (int i = 0; i < string.length(); i++) {
            GlChar mChar = new GlChar(string.charAt(i), this.getGlFont());
            mChar.setX(xPosition);
            mChar.setY(this.getY());
            this.getList().add(mChar);
         //le prochain caratère serra après
            xPosition += mChar.getWidth();
        }
        this.updateFont();
        this.updateSize();

    }

    private void updateFont() {
        for (GlChar glChar : this.getGlChar()) {
            glChar.setGlFont(this.getGlFont());
        }
    }


    private void updateSize() {
        for (GlChar glChar : this.getGlChar()) {
            glChar.setSize(this.getSize());
        }
    }


    public void add(GlChar glChar) {
        glChar.setGlFont(this.getGlFont());
        this.getList().add(glChar);
    }


}
