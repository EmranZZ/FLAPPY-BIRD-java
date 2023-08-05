package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52; //Texture size 52 pixels
    private static final int FLUCTUATION = 130; //tube can move up and down randomly between 0 and 130
    private static final int TUBE_GAP = 100; //Gap between topTube and BottomTube
    private static final int LOWEST_OPENING = 120;//lowest opening for the bottom tube so the top of the bottom tube can't be below the area of the screen
    //Creating two texture; one for top tube, another for bottom tube
    private Texture topTube, bottomTube;
    //Vector2 holder for the position of tube
    private Vector2 posTopTube, posBottomTube;
    private Rectangle boundsTop, boundsBottom; //invisible rectangle for collision
    private Random rand; //will generate the tube in random position on the screen

    //creating constructor
    public Tube(float x){ //coordinate of x asis where the tube is going to start
        topTube = new Texture("topTube.png");
        bottomTube = new Texture("bottomTube.png");
        rand = new Random();

        //setting position of top tube and bottom tube
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y-TUBE_GAP-bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight()); //x,y coordinate of invisible rectangle with Width and Height
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void rePosition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y-TUBE_GAP-bottomTube.getHeight());

        boundsTop.setPosition(posTopTube.x, posTopTube.y); //have to reposition our invisible rectangle tubes
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom); //will return true
    }
}