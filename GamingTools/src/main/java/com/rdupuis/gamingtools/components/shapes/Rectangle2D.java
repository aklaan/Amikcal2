package com.rdupuis.gamingtools.components.shapes;

import com.rdupuis.gamingtools.components.Vertex;
import com.rdupuis.gamingtools.enums.DrawingMode;
import com.rdupuis.gamingtools.utils.CONST;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.ArrayList;

public class Rectangle2D extends Shape {

    static final int NB_RECTANGLE_VERTEX = 4;
    private DrawingMode mDrawingMode;


    public Rectangle2D(DrawingMode drawingMode) {
        super();

        this.mDrawingMode = drawingMode;
        this.setNbvertex(NB_RECTANGLE_VERTEX);
        this.setNbIndex((drawingMode == DrawingMode.FILL) ? 6 : 8);
    }


    @Override
    public ArrayList<Vertex> getVertices() {

        ArrayList<Vertex> temp = new ArrayList<Vertex>();
        // on ajoute les vertex (x,y,zu,v)
        temp.add(new Vertex(-0.5f, 0.5f, 0f, 0f, 0f, 1f, 1f, 1f, 1f));
        temp.add(new Vertex(-0.5f, -0.5f, 0f, 0f, 1f, 1f, 1f, 1f, 1f));
        temp.add(new Vertex(0.5f, -0.5f, 0f, 1f, 1f, 1f, 1f, 1f, 1f));
        temp.add(new Vertex(0.5f, 0.5f, 0, 1f, 0f, 1f, 1f, 1f, 1f));

        return temp;

    }


    @Override
    public ShortBuffer getIndices() {

        ShortBuffer result = null;

        switch (this.mDrawingMode) {
            // on dessine que les lignes de contour
            case EMPTY:
                result = getIndicesForEmptyRec();
                break;
            // on dessine des triangles plein
            case FILL:

                result = getIndicesForFillRec();

                // on indique l'ordre dans lequel on doit afficher les vertex
                // pour dessiner les 2 triangles qui vont former le carré.

        }
        result.rewind();
        return result;

    }

    public static ShortBuffer getIndicesForEmptyRec() {
        ShortBuffer indices;
        indices = ByteBuffer.allocateDirect(8 * CONST.SHORT_SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        indices.rewind();

        indices.put((short) 0).put((short) 1)
                .put((short) 1).put((short) 2)
                .put((short) 2).put((short) 3)
                .put((short) 3).put((short) 0);
        indices.rewind();

        return indices;

    }


    public static ShortBuffer getIndicesForFillRec() {
        ShortBuffer indices;
        indices = ByteBuffer.allocateDirect(6 * CONST.SHORT_SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();

        indices.put((short) 0).put((short) 1).put((short) 2)
                .put((short) 0).put((short) 2).put((short) 3);

        return indices;

    }


}
