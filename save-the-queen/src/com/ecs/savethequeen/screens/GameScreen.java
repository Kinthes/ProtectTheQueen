package com.ecs.savethequeen.screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;
import com.ecs.savethequeen.SaveTheQueen;
import com.ecs.savethequeen.domain.Arrow;
import com.ecs.savethequeen.domain.Hector;
import com.ecs.savethequeen.res.Data;





public class GameScreen extends AbstractScreen {
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	Data data;
	Hector hector;	
	Vector3 touchPos;
	int dropsGathered=0;
	
	Array<Arrow> arrows;
	long lastDropTime;  //nanoseconds need long!
	
	private Pool<Arrow> rectPool = new Pool<Arrow>(){
		@Override
		protected Arrow newObject(){
			return new Arrow();
		}
	};
	
//***********************************************************************************************	
	public GameScreen(SaveTheQueen game) {
		super(game);
		
		data=new Data();
		hector=new Hector();
				
		touchPos = new Vector3();
		
		super.getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//800,480);
			
		arrows = new Array<Arrow>();
		spawnArrows();
	
	}
	
//**************************************************************************************************
	private void spawnArrows(){
		Arrow newArrow = new Arrow();
		newArrow.getObject().x = MathUtils.random(0,800-newArrow.getObject().width);
		newArrow.getObject().y=480;
		Vector2 start=new Vector2(newArrow.getObject().x, newArrow.getObject().y);		
		Vector2 end=new Vector2(hector.getCharacter().x, hector.getCharacter().y);
		newArrow.setDirection(end);
		newArrow.getDirection().sub(start);
		newArrow.getDirection().nor();
		newArrow.setAngle(newArrow.getDirection().angle());
		arrows.add(newArrow);
		lastDropTime=TimeUtils.nanoTime();
	}
	
//****************************************************************************************************	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);	
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		super.getCamera().update();
				
		super.getBatch().setProjectionMatrix(super.getCamera().combined);
		super.getBatch().begin();
			super.getBatch().draw(data.hectorImage, hector.getCharacter().x, hector.getCharacter().y);
			for(Arrow arrowdrop: arrows){
				//super.getBatch().draw(data.arrowImage,arrowdrop.getObject().x, arrowdrop.getObject().y);
				//super.getBatch().draw(data.arrowImage,arrowdrop.getObject().x,arrowdrop.getObject().y, 
				//		0.f, 0.f, arrowdrop.getObject().width, arrowdrop.getObject().height, 1.f, 1.f,arrowdrop.getDirection().angle(), false);
				data.arrowSpr.rotate(arrowdrop.getDirection().angle()-90);
				data.arrowSpr.setPosition(arrowdrop.getObject().x, arrowdrop.getObject().y);
				data.arrowSpr.draw(super.getBatch());
				data.arrowSpr.rotate(-(arrowdrop.getDirection().angle()-90));				
			}
			super.getFont().draw(super.getBatch(), "Drops collected : "+dropsGathered,0,480);
		super.getBatch().end();
		//drawDebug();
			
		moveArrows(delta);
		
		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnArrows(); //1000000000		
		
		moveHector(delta);
	}	
	
//****************************************************************************************	
	@Override
	public void show(){
		data.rainMusic.play();
	}
//********************************************************************************************
	
	@Override
	public void dispose(){		
	    data.dispose();	    	
	}

//******************************************************************************************
	public void moveArrows(float delta){
		Iterator<Arrow> iter = arrows.iterator();
		while(iter.hasNext()){
			Arrow arrowdrop = rectPool.obtain();
			arrowdrop=iter.next();
			//arrowdrop.getObject().y -= 400*delta;
			arrowdrop.getObject().x += arrowdrop.getDirection().x * 400 * delta;
			arrowdrop.getObject().y += arrowdrop.getDirection().y * 400 * delta;
			if(arrowdrop.getObject().y + arrowdrop.getObject().height <0)iter.remove();
			else if(arrowdrop.getObject().y > hector.getCharacter().y+(hector.getCharacter().height/2)){ 
				if(arrowdrop.getObject().overlaps(hector.getCharacter())){
				data.dropSound.play();
				iter.remove();
				dropsGathered++;
				}
			}
			rectPool.free(arrowdrop);
		}		
	}

//*******************************************************************************************
	public void moveHector(float delta){
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			if(hector.getvX()>-10){
				float tmp = hector.getvX() - hector.getAccX() * delta;
				hector.setvX(tmp);				
			}
			hector.getCharacter().x += hector.getvX();
		} 
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			if(hector.getvX()<10){
				float tmp = hector.getvX() + hector.getAccX() * delta;
				hector.setvX(tmp);				
			}
			hector.getCharacter().x += hector.getvX();
		}
		else if(hector.getvX()!=0){
			hector.setvX(hector.getvX() * 0.9f);
			hector.getCharacter().x += hector.getvX();
		}
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		if(hector.getCharacter().x < 0) hector.getCharacter().x = 0;
		if(hector.getCharacter().x > 800 - hector.getCharacter().width) hector.getCharacter().x = 800 - hector.getCharacter().width;
	}


//**************************************************************************************************
	public void drawDebug(){
		debugRenderer.setProjectionMatrix(super.getCamera().combined);
		debugRenderer.begin(ShapeType.Rectangle);
			debugRenderer.setColor(new Color(1,0,0,1));
			for(Arrow arrowdrop: arrows){
				debugRenderer.rect(arrowdrop.getObject().x,arrowdrop.getObject().y,arrowdrop.getObject().width, arrowdrop.getObject().height);
			}
			debugRenderer.setColor(new Color(0,1,0,1));
			debugRenderer.rect(hector.getCharacter().x,hector.getCharacter().y,hector.getCharacter().width, hector.getCharacter().height);
	
		debugRenderer.end();
	}


}
