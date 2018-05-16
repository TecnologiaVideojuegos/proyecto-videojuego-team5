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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.RotateTransition;
import org.newdawn.slick.state.transition.SelectTransition;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoEscenarioKPOP3 extends BasicGameState {

    private float personajex, personajey, enemigox, enemigoy;
    private Sprite puntero;
    private float ang;
    private Image fondo;
    private int cX = 1080, cY = 607;
    private Music music;
    private boolean derecha;
    private Rectangle perR, perE;
    private boolean colision;
    private int estado;

    @Override
    public int getID() {
        return 8;
    }

    /*public PantallaInicio(Personaje personaje){
        this.personaje=personaje;
    }*/
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.personajex = 343; //Coordenadas donde empieza el personaje
        this.personajey = 349;
        this.enemigox = 681;
        this.enemigoy = 349;
        estado = 0;
        fondo = new Image("Design/scenario1.png"); //Imagen de fondo
        derecha = true;
        ang = 200f;
        puntero = new Sprite("Design/cursor1.png");
        colision = false;

        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        if (derecha) {
            //alfredoD.draw(x,y);
            ClaseEstatica.getPersonaje().getAnimD().draw(personajex, personajey);
        } else {
            //alfredoI.draw(x,y);
            ClaseEstatica.getPersonaje().getAnimI().draw(personajex, personajey);
        }
        ClaseEstatica.getEnemigo().getAnimI().draw(enemigox, enemigoy);

        if (colision) {
            ClaseEstatica.getPersonaje().getAnimD().stop();
            g.drawString("¿QUIERES ENFRENTARTE AL TEMIBLE KIM JONG-DOS??", 50, 620);
            g.drawString("Si, no tengo miedo", 50, 654);
            g.drawString("Nooo, no estoy preparado", 500, 654);
            if (estado == 0) {
                puntero.draw(221, 654);
            }
            else if (estado == 1) {
                puntero.draw(723, 654);
            }

        }

        g.drawString("Coordenadas :" + personajex + ", " + personajey, 30, 30);
        //g.drawString("UNTIL THE LAST NOTE", 30, 30);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        ang += delta * 0.4f;
        if (container.getInput().isKeyDown(Input.KEY_M)) {
            music.play();
            //music.resume();

        }
        if (container.getInput().isKeyDown(Input.KEY_N)) {
            music.pause();
        }
        if (perR.intersects(perE)) {
            colision = true;
            if(derecha){
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
            }else{
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimI().setCurrentFrame(0);
            }
        }
        if (!perR.intersects(perE)) {
            colision = false;
        }
        if (!colision) {
            if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimI().start();
                if (personajex > 0) {
                    personajex -= delta * 0.4f;
                    perR.setX(personajex);
                    derecha = false;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                }
            } else if (container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyDown(Input.KEY_D)) {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimD().start();
                if (personajex < 1016) {
                    personajex += delta * 0.4f;
                    perR.setX(personajex);
                    derecha = true;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                }
            } else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimD().start();
                if (personajey > 293) {
                    personajey -= delta * 0.4f;
                    perR.setY(personajey);
                    derecha = true;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                }
            } else if (container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_S)) {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimD().start();
                if (personajey < 556) {
                    personajey += delta * 0.4f;
                    perR.setY(personajey);
                    derecha = true;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                }
            } else {
                if (derecha) {
                    ClaseEstatica.getPersonaje().getAnimD().stop();
                    ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
                } else {
                    ClaseEstatica.getPersonaje().getAnimI().stop();
                    ClaseEstatica.getPersonaje().getAnimI().setCurrentFrame(0);
                }
            }
        } else {
            if (container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
                if (estado == 0) {
                    estado = 1;
                }
            } else if (container.getInput().isKeyPressed(Input.KEY_LEFT)) {
                if (estado == 1) {
                    estado = 0;
                }
            } else if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
                if (estado == 0) {
                    ClaseEstatica.getPersonaje().restaurarTodo(); //LE SUBIMOS TODOS LOS STATS
                    game.enterState(11, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                } else if (estado == 1) {
                    if(derecha) {
                        this.personajex = 595; //Coordenadas donde empieza el personaje
                        this.personajey = 359;
                        perR.setX(personajex);
                        perR.setY(personajey);
                    }else{
                        this.personajex = 799;
                        this.personajey = 359;
                        perR.setX(personajex);
                        perR.setY(personajey);
                    }                    
                }
            }
        }

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        perR = new Rectangle(personajex, personajey, ClaseEstatica.getPersonaje().getAnimD().getWidth(), 50);
        perE = new Rectangle(enemigox, enemigoy, ClaseEstatica.getEnemigo().getAnimD().getWidth(), 50);
        this.personajex = 343; //Coordenadas donde empieza el personaje
        this.personajey = 349;
    }
}
