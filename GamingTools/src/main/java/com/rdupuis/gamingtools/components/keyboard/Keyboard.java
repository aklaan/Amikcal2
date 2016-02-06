package com.rdupuis.gamingtools.components.keyboard;

import com.rdupuis.gamingtools.components.CompositeShape;
import com.rdupuis.gamingtools.components.button.Button;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.enums.DrawingMode;

/**
 * Created by rodol on 04/02/2016.
 */
public class Keyboard extends CompositeShape {


    public Keyboard(float x, float y, float witdth, float height) {
        super();
        int nbcases = 4;
        float spaceX;
        float spaceY;
        float offset = 5.f;

        float widthCase = witdth / nbcases;
        float heightCase = height / nbcases;

        for (int i = 0; i < nbcases; i++) {
            spaceX = (widthCase * i)+offset;
            for (int j = 0; j < nbcases; j++) {
                spaceY = (heightCase * j)+offset;
                //Button(float x, float y, float witdth, float height)
                Button button = new Button(x + spaceX, y + spaceY, widthCase, heightCase);
                button.textureEnabled=false;
                button.setTagName("KeyB:" + i + "-" + j);
                this.getShapeList().add(button);


            }

        }

    }

}
