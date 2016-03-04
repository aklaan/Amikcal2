package com.rdupuis.gamingtools.components.shapes;

/**
 * Created by rodol on 04/03/2016.
 */
public class FrameCursor {


    private static int x;
    private static int y;
    private static int height;
    private static int width;



    private float ratio;

    /**
     *  getter & setter
     */

    public static int getX() {
        return x;
    }
    public static void setX(int newx) {
        x = newx;
    }


    public static int getY() {
        return y;
    }

    public static void setY(int newy) {
        y = newy;
    }



    public static int getHeight() {
        return height;
    }

    public static void setHeight(int newheight) {
        height = newheight;
    }


    public static int getWidth() {
        return width;
    }

    public static void setWidth(int newwitdh) {
        width = newwitdh;
    }


    public float getRatio() {
        return ratio;
    }

    public void setRatio(float newratio) {
        this.ratio = newratio;
    }

    public FrameCursor(){
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.ratio=0.f;
    }






}

