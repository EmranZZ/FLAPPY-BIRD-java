package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

//Let's create a State of our game.
//We can organize the different phases of our game using Game States
public abstract class State {
    //each states needs a camera to locate a position in the world
    protected OrthographicCamera cam;
    //creating XYZ coordinate system for mouse or some sort of pointer
    protected Vector3 mouse;
    //creating game state manager which is a way to manage our sates
    //by which we can manage the functionality
    protected GameStateManager gsm;

    //generating constructor where initialize the object
    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt); //where Delta time is the
                                           //difference between one frame rendered and the next frame rendered
    public abstract void render(SpriteBatch sb); //where SpriteBatch renders everything to the screen in one big blob
    public abstract void dispose(); //dispose() for each state
}
