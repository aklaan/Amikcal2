package com.rdupuis.gamingtools.components.fonts;

import android.util.Log;
import android.util.Xml;

import com.gamingtools.rdupuis.gamingtools.R;
import com.rdupuis.gamingtools.components.shapes.FrameCursor;
import com.rdupuis.gamingtools.components.texture.Texture;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by rodol on 18/02/2016.
 */
public abstract class GlFont {


    //getter & setter
    abstract public Texture getMap();

    abstract public void setMap(Texture texture);

    abstract public void setXmlData(InputStream inputStream);

    abstract public InputStream getXmlData();

    abstract public FrameCursor getFrameCursor();

    abstract public int getMapPathId();

    abstract public int getXmlDataPathId();

    abstract public float[] getCharTextCoord(int value);

    abstract public float getCharRatio(int value);

}