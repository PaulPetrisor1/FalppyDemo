package com.cg.flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by PPR on 16/06/2017.
 */

public class Tubes {

    public static final int TUBE_WIDHT = 52;


    private static final int GAP = 100;
    private static final int FLUCT = 130;
    private static final int LOWEST_OPENING = 120;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private Rectangle boundsTop, boundsBot;

    public Tubes(float x){

        topTube = new Texture("topTube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCT) + GAP + LOWEST_OPENING );
        posBotTube = new Vector2(x, posTopTube.y - GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(),bottomTube.getHeight());

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

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float x){
         posTopTube.set(x, rand.nextInt(FLUCT)+GAP+LOWEST_OPENING);
         posBotTube.set(x, posTopTube.y - GAP - bottomTube.getHeight());

        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);

    }

    public boolean collision(Rectangle player){

        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose(){

        topTube.dispose();
        bottomTube.dispose();
    }


}

