/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoBatalla extends BasicGameState{
    private Music musicaBatalla;
    private Image fondo;
    
    @Override
    public int getID() {
       return 9;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/hallway1.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        ClaseEstatica.getPersonaje().getAnimD().draw(300, 330);
        ClaseEstatica.getEnemigo().getAnimI().draw(600, 330);
        //ClaseEstatica.getPersonaje().getAnimI().draw(300, 150);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }
    
    @Override
       public void enter(GameContainer container, StateBasedGame game) throws SlickException {
           ClaseEstatica.getPersonaje().getMusicB().play();
    }
    
}
