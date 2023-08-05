package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

import java.awt.Rectangle;

public class PlayState extends State{
    private static final int TUBE_SPACING = 125; //space between tubes
    private static final int TUBE_COUNT = 4; //how many tubes total at any given time our game will actually have

    private Bird bird;
    private Texture bg; //Texture obj for Background


    //creating array of tubes
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300); //from where the bird fall
        cam.setToOrtho(false, FlappyDemo.WIDTH /2, FlappyDemo.HEIGHT /2);
        bg = new Texture("bg.png");

        tubes = new Array<Tube>();

        for(int i=1; i<= TUBE_COUNT ; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80; //update camera of game world based on where the bird is! That means we're going to follow the bird //adding 80 for offset the camera just a little bit in front of our bird

        //applying logic for how to remove of reposition a tube when it gets off the camera viewport
        for(Tube tube: tubes){
            //finding the left side of the screen
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){ //camera were in center of the screen. So move it to left of the screen by dividing with 2
                tube.rePosition(tube.getPosTopTube().x +((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())){ //checking whether tubes touching the bird?
                gsm.set(new PlayState(gsm));
            }
        }
        cam.update(); //for every change updating camera with update() method
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0 ); //drawing background in PlayState
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for( Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
