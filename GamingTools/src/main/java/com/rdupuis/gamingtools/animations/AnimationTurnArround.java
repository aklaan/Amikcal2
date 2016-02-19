package com.rdupuis.gamingtools.animations;

import com.rdupuis.gamingtools.components.GameObject;

public class AnimationTurnArround extends Animation {

	GameObject mCible;
	float distance;
	float angle;

	public AnimationTurnArround(GameObject parent, GameObject cible, float distance) {
		super(parent);
		this.mCible = cible;
		this.angle = 0.0f;
		this.setSpeed(5.0f);
		this.distance = distance;
	}

	@Override
	public void start() {
		super.start();

	}

	@Override
	public void play() {

		if (this.mCible != null) {
			this.angle += 0.05f;
			this.getAnimatedGameObject().setX(mCible.getX()
					+ (float) (Math.cos(this.angle * this.getSpeed()) * this.distance));
			this.getAnimatedGameObject().setY(mCible.getY()
					+ (float) (Math.sin(this.angle * this.getSpeed()) * this.distance));

		}

	}

}
