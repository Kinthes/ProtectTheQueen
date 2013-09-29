package domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Hector extends Character {
	private float vX;
	private float accX;
	
	public Hector(){
		
		character=new Rectangle();
		character.x = 800/2-character.width/2;
		character.y = Gdx.graphics.getHeight()/4;//50;
		character.width=32;
		character.height=32;
		vX=0;
		accX=50;
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
