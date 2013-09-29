package com.ecs.savethequeen;

import com.badlogic.gdx.Game;
import com.ecs.savethequeen.screens.MenuScreen;



public class SaveTheQueen extends Game {
		
	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void create() {		
		this.setScreen(new MenuScreen(this));
	}

}
