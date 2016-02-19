package com.rdupuis.gamingtools.components.shapes;

import com.rdupuis.gamingtools.enums.DrawingMode;

/**
 * Created by rodol on 18/02/2016.
 */
public class GlChar extends Rectangle2D {


    private char value;

    //getter & setter
    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }


    //Constructor
    public GlChar(char value) {
        super(DrawingMode.FILL);
        this.value = value;
    }
}
