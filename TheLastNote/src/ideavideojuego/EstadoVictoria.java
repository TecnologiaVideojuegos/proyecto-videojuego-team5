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
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
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
    private String texto;
    private int x, y, tiempo;
    private static UnicodeFont font;

    @Override
    public int getID() {
        return 12;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/final.jpg");
        musicaVictoria = new Music("Musica/winning.ogg");
        Alfredo = new SpriteSheet("Design/FreddieDance1.png", 105, 171);
        AlfredoDance = new Animation(Alfredo, 100);

        java.awt.Font fuenteAWT = new java.awt.Font("Rockwell Condensed", 0, 24);
        font = new UnicodeFont(fuenteAWT);
        font.addAsciiGlyphs();
        ColorEffect colorFuente = new ColorEffect(java.awt.Color.WHITE);
        font.getEffects().add(colorFuente);
        font.loadGlyphs();
        Mozart = new SpriteSheet("Design/BombinDance1.png", 107, 171);
        MozartDance = new Animation(Mozart, 100);

        Moldova = new SpriteSheet("Design/SaxGuyDance1.png", 106, 171);
        MoldovaDance = new Animation(Moldova, 100);

        texto = "GANASTE!!!! PRÓXIMAMENTE MÁS MAPAS Y PERSONAJES";

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        if (!musicaVictoria.playing()) {
            musicaVictoria.play();
        }

        AlfredoDance.draw(200, 100);
        AlfredoDance.start();

        MoldovaDance.draw(520, 100);
        MoldovaDance.start();

        MozartDance.draw(800, 100);
        MozartDance.start();

        font.drawString(x, 300, texto);
        g.drawString("Esc para salir", 10, 10);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        if (entrada.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

        tiempo += delta;
        if (tiempo > 50) {
            tiempo = 0;
            x += 5;
            if (x > 400) {
                x = 30;
            }
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        musicaVictoria.play();
    }
}
