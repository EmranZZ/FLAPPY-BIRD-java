package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

//creating a new bird class and using that class to
//set the position of our bird texture of our screen
//allowing it to jump up and down
public class Bird {

    private static final int GRAVITY = -15; //Creating Gravity on our bird
    private static final int MOVEMENT = 100 ; //for Horizontal Movement
    //needs a position, we need to know where the bird is in our game world
    private Vector3 position; //X,Y,Z axis but we will use only X and Y axis
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture bird;

    //We need to have some sort of representation of gravity
    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
    }

    public void update(float dt){
        if(position.y > 0) // GRAVITY only when y>0
            velocity.add(0, GRAVITY, 0); //adding gravity to its velocity

        velocity.scl(dt); //Multiplying x,y,z by a delta time
        position.add(MOVEMENT * dt, velocity.y, 0); // adding velocity of y to position

        if(position.y < 0)
            position.y = 0; //if he hits the bottom of the screen, he doesn't keep going

        velocity.scl(1/dt); //it inverses what we scaled previously that means we multiply with 1/dt
        bounds.setPosition(position.x, position.y); //everytime bird moves, we need to update our bound
    }

    //generating some getters so we can get some information back to our play state
    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }

    public void jump(){
        velocity.y = 250; //as the GRAVITY was negative, if we need to get jumping our bird we need to add a positive velocity
    }

    public Rectangle getBounds(){
        return bounds; //creating method for getting bounds
    }
}
