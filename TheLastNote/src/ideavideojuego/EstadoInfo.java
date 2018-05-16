/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoInfo extends BasicGameState {

    private Image fondoClassic, fondoJazz, fondoRock;
    private int indicador;

    @Override
    public int getID() {
        return 20;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondoClassic = new Image("Design/InfoClassic.PNG");
        fondoJazz = new Image("Design/InfoJazz.PNG");
        fondoRock = new Image("Design/InfoRock.PNG");

        indicador = 0;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        switch (indicador) {
            case 0:
                fondoClassic.draw(100, 150);
                break;
            case 1:
                fondoRock.draw(100, 150);
                break;
            case 2:
                fondoJazz.draw(100, 150);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        if (entrada.isKeyPressed(Input.KEY_RIGHT)) {
            if (indicador == 0) {
                indicador = 1;
                if (!ClaseEstatica.getClick().playing()) {
                    ClaseEstatica.getClick().play();
                }
            } else if (indicador == 1) {
                indicador = 2;
                if (!ClaseEstatica.getClick().playing()) {
                    ClaseEstatica.getClick().play();
                }
            }
        } else if (entrada.isKeyPressed(Input.KEY_LEFT)) {
            if (indicador == 1) {
                indicador = 0;
                if (!ClaseEstatica.getClick().playing()) {
                    ClaseEstatica.getClick().play();
                }
            } else if (indicador == 2) {
                indicador = 1;
                if (!ClaseEstatica.getClick().playing()) {
                    ClaseEstatica.getClick().play();
                }
            }
        } else if (entrada.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }

}
