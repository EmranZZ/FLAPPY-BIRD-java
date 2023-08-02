package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.FlappyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//config.setForegroundFPS(60);
		//config.setTitle("Flappy Bird");
		//giving width & height for Game Screen
		config.setWindowedMode(FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
		//giving title for Game Screen
		config.setTitle(FlappyDemo.TITLE);
		new Lwjgl3Application(new FlappyDemo(), config);
	}
}
