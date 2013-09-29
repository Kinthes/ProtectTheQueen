package com.ecs.savethequeen.domain;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
	


public class Object {
	Vector2 direction;
	protected Rectangle object;
	
	
	public Object(){
		direction=new Vector2();
	}
	
	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public void dispose(){
		
	}


	public Rectangle getObject() {
		return object;
	}

	public void setObject(Rectangle object) {
		this.object = object;
	}


	
}
