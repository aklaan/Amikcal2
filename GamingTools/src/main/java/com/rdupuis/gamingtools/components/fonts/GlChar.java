package com.rdupuis.gamingtools.components.fonts;

import com.rdupuis.gamingtools.components.ColorRGBA;
import com.rdupuis.gamingtools.components.fonts.GlFont;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;

/**
 * Created by rodol on 18/02/2016.
 */
public class GlChar extends Rectangle2D {

    private char value;

    public int getFontSize() {
        return mFontSize;
    }

    public void setFontSize(int mFontSize) {
        this.mFontSize = mFontSize;
    }

    private int mFontSize;

    public float getRatioWidthHeight() {
        return ratioWidthHeight;
    }

    public void setRatioWidthHeight(float ratioWidthHeight) {
        this.ratioWidthHeight = ratioWidthHeight;
    }

    private float ratioWidthHeight;

    /**
     * getter & setter
     *
     * @return
     */


    public void setGlFont(GlFont mGlFont) {
        update();
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
        update();
    }

    /**
     * Constructor
     */

    public GlChar(char value, GlFont font) {
        super(DrawingMode.FILL);
        this.value = value;
        this.mFontSize = 10; // taille d'un caractère par défaut
        this.enableTexturing();
        this.setTexture(font.getMap());
        this.setTexCoord(font.getCharTextCoord(value));
        this.setRatioWidthHeight(font.getCharRatio(value));
        this.setAmbiantColor(new ColorRGBA(1f, 1f, 1f, 1f));

        this.setX(0);
        this.setY(0);

    }


    public void setColor(ColorRGBA colorRGBA) {
        this.setAmbiantColor(colorRGBA);
    }

    @Override
    public float getHeight() {
        return this.mFontSize;
    }

    @Override
    public float getWidth() {
        return this.getHeight() * getRatioWidthHeight();

    }


}

