package com.rdupuis.gamingtools.components.shapes;


import com.rdupuis.gamingtools.components.Composition;

import java.util.ArrayList;

/**
 * Created by rodol on 18/02/2016.
 */
public class GlString implements Composition {

    private ArrayList<Composition> mGlCharList;

    public ArrayList<Composition> getComponent() {
        return mGlCharList;
    }


    public void add(GlChar glChar) {
        mGlCharList.add(glChar);
    }

}
