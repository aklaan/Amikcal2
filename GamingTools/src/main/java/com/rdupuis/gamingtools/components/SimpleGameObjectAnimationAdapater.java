package com.rdupuis.gamingtools.components;

import com.rdupuis.gamingtools.animations.Animation;
import com.rdupuis.gamingtools.components.shapes.Shape;

/**
 * Created by rodol on 19/10/2015.
 */
public class SimpleGameObjectAnimationAdapater extends Shape {
    private Shape mShape;
    private Animation animation;

    public SimpleGameObjectAnimationAdapater(Shape shape) {
        this.mShape = shape;
    }


    public Animation getAnimation() {
        return this.animation;
    }

    public void setAnimationList(Animation animation) {
        this.animation = animation;
    }


}



