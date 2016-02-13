package com.rdupuis.gamingtools.components.keyboard;

import com.rdupuis.gamingtools.components.CompositeShape;
import com.rdupuis.gamingtools.components.button.Button;
import com.rdupuis.gamingtools.components.shapes.Rectangle2D;
import com.rdupuis.gamingtools.enums.DrawingMode;

/**
 * Created by rodol on 04/02/2016.
 */
public class Keyboard extends CompositeShape {


    public Keyboard(float x, float y, float width, float height) {
        super();
        int nbcases = 6;
        float spaceX =x;
        float spaceY =y;
        float offset = 15.f;

        float widthCase = width / nbcases;
        float heightCase = height / nbcases;

        for (int i = 0; i < nbcases; i++) {

            spaceY = y;
            for (int j = 0; j < nbcases; j++) {

                //Button(float x, float y, float witdth, float height)
                Button button = new Button(spaceX, spaceY, widthCase, heightCase);

                button.textureEnabled=false;
                button.setTagName("KeyB:" + i + "-" + j);
                this.getShapeList().add(button);
                spaceY += heightCase +offset;

            }
            spaceX += widthCase +offset;
        }

    }

}
