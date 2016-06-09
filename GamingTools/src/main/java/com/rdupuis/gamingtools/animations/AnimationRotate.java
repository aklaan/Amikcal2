package com.rdupuis.gamingtools.animations;

import com.rdupuis.gamingtools.components.shapes.Shape;

public class AnimationRotate extends Animation {


    public AnimationRotate(Shape parent) {
        super(parent);
        this.launch();
    }

    @Override
    public void launch() {
        super.launch();
        this.setStatus(AnimationStatus.PLAYING);
    }

    @Override
    public void play() {

        ///this.getAnimatedGameObject().AngleZ_inc(+5.5f);

    }

}
