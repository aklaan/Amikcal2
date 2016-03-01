package com.rdupuis.gamingtools.components.keyboard;

import com.rdupuis.gamingtools.components.GroupOfGameObject;
import com.rdupuis.gamingtools.components.button.Button;
import com.rdupuis.gamingtools.components.button.ButtonWithText;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.utils.Tools;
import com.rdupuis.gamingtools.utils.Vector2D;

import java.util.ArrayList;

/**
 * Created by rodol on 04/02/2016.
 */
public class Keyboard extends GroupOfGameObject {


    public Keyboard(float x, float y, float width, float height, Texture texUp, Texture texDown) {
        super();
        int nbcases = 6;
        float spaceX = x;
        float spaceY = y;
        float offset = 57.f;

        for (int i = 0; i < nbcases; i++) {



         //   ButtonWithText bt = new ButtonWithText(spaceX,spaceY,100,100,texUp,texDown);
         //   bt.setText("A");
            /**
             *

                //Button(float x, float y, float witdth, float height)
                ArrayList<Vector2D> txtCoord = Tools.Compute2DCoord(texUp, 3, 1, 57, 123);
                Button button = new Button(spaceX, spaceY, 57, 123, texUp, texDown);
                button.setTexCoord(txtCoord.get(0), txtCoord.get(1), txtCoord.get(2), txtCoord.get(3));
                button.textureEnabled = true;
                button.setTagName("KeyB:A");

             */
           //     this.add(bt);

            spaceX += offset;
        }

    }




}
