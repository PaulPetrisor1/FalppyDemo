package com.cg.flappy.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cg.flappy.FlappyMain;

public class MenuState extends State {

    private Texture backround;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {

        super(gsm);
        backround = new Texture("backround.png");
        playButton = new Texture("playbtn.png");



    }

    @Override
   public void handleInput() {

        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));


        }
    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(backround, 0,0, FlappyMain.WIDTH, FlappyMain.HEIGHT);
        sb.draw(playButton, (FlappyMain.WIDTH / 2) - (playButton.getWidth() / 2), FlappyMain.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {
            backround.dispose();
            playButton.dispose();
        System.out.println("Menu state disposed");
    }
}
