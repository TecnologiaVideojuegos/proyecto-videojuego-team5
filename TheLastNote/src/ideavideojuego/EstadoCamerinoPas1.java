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
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoCamerinoPas1 extends BasicGameState {

    private float x, y;
    private float ang;
    private Image fondo1, fondo2, fondo3, fondo4;
    private Sound fail;
    private boolean derecha, mover, baile, introduccion1, introduccion2, introduccion3;
    private int dato, pasillo;
    private String texto;

    @Override
    public int getID() {
        return 13;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo1 = new Image("Design/camerino.png"); //Imagen de fondo
        fondo2 = new Image("Design/camerino.png");
        fondo3 = new Image("Design/camerino.png");
        derecha = true;
        mover = false;
        baile = false;
        ang = 200f;
        ClaseEstatica.setFail(fail);
        texto = "";
        introduccion1 = false;
        introduccion2 = false;
        introduccion3 = false;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //fondo1.draw();
        if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo1") {
            fondo1.draw();
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo2") {
            fondo2.draw();
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo3") {
            fondo3.draw();
        }
        if (introduccion1 || introduccion2 || introduccion3) {
            ClaseEstatica.getPersonaje().getDial().draw(216, 537);
            g.drawString(texto, 330, 560);
        }
        if (mover) {
            if (derecha) {
                ClaseEstatica.getPersonaje().getAnimD().draw(x, y);
            } else if (baile) {
                ClaseEstatica.getPersonaje().getBaile().draw(x, y);
            } else {
                ClaseEstatica.getPersonaje().getAnimI().draw(x, y);
            }
        } else {
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
        }
        g.drawString("Coordenadas :" + x + ", " + y, 30, 30);
        //g.drawString("UNTIL THE LAST NOTE", 30, 30);
        if (!ClaseEstatica.getPersonaje().getMusicH8().playing()) {
            ClaseEstatica.getPersonaje().getMusicH8().play();
        }
        mover = true;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        ang += delta * 0.4f;
        dato += delta;
        if (container.getInput().isKeyDown(Input.KEY_M)) {
            ClaseEstatica.getPersonaje().getMusicH8().play();;

        }
        if (container.getInput().isKeyDown(Input.KEY_N)) {
            ClaseEstatica.getPersonaje().getMusicH8().pause();
        }
        if (container.getInput().isKeyDown(Input.KEY_B)) {
            derecha = false;
            baile = true;
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getBaile().start();
        }
        /*if (container.getInput().isKeyDown(Input.KEY_DELETE)) 
            game.enterState(1);*/
        if (introduccion1) {
            switch (dato) {
                case 500:
                    texto = "Buajjj. ¡Qué mal huele aquí!. Hace\n mucho tiempo que no entra nadie aqui";
                    break;
                case 4000:
                    texto = "¿De quién sería este camerino?.\n OHHH. Ahí hay algo";
                    break;
                case 7000:
                    texto = "Seguro que me dará más fuerzas para\nderrotar a mis adversarios.";
                    break;
                case 10000:
                    introduccion1 = false;
            }
        } else if (introduccion2) {
            switch (dato) {
                case 500:
                    texto = "Dios mio!! Casi muero ahí dento. Ese\n Luis Fonsi y su reggeaton...";
                    break;
                case 4000:
                    texto = "¡¡¡No destruirán la MÚSICA!!!";
                    break;
                case 7000:
                    texto = "Ha sido una batalla muy dura y\nnecesito fuerzas";
                    break;
                case 10000:
                    texto = "Esperaaa. ¿¿Qué es eso que hay ahí??\nSeguro que me dará más fuerzas...";
                    break;
                case 14000:
                    texto = "...¡¡¡Contra aquellos que quieren\n retar mi RITMO!!!";
                    break;
                case 17000:
                    introduccion2 = false;
            }
        } else {
            if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimI().start();
                if (x > 217) {
                    x -= delta * 0.4f;
                    derecha = false;
                    baile = false;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                }
            } else if (container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyDown(Input.KEY_D)) {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimD().start();
                if (x < 796) {
                    x += delta * 0.4f;
                    derecha = true;
                    baile = false;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                }
            } else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimD().start();
                if (y > 271) {
                    y -= delta * 0.4f;
                    derecha = true;
                    baile = false;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                }
            } else if (container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_S)) {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimD().start();
                if (y < 480) {
                    y += delta * 0.4f;
                    derecha = true;
                    baile = false;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                } else if ((y >= 480) && (x >= 485) && (x <= 530)) {

                    ClaseEstatica.getPersonaje().getAnimD().stop();
                    ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);

                    if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo1") {
                        game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                    } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo2") {
                        game.enterState(5, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                    } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo2") {
                        game.enterState(7, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        if (!ClaseEstatica.getPersonaje().getMusicH8().playing()) {
            ClaseEstatica.getPersonaje().getMusicH8().play();
        }
        this.x = 500; //Coordenadas donde empieza el personaje
        this.y = 367;
        mover = false;
        baile = false;
        ClaseEstatica.getPersonaje().getAnimD().stop();
        ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
        if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo1") {
            introduccion1 = true;
            texto = "";
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo2") {
            introduccion2 = true;
            texto = "";
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo3") {
            introduccion3 = true;
            texto = "";
        }
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo1") {
            ClaseEstatica.setUltimoEstado("EstadoCamerinoPas1");
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo2") {
            ClaseEstatica.setUltimoEstado("EstadoCamerinoPas2");
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo3") {
            ClaseEstatica.setUltimoEstado("EstadoCamerinoPas3");
        }
    }

}
