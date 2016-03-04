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

    /**
     * getter & setter
     */

    public GlFont getGlFont() {
        return mGlFont;
    }

    public void setGlFont(GlFont mGlFont) {
        this.mGlFont = mGlFont;
        //on a modifié la font. il faut que l'update répercute
        // la modification sur les char
        this.updateGlchar();
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

    }


    public void updateGlchar() {

        float xPosition = this.getX();

        for (GlChar glChar : this.getGlChar()) {
            glChar.setGlFont(this.getGlFont());
            glChar.setX(xPosition);
            glChar.setY(this.getY());
            xPosition += glChar.getWidth();

        }

    }


    @Override
    public void update() {
        this.getGlFont().setSize(this.getGlFont().getSize() + 0.1f);
        updateGlchar();
    }


    private void add(GlChar glChar) {
        glChar.setGlFont(this.getGlFont());
        this.getList().add(glChar);
    }


}
