package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

//Creating a manager that has a stack of states which renders and updates the one at the top
public class GameStateManager {
    //Creating Stack of States inside the GSM Class
    private Stack<State> states;

    public GameStateManager(){ //Constructor of Game State Manager
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){ //dt-> Delta Time: Change in time between two renders
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
