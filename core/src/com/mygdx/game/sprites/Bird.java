package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

//creating a new bird class and using that class to
//set the position of our bird texture of our screen
//allowing it to jump up and down
public class Bird {
    //it needs a position
    //we need to know where the bird is in our game world
    private Vector3 position; //X,Y,Z axis but we will use only X and Y axis
    private Vector3 velocity;
    private Texture bird;
    private static final int GRAVITY = -15; //Creating Gravity on our bird
    //We need to have some sort of representation of gravity
    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
    }

    public void update(float dt){
        velocity.add(0, GRAVITY, 0); //adding gravity to its velocity
        velocity.scl(dt); //Multiplying x,y,z by a delta time
        position.add(0, velocity.y, 0); // adding velocity of y to position

        velocity.scl(1/dt); //it inverses what we scaled previously that means we multiply with 1/dt
    }

    //generating some getters so we can get some information back to our play state
    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }
}
