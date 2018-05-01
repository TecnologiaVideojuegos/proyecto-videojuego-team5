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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoEscenarioKPOP3 extends BasicGameState{
    private AppGameContainer contenedor;
    private float personajex,personajey,enemigox,enemigoy;
    private Animation ludwigD,ludwigI;
    private SpriteSheet spriteLudwigD,spriteLudwigI;
    private float ang;
    private Image fondo;
    private Music music;
    private boolean derecha;
    private Personaje LudwigvanMozart;
    private Personaje personaje;
    private Sound step;
    private Rectangle perR,perE;
    private boolean colision;
    
    @Override
    public int getID() {
        return 8;
    }
    
    /*public PantallaInicio(Personaje personaje){
        this.personaje=personaje;
    }*/

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.personajex = 20; //Coordenadas donde empieza el personaje
        this.personajey = 257;
        
        this.enemigox = 100;
        this.enemigoy=257;
        fondo = new Image("Design/hallway1.png"); //Imagen de fondo
        music = new Music("Musica/rock_hall.ogg", false);
        step = new Sound("Musica/step.ogg");
        derecha=true;
        colision = false;
        ang = 200f;
        
        spriteLudwigD = new SpriteSheet("Design/BombinWalkSprite_V4.png", 71, 167);
        spriteLudwigI = new SpriteSheet("Design/BombinWalkSprite_V3.png", 71, 167);
        ludwigD = new Animation(spriteLudwigD,100);
        ludwigI = new Animation(spriteLudwigI,100);
//        LudwigvanMozart = new Personaje(400,"Ludwin van Mozart",new SpriteSheet("Design/BombinWalkSprite_V4.png", 71, 167),ludwigD,ludwigI);
        //Creación EN   EMIGO
        Ataque Misilazo = new Ataque(10, 20, "Misilazo", "Lanzará un misil para causar un daño leve", 10);
        Ataque Kpop = new Ataque(30, 10, "Ritmo K-POP", "Moverá su cuerpo al ritmo de K-POP para causar un daño brutal a su enemigo", 10);
        Ataque Nuclear = new Ataque(40, 5, "Ataque nuclear", "Lanzará un ataque nuclear para causar un daño LETAL!!!", 10);
        /*KimJongDos = new Personaje(250, "Kim Jong-Dos");
        KimJongDos.getAtaques().add(Misilazo);
        KimJongDos.getAtaques().add(Kpop);
        KimJongDos.getAtaques().add(Nuclear);*/
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        g.draw(perR);
        g.draw(perE);
        if(derecha){
            //alfredoD.draw(x,y);
            ClaseEstatica.getPersonaje().getAnimD().draw(personajex, personajey);
        }
        else{
            //alfredoI.draw(x,y);
            ClaseEstatica.getPersonaje().getAnimI().draw(personajex, personajey);
        }
        ludwigD.draw(enemigox, enemigoy);
        ludwigD.stop();
        
        if(colision){
            g.drawString("Colision!!", 500, 30);
        }
        g.drawString("Coordenadas :" + personajex+ ", " + personajey, 30, 30);
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
        if(perR.intersects(perE)){
            colision = true;
            //game.enterState(11);
        }
        if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A) && !colision) {
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getAnimI().start();
            if(personajex>0){
                personajex-= delta * 0.4f;
                perR.setX(personajex);
                derecha=false;
                if(!step.playing())
                    step.play();
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyDown(Input.KEY_D)) {
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().start();
            if(personajex<1018){
                personajex += delta * 0.4f;
                perR.setX(personajex);
                derecha=true;
                if(!step.playing())
                    step.play();
            }else{
                //game.enterState(9);

            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().start();
            if(personajey>257){
                personajey -= delta * 0.4f;
                perR.setY(personajey);
                derecha=true;
                if(!step.playing())
                    step.play();
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_S) ) {
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().start();
            if(personajey<354){
                personajey += delta * 0.4f;
                perR.setY(personajey);
                derecha=true;
                if(!step.playing())
                    step.play();
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
       perR = new Rectangle(personajex,personajey,ClaseEstatica.getPersonaje().getAnimD().getWidth(),50);
       perE = new Rectangle(enemigox,enemigoy,ludwigD.getWidth(),50);
    }
}
