package com.ecs.savethequeen.res;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Data {
	public Texture arrowImage, hectorImage, queenImage;
	public Sprite arrowSpr, hectorSpr, queenSpr;
	public Sound dropSound;	
	public Music rainMusic;
	
	public Data(){
		hectorImage = new Texture(Gdx.files.internal("data/hector.png"));
		hectorSpr=new Sprite(hectorImage);		
		arrowImage = new Texture(Gdx.files.internal("data/arrow.png"));
		arrowSpr=new Sprite(arrowImage);
		queenImage = new Texture(Gdx.files.internal("data/hector.png"));
		queenSpr=new Sprite(queenImage);
		
		dropSound = Gdx.audio.newSound(Gdx.files.internal("data/drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/rain.mp3"));		
		rainMusic.setLooping(true);
	}
	
	public void dispose(){
		hectorImage.dispose();
		arrowImage.dispose();	
		queenImage.dispose();
	    dropSound.dispose();
	    rainMusic.dispose();
	}
}
