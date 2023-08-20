package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyDemo;

public class GameOverState extends State{
    private Texture gameOver;
    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        gameOver = new Texture("gameOverState.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(gameOver,0, 0, FlappyDemo.WIDTH  , FlappyDemo.HEIGHT );
        sb.end();
    }

    @Override
    public void dispose() {
        gameOver.dispose();
    }
}
