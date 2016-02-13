package com.rdupuis.gamingtools.components.physics;

import com.rdupuis.gamingtools.components.Vertex;

import java.util.ArrayList;

/**
 * Created by rodol on 05/02/2016.
 */
public interface Collidable {

    ArrayList<Vertex> getVertices();

    float[] getModelView();

    boolean isCollisionCheckingEnabled();

    void disableCollisionChecking();

    void enableCollisionChecking();

}