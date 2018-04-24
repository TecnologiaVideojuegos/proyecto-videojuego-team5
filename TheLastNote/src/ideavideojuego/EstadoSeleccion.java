/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoSeleccion extends BasicGameState {

    private static final Punto ALFREDO;
    private static final Punto MOLDOVA;
    private static final Punto LUDWIG;
    private Sprite botonALFREDO;
    private Sprite botonMOLDOVA;
    private Sprite botonLUDWIG;
    private Image fondo;
    private int indicador;
    private Sprite puntero;
    
    static {
        ALFREDO = new Punto(275.0f, 125.0f);
        MOLDOVA = new Punto(275.0f, 200.0f);
        LUDWIG = new Punto(275.0f, 200.0f);
    }
    
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.botonALFREDO = new Sprite("Design/FreddyStill.png", new Punto(400.0f, 150.0f));
        this.botonMOLDOVA = new Sprite("Design/SaxoStill.png", new Punto(400.0f, 225.0f));
        this.botonLUDWIG = new Sprite("Design/BombinStill.png", new Punto(400.0f, 225.0f));
        puntero = new Sprite("Design/PunteroLoL.png", ALFREDO);
        this.indicador = 0;
    }
        

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        botonALFREDO.draw();
        botonALFREDO.draw();
        botonALFREDO.draw();
        puntero.draw();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }
}
