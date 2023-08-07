package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {

	//generating Height, Width & Title of the Game Screen
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music; //for music of our game
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3")); //bg music
		music.setLooping(true); //continuously loop around the music
		music.setVolume(.3f);// .1 means 10% volume
		music.play(); //start it soon as our game starts

		Gdx.gl.glClearColor(1,0,0,1); //Clear Color basically wipes the screen
		// clean and redraws everything fresh
		
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//ScreenUtils.clear(1, 0, 0, 1);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}
