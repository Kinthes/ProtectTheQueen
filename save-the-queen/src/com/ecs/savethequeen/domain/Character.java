package com.ecs.savethequeen.domain;



import com.badlogic.gdx.math.Rectangle;

public class Character {
	private float vX;
	private float accX;
	protected Rectangle character;
		
	public Character(){

	}

	public Rectangle getCharacter() {
		return character;
	}

	public void setCharacter(Rectangle character) {
		this.character = character;
	}

	public float getvX() {
		return vX;
	}

	public void setvX(float vX) {
		this.vX = vX;
	}

	public float getAccX() {
		return accX;
	}

	public void setAccX(float accX) {
		this.accX = accX;
	}
}
