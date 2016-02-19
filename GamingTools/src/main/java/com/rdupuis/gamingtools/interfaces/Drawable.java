package com.rdupuis.gamingtools.interfaces;

import com.rdupuis.gamingtools.components.ColorRGBA;
import com.rdupuis.gamingtools.components.Vertex;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;

import java.nio.ShortBuffer;
import java.util.ArrayList;

public interface Drawable {

    public void draw();

    void setGlVBoId(int index);

    void setGlVBiId(int index);

    int getGlVBiId();

    ShortBuffer getIndices();

    int getNbvertex();

    ArrayList<Vertex> getVertices();

    int getGlVBoId();

    boolean getVisibility();

    boolean isTextureEnabled();

    Texture getTexture();

    float[] getModelView();

    ColorRGBA getAmbiantColor();

    int getGlDrawMode();

    int getNbIndex();
}
