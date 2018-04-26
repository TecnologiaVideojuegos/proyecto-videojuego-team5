/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
    private IdeaVideojuego p;
    private Personaje personaje;
    
    static {
        ALFREDO = new Punto(0,0);
        MOLDOVA = new Punto(300, 0);
        LUDWIG = new Punto(600, 0);
    }
    
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.botonALFREDO = new Sprite("Design/FreddyStill.png", ALFREDO);
        this.botonMOLDOVA = new Sprite("Design/SaxoStill.png", MOLDOVA);
        this.botonLUDWIG = new Sprite("Design/BombinStill.png", LUDWIG);
        puntero = new Sprite("Design/PunteroLoL.png", ALFREDO);
        this.indicador = 0;
        personaje = new Personaje(200, "Alfredo Mercurio");
    }
        

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        botonALFREDO.draw();
        botonMOLDOVA.draw();
        botonLUDWIG.draw();
        puntero.draw();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        if(entrada.isKeyPressed(Input.KEY_RIGHT)){
            if (indicador==0){
                indicador=1;
                puntero.setPosicion(MOLDOVA);
            }
            else if(indicador==1){
                indicador=2;
                puntero.setPosicion(LUDWIG);
            }
        }else if(entrada.isKeyPressed(Input.KEY_LEFT)){
            if(indicador==1){
                indicador=0;
                puntero.setPosicion(ALFREDO);
            }
            else if(indicador==2){
                indicador=1;
                puntero.setPosicion(MOLDOVA);
            }
        }else if(entrada.isKeyPressed(Input.KEY_ENTER)){
            if(indicador==0 || indicador ==1 || indicador ==2){
                //game.enterState(3);
                //game.addState(new EstadoPasillo1(personaje));
                game.enterState(3);
            }
        } 
    }
}
