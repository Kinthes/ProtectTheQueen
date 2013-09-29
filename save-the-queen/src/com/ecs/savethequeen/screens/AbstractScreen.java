package com.ecs.savethequeen.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ecs.savethequeen.SaveTheQueen;

public abstract class AbstractScreen implements Screen{
	
	protected final SaveTheQueen	game;
	
	private SpriteBatch		batch;
	private BitmapFont 		font;
	private OrthographicCamera camera;
	
	public AbstractScreen(SaveTheQueen game){
		this.game=game;
				
		
	}
	
	
	@Override
	public void render(float delta) {
		//super.render(delta);
		/*Gdx.gl.glClearColor(0, 0, 0.2f, 1);	
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();				
		batch.setProjectionMatrix(camera.combined);*/
	}

	public BitmapFont getFont() {
		if(font == null)font = new BitmapFont();
		return font;
	}


	public void setFont(BitmapFont font) {
		this.font = font;
	}


	public SpriteBatch getBatch() {
		if(batch == null)batch = new SpriteBatch();
		return batch;
	}


	public OrthographicCamera getCamera() {
		if(camera==null){
			camera = new OrthographicCamera();
			camera.setToOrtho(false, 800,480);
		}
		return camera;
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		font.dispose();
	}

}
