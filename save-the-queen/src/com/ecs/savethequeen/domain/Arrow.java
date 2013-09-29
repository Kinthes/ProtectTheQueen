package com.ecs.savethequeen.domain;

import com.badlogic.gdx.math.Rectangle;



public class Arrow extends Object {
	private float angle;
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public Arrow(){

		object=new Rectangle();
		object.width=8;
		object.height=32;
	}

}
