package com.rdupuis.gamingtools.components.shapes;

import com.gamingtools.rdupuis.gamingtools.R;
import com.rdupuis.gamingtools.components.texture.Texture;
import com.rdupuis.gamingtools.enums.DrawingMode;
import com.rdupuis.gamingtools.utils.Tools;
import com.rdupuis.gamingtools.utils.Vector2D;

import java.util.ArrayList;

/**
 * Created by rodol on 18/02/2016.
 */
public class GlFont  {


    private static Texture mTexture;

    public static Texture getMap() {
        return mTexture;
    }

    public static void setMap(Texture texture) {
        mTexture = texture;
    }


    public  float getMapRatio(char charCode) {
        float result;
        switch (Character.getNumericValue(charCode)) {
/**
 * pour tester le concept on utilise la map calibri en dur...a faire évoluer.!!!!!
 */
            case 65:
                result = 57.f/ 123.f;
                break;

            default:
                result = 57.f/123.f;

        }

        return result;
    }


    public static float[] getTextCoord(char charCode) {
        float[] result = new float[8];


        switch (Character.getNumericValue(charCode)) {
/**
 * pour tester le concept on utilise la map calibri en dur...a faire évoluer.!!!!!
 */
            case 65:
                result = getCharTextCoord(3, 1, 57, 123);
                break;

            default:
                result = getCharTextCoord(3, 1, 57, 123);

        }

        return result;
    }




    public static float[] getCharTextCoord(int x, int y, int width, int heigth) {
        float[] result = new float[8];

        // il faut ajouter le cast en fload, sinon java tranforme le resultat en int car geWidth()
        // et getHeight() retournent un int.
        //upLeft
        result[0] = x / (float) mTexture.getWidth();
        result[1] = y / (float) mTexture.getHeight();
        //downLeft
        result[2] = x / (float) mTexture.getWidth();
        result[3] = (y + heigth) / (float) mTexture.getHeight();
        //downRight
        result[4] = (x + width) / (float) mTexture.getWidth();
        result[5] = (y + heigth) / (float) mTexture.getHeight();
        //upRight
        result[6] = (x + width) / (float) mTexture.getWidth();
        result[7] = y / (float) mTexture.getHeight();
        return result;
    }


}