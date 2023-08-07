package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

import java.awt.Rectangle;

public class PlayState extends State{
    private static final int TUBE_SPACING = 125; //space between tubes
    private static final int TUBE_COUNT = 4; //how many tubes total at any given time our game will actually have
    private static final int GROUND_Y_OFFSET = -45; //creating offset for our ground
    private Bird bird;
    private Texture bg; //Texture obj for Background
    private Texture ground; //creating Texture for Ground

    //creating array of tubes
    private Array<Tube> tubes;
    private Vector2 groundPos1, groundPos2; //creating two Vector2 obj to hold the positions of our two grounds

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300); //from where the bird fall
        cam.setToOrtho(false, FlappyDemo.WIDTH /2, FlappyDemo.HEIGHT /2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2((cam.position.x - cam.viewportWidth / 2), GROUND_Y_OFFSET); //x started originally from the left side of our camera
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

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
        updateGround(); //update ground in every change
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80; //update camera of game world based on where the bird is! That means we're going to follow the bird //adding 80 for offset the camera just a little bit in front of our bird

        //applying logic for how to remove of reposition a tube when it gets off the camera viewport
        for(Tube tube: tubes){
            //finding the left side of the screen
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){ //camera were in center of the screen. So move it to left of the screen by dividing with 2
                tube.rePosition(tube.getPosTopTube().x +((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())){ //checking whether tubes touching the bird?
                gsm.set(new GameOverState(gsm)); //when collides to tube, it will show GameOver State
            }
        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){ //when bird is in the ground or under the ground
            gsm.set(new GameOverState(gsm));  ////when collides to Ground, it will show GameOver State
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
        sb.draw(ground, groundPos1.x, groundPos1.y); //drawing ground
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

    }

    @Override
    public void dispose() {
        ground.dispose();
    }

    private void updateGround(){
        //if the camera is passed where the position of our ground actually is ie. What would actually show on the screen
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
