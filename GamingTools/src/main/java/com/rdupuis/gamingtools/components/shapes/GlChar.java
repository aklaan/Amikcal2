package com.rdupuis.gamingtools.components.shapes;

import com.gamingtools.rdupuis.gamingtools.R;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;

import java.nio.charset.Charset;

/**
 * Created by rodol on 18/02/2016.
 */
public class GlChar extends Rectangle2D {

    private char value;
    private GlFont mGlFont;

    /**
     * getter & setter
     *
     * @return
     */

    public GlFont getGlFont() {
        return mGlFont;
    }

    public void setGlFont(GlFont mGlFont) {
        this.mGlFont = mGlFont;
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
        this.mGlFont = font;
        this.value = value;

        this.enableTexturing();
        this.setTexCoord(font.getCharTextCoord(value));
        this.setTexture(font.getMap());

        this.setX(0);
        this.setY(0);

    }


    @Override
    public float getHeight() {
        return this.getGlFont().getSize();
    }

    @Override
    public float getWidth() {
        float ratio = this.getGlFont().getRatio(this.value);
        return this.getGlFont().getSize() * ratio;

    }


    @Override
    public Texture getTexture() {

        return this.getGlFont().getMap();
    }
}

