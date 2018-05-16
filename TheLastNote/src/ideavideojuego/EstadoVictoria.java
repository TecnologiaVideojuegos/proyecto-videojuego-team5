/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoVictoria extends BasicGameState {

    private Image fondo;
    private SpriteSheet Alfredo, Mozart, Moldova;
    private Animation AlfredoDance, MozartDance, MoldovaDance;
    private Music musicaVictoria;

    @Override
    public int getID() {
        return 12;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/escenario.jpg");
        musicaVictoria = new Music("Musica/winning.ogg");
        Alfredo = new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164);
        AlfredoDance = new Animation(Alfredo, 100);

        Mozart = new SpriteSheet("Design/BombinWalkSprite_V4.png", 71, 167);
        MozartDance = new Animation(Mozart, 100);

        Moldova = new SpriteSheet("Design/SaxGuyWalkSprite_V4.png", 67, 176);
        MoldovaDance = new Animation(Moldova, 100);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        if (!musicaVictoria.playing()) {
            musicaVictoria.play();
        }

        AlfredoDance.draw(380, 380);
        AlfredoDance.start();

        MozartDance.draw(620, 380);
        MozartDance.start();

        MoldovaDance.draw(500, 380);
        MoldovaDance.start();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        if (entrada.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0,new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        musicaVictoria.play();
    }
}
