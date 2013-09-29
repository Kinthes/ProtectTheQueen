package com.ecs.savethequeen.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.ecs.savethequeen.SaveTheQueen;


public class MenuScreen extends AbstractScreen {

	public MenuScreen(SaveTheQueen game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);	
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		super.getCamera().update();				
		super.getBatch().setProjectionMatrix(super.getCamera().combined);
		
		//super.render(delta);
		
		super.getBatch().begin();
			super.getFont().draw(super.getBatch(), "Welcome to Drop !!!", 100, 150);
			super.getFont().draw(super.getBatch(), "Tap anywhere to begin...", 100, 100);
		super.getBatch().end();
		
		if(Gdx.input.isTouched()){
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}
}
