/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
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
 * @author Álvaro Zamorano
 */
public class EstadoPasillo3 extends BasicGameState{
    private AppGameContainer contenedor;
    private float x,y;
    private String texto;
    private int tiempo;
    private Animation anim,alfredoD,alfredoI;
    private SpriteSheet sprite,spriteAlfredoD,spriteAlfredoI;
    private float ang;
    private Image fondo;
    private int cX = 1080,cY=607;
    private Music music;
    private boolean derecha;
    private Personaje personaje;
    private Sound step;
    
    @Override
    public int getID() {
        return 7;
    }
    
    /*public EstadoPasillo1(Personaje personaje){
        this.personaje=personaje;
    */

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.x = 571; //Coordenadas donde empieza el personaje
        this.y = 257;
        //texto = "Hello World";
        fondo = new Image("Design/hallway1.png"); //Imagen de fondo
        music = new Music("Musica/rock_hall.ogg", false);
        spriteAlfredoD = new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164);
        spriteAlfredoI = new SpriteSheet("Design/FreddieWalk_V3.png", 67 ,164);
        alfredoD = new Animation(spriteAlfredoD,100);
        alfredoI = new Animation(spriteAlfredoI,100);
        derecha=true;
        ang = 200f;
        //contenedor.getGraphics().setBackground(Color.gray);
        //anim.stop();
        //anim.setAutoUpdate(true);
        

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //music = new Music("Musica/rock_hall.ogg", false);
        fondo.draw();
        g.drawString("Pasillo 3", 50, 600);
        //System.out.println("ESTADO EN EL REDNER --> "+ ClaseEstatica.getPersonaje().getNombre());
        if(derecha){
            //alfredoD.draw(x,y);
            ClaseEstatica.getPersonaje().getAnimD().draw(x, y);
            ;
        }
        else{
            //alfredoI.draw(x,y);
            ClaseEstatica.getPersonaje().getAnimI().draw(x, y);
        }
        g.drawString("Coordenadas :" + x + ", " + y, 30, 30);
        //g.drawString("UNTIL THE LAST NOTE", 30, 30);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        ang += delta * 0.4f;
	if (container.getInput().isKeyDown(Input.KEY_M)){
			music.play();                       
                        //music.resume();
                        
                        
        }
        if (container.getInput().isKeyDown(Input.KEY_N)){
			music.pause();
        }
        if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getAnimI().start();
            if(x>0){
                x -= delta * 0.4f;
                derecha=false;
                if (!ClaseEstatica.getSonidoPaso().playing()) 
                    ClaseEstatica.getSonidoPaso().play();
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyDown(Input.KEY_D)) {
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().start();
            if(x<1018){
                x += delta * 0.4f;
                derecha=true;
                if (!ClaseEstatica.getSonidoPaso().playing()) 
                    ClaseEstatica.getSonidoPaso().play();
            }else{
                game.enterState(8);

            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().start();
            if(y>257){
                y -= delta * 0.4f;
                derecha=true;
                if (!ClaseEstatica.getSonidoPaso().playing()) 
                    ClaseEstatica.getSonidoPaso().play();
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_S) ) {
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().start();
            if(y<354){
                y += delta * 0.4f;
                derecha=true;
                if (!ClaseEstatica.getSonidoPaso().playing()) 
                    ClaseEstatica.getSonidoPaso().play();
            }
	}
        else{
            if (derecha){
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
            }
            else{
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimI().setCurrentFrame(0);
            }
        }            
    }
    @Override
       public void enter(GameContainer container, StateBasedGame game) throws SlickException {
       music.play();
    }
    
}
