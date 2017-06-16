package com.cg.flappy.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cg.flappy.FlappyMain;
import com.cg.flappy.sprites.Bird;


public class PlayState extends State {

    private Bird bird;
    private Texture bg;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, FlappyMain.WIDTH/2, FlappyMain.HEIGHT/2);
        bg = new Texture("backround.png");


    }

    @Override
    protected void handleInput(){

        if(Gdx.input.justTouched())
            bird.jump();


    }



    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2),0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();


    }

    @Override
    public void dispose() {

    }
}
