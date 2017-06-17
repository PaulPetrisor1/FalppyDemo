package com.cg.flappy.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.cg.flappy.FlappyMain;
import com.cg.flappy.sprites.Bird;
import com.cg.flappy.sprites.Tubes;


public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;


    private Array<Tubes> tubesArray;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, FlappyMain.WIDTH/2, FlappyMain.HEIGHT/2);
        bg = new Texture("backround.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(),GROUND_Y_OFFSET);
      ;

        tubesArray = new Array<Tubes>();

        for(int i=1; i<= TUBE_COUNT; i++){
            tubesArray.add(new Tubes(i * (TUBE_SPACING + Tubes.TUBE_WIDHT )));
        }


    }

    @Override
    protected void handleInput(){

        if(Gdx.input.justTouched())
            bird.jump();


    }



    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

       for (int i = 0; i < tubesArray.size; i++) {
            Tubes tube = tubesArray.get(i);

            if(cam.position.x -(cam.viewportWidth/2)> tube.getPosTopTube().x + tube.getTopTube().getWidth()){

                tube.reposition(tube.getPosTopTube().x + (Tubes.TUBE_WIDHT + TUBE_SPACING) *  TUBE_COUNT);
            }

            if (tube.collision(bird.getBounds()))
                gsm.set(new PlayState(gsm));

        }
        if(bird.getPosition().y <= ground.getHeight()+GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm));
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2),0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for(Tubes tube : tubesArray) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();


    }

    @Override
    public void dispose() {

        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tubes tube : tubesArray)
            tube.dispose();
        System.out.println("Play state disposed");

    }

    private void updateGround(){

        if (cam.position.x - (cam.viewportWidth / 2 )> groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2 , 0 );

        if (cam.position.x - (cam.viewportWidth / 2 )> groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2 , 0 );

    }


}

