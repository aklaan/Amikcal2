package com.rdupuis.gamingtools.components.shapes;

import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;

/**
 * Created by rodol on 18/02/2016.
 */
public class GlChar extends Rectangle2D {

    private char value;
    private GlFont mGlFont;
    private float size;

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
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    /**
     * Constructor
     */

    public GlChar(char value, GlFont font) {
        super(DrawingMode.FILL);
        this.mGlFont = font;
        this.value = value;

        this.enableTexturing();
        this.setTexCoord(font.getTextCoord(value));
        this.setTexture(font.getMap());
        this.setSize(100.f);

        this.setX(0);
        this.setY(0);
    }


    public void setSize(float size) {
        this.setHeight(size);
        float width = this.getGlFont().getMapRatio(this.getValue());
        this.setWidth(size * width);
        this.size = size;
    }

    @Override
    public Texture getTexture() {

        return this.getGlFont().getMap();
    }
}

