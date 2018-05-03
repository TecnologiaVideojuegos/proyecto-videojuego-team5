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
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Michael Lofer
 */
public class EstadoMenu extends BasicGameState{
    private Image fondo;
    private Sprite puntero;
    private Music musica;
    //private Sound click;
    private static final Punto JUGAR = new Punto(450, 180);
    private static final Punto SALIR = new Punto(450, 400);
    
    private int indicador;
    
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/menuTekken.jpg");
        puntero = new Sprite("Design/cursor1.png", JUGAR);
        musica = new Music("Musica/musicaFinal.ogg");
        //click = new Sound("Musica/menuClick.wav");
        indicador=0;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();;
        puntero.draw();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        if(entrada.isKeyPressed(Input.KEY_UP)){
            indicador=0;
            puntero.setPosicion(JUGAR);
            /*if(!click.playing())
                    click.play();*/
        }else if(entrada.isKeyPressed(Input.KEY_DOWN)){
            indicador=1;
            puntero.setPosicion(SALIR);
            /*if(!click.playing())
                    click.play();*/
        }else if(entrada.isKeyPressed(Input.KEY_ENTER)){
            if(indicador==0){
                /*if(!click.playing())
                    click.play();*/
                game.enterState(1);
            }else{
                System.exit(0);
            }
        } 
    }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        musica.play();
    }
}
