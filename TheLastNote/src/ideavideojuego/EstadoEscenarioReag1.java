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
public class EstadoEscenarioReag1 extends BasicGameState {

    private AppGameContainer contenedor;
    private float personajex, personajey, enemigox, enemigoy;
    private Sprite puntero;
    private String texto;
    private int tiempo;
    private Animation anim, alfredoD, alfredoI;
    private SpriteSheet sprite, spriteAlfredoD, spriteAlfredoI;
    private float ang;
    private Image fondo;
    private int cX = 1080, cY = 607;
    private Music music;
    private boolean derecha;
    private Personaje LuisFonsi;
    private Personaje personaje;
    private Sound step;
    private Rectangle perR, perE;
    private boolean colision;
    private int estado;

    @Override
    public int getID() {
        return 4;
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
        music = new Music("Musica/rock_hall.ogg", false);
        spriteAlfredoD = new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164);
        spriteAlfredoI = new SpriteSheet("Design/FreddieWalk_V3.png", 67, 164);
        alfredoD = new Animation(spriteAlfredoD, 100);
        alfredoI = new Animation(spriteAlfredoI, 100);
        derecha = true;
        ang = 200f;
        puntero = new Sprite("Design/cursor1.png");

        //Creación ENEMIGO
        /*Ataque Microfonazo = new Ataque(10, 20, "Microfonazo", "Lanzará un micrófono para causar un daño leve", 10);
        Ataque Flow = new Ataque(30, 10, "Flow", "Moverá sus caderas para causar un daño brutal en la vista del enemigo", 10);
        Ataque Despacito = new Ataque(40, 5, "Despacito", "Cantará su mitica canción Despacito para causar daño letal en los oidos del enemigo", 10);
        //LuisFonsi = new Personaje(250,"Ludis Fonsi", new SpriteSheet("Design/SaxoStill.png", 170, 410));
        /*LuisFonsi.getAtaques().add(Microfonazo);
        LuisFonsi.getAtaques().add(Flow);
        LuisFonsi.getAtaques().add(Despacito);*/
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        g.draw(perE);
        g.draw(perR);
        if (derecha) {
            //alfredoD.draw(x,y);
            ClaseEstatica.getPersonaje().getAnimD().draw(personajex, personajey);
            ClaseEstatica.getPersonaje().getAnimD().stop();
        } else {
            //alfredoI.draw(x,y);
            ClaseEstatica.getPersonaje().getAnimI().draw(personajex, personajey);
            ClaseEstatica.getPersonaje().getAnimI().stop();
        }
        ClaseEstatica.getEnemigo().getAnimI().draw(enemigox, enemigoy);

        if (colision) {
            g.drawString("¿QUIERES ENFRENTARTE AL TEMIBLE LUIS FONSI?", 50, 620);
            g.drawString("Si, no tengo miedo", 50, 700);
            g.drawString("Nooo, no estoy preparado", 500, 700);
            if (estado == 0) {
                puntero.draw(150, 600);
            }
            else if (estado == 1) {
                puntero.draw(600, 500);
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
            //game.enterState(9,new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
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
                if (personajex < 1018) {
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
                if (personajey < 380) {
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
                    game.enterState(9, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                } else if (estado == 1) {
                    game.enterState(4);
                }
            }
        }

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        music.play();
        perR = new Rectangle(personajex, personajey, ClaseEstatica.getPersonaje().getAnimD().getWidth(), 50);
        perE = new Rectangle(enemigox, enemigoy, ClaseEstatica.getEnemigo().getAnimD().getWidth(), 50);
    }
}
