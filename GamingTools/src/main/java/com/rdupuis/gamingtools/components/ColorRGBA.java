package com.rdupuis.gamingtools.components;

import com.rdupuis.gamingtools.utils.CONST;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by rodol on 29/01/2016.
 */
public class ColorRGBA {

    private float red = 1.f;
    private float green = 1.f;
    private float blue = 1.f;
    private float alpha = 1.f;


    public void setRed(float colorValue) {
        this.red = checkValue(colorValue);
    }

    public float getRed(){
        return this.red;
    }


    public void setGreen(float colorValue) {
        this.green = checkValue(colorValue);
    }

    public float getGreen(){
        return this.green;
    }


    public void setBlue(float colorValue) {
        this.blue = checkValue(colorValue);
    }

    public float getBlue(){
        return this.blue;
    }


    public void setAlpha(float alphaValue) {
        this.alpha = checkValue(alphaValue);
    }

    public float getAlpha(){
        return this.alpha;
    }


    private float checkValue(float colorValue) {
        colorValue = (colorValue > 1) ? 1f : colorValue;
        colorValue = (colorValue < 0) ? 0f : colorValue;

        return colorValue;
    }


    public FloatBuffer getColorBuffer() {

        FloatBuffer result = ByteBuffer.allocateDirect(4 * CONST.FLOAT_SIZE)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

        result.rewind();
        //on écrit les coordonées de texture
        result.put(getRed()).put(getGreen()).put(getBlue()).put(getAlpha());
        // on se repositionne en 0 , prêt pour la lecture
        result.rewind();


        return result;
    }



}
