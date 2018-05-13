/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoPasillo1 extends BasicGameState{
    private AppGameContainer contenedor;
    private float x,y;
    private String texto;
    private Animation fonsiD,fonsiI;
    private SpriteSheet spriteFonsiD, spriteFonsiI;
    private float ang;
    private Image fondo;
    private int cX = 1080,cY=607;
    private Music music;
    private Sound step;
    private boolean derecha;
    private Personaje LuisFonsi;
    
    @Override
    public int getID() {
        return 3;
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
        derecha=true;
        ang = 200f;
        Sound laser = new Sound(("Musica/Sonidos/fx_laser.ogg"));
                
        spriteFonsiD = new SpriteSheet("Design/SaxGuyWalkSprite_V4.png", 67, 176);
        spriteFonsiI = new SpriteSheet("Design/KimJong2Sprite1.png", 112, 180);
        fonsiD = new Animation(spriteFonsiD,150);
        fonsiI = new Animation(spriteFonsiI,150);

        Ataque Microfonazo = new Ataque(10, 20, "Microfonazo", "Lanzará un micrófono para causar un daño leve", 10, laser);
        Ataque Flow = new Ataque(30, 10, "Flow", "Moverá sus caderas para causar un daño brutal en la vista del enemigo", 20, laser);
        Ataque Despacito = new Ataque(40, 5, "Despacito", "Cantará su mitica canción Despacito para causar daño letal en los oidos del enemigo", 30, laser);
        LuisFonsi = new Personaje(350,"Ludis Fonsi", new SpriteSheet("Design/KimJong2Sprite1.png", 70, 176), fonsiD, fonsiI, null, null);
        LuisFonsi.getAtaques().add(Microfonazo);
        LuisFonsi.getAtaques().add(Flow);
        LuisFonsi.getAtaques().add(Despacito);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //music = new Music("Musica/rock_hall.ogg", false);
        fondo.draw();
        g.drawString("Pasillo 1", 50, 600);
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
        if(!ClaseEstatica.getPersonaje().getMusicH().playing()){
            ClaseEstatica.getPersonaje().getMusicH().play();
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        ang += delta * 0.4f;
	if (container.getInput().isKeyDown(Input.KEY_M)){
            ClaseEstatica.getPersonaje().getMusicH().play();        
                        
                        
        }
        if (container.getInput().isKeyDown(Input.KEY_N)){
            ClaseEstatica.getPersonaje().getMusicH().pause();
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
                ClaseEstatica.setEnemigo(LuisFonsi);
                //game.enterState(9); //Prueba
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
                game.enterState(4,new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
            //ClaseEstatica.getPersonaje().getMusicH().play();
            this.x = 30; //Coordenadas donde empieza el personaje
            this.y = 257;
    }
    
}
