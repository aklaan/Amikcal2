package com.rdupuis.gamingtools.components;

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

}
