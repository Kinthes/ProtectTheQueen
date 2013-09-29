package com.ecs.savethequeen.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Queen extends Character {
	
	public Queen(){
	character=new Rectangle();
	character.x = 800/2-character.width/2;
	character.y = Gdx.graphics.getHeight()/4;//50;
	character.width=64;
	character.height=64;
	super.setvX(0);
	super.setAccX(50);
	}
}
