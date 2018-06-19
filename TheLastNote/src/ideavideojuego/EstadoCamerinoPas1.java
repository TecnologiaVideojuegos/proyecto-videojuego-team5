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
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
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
    private Image fondo1, fondo2, fondo3, fondo4, pociVida, pociFuerza;
    private Sound fail;
    private boolean derecha, mover, baile, introduccion1, introduccion2, introduccion3, vez1, vez2, vez3, life, force;
    private int dato, pasillo, contadorIntro;
    private String texto;
    private Rectangle rectPer, rectPoci, rectPociF;
    private static UnicodeFont font;

    @Override
    public int getID() {
        return 13;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo1 = new Image("Design/camerino2.png"); //Imagen de fondo
        fondo2 = new Image("Design/camerino3.png");
        pociVida = new Image("Design/Potion_V2.png");
        pociFuerza = new Image("Design/Potion_V1.png");
        derecha = true;
        mover = false;
        baile = false;
        ang = 200f;
        texto = "";
        introduccion1 = false;
        introduccion2 = false;
        introduccion3 = false;
        vez1 = true;
        vez2 = true;
        vez3 = true;
        life = false;
        force = false;

        java.awt.Font fuenteAWT = new java.awt.Font("Rockwell Condensed", 0, 24);
        font = new UnicodeFont(fuenteAWT);
        font.addAsciiGlyphs();
        ColorEffect colorFuente = new ColorEffect(java.awt.Color.WHITE);
        font.getEffects().add(colorFuente);
        font.loadGlyphs();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        //fondo1.draw();
        if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo1") {
            fondo1.draw();
            if (vez1) {
                ClaseEstatica.getPersonaje().getDial().draw();
                font.drawString(270, 570, texto);
            }
            if (!life) {
                pociVida.draw(776, 480);
            }
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo2") {
            fondo2.draw();
            if (vez2) {
                ClaseEstatica.getPersonaje().getDial().draw();
                font.drawString(270, 570, texto);
            }
            if (!force) {
                pociFuerza.draw(226, 480);
            }
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
        //g.drawString("Coordenadas :" + x + ", " + y, 30, 30);
        g.drawString("UNTIL THE LAST NOTE", 30, 30);
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

        if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo1") {
            if (rectPer.intersects(rectPoci)) {
                if (!life) {
                    life = true;
                    ClaseEstatica.getPersonaje().addPociVida();
                }
            }
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo2") {
            if (rectPer.intersects(rectPociF)) {
                if (!force) {
                    force = true;
                    ClaseEstatica.getPersonaje().addPociFuerza();
                }
            }
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo3") {
            introduccion3 = true;
            texto = "";
        }

        if (introduccion1 && vez1) {
            if (contadorIntro == 0) {
                texto = "Buajjj. ¡Qué mal huele aquí!. Hace\n mucho tiempo que no entra nadie aqui";
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                texto = "¿De quién sería este camerino?.\n OHHH. Ahí hay algo";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                texto = "Seguro que me dará más fuerzas para\nderrotar a mis adversarios.";
                contadorIntro++;
                dato = 0;
            }  else if (contadorIntro == 3 && (dato > 2000)) {
                introduccion1 = false;
                vez1 = false;
            }

        } else if (introduccion2 && vez2) {
            if (contadorIntro == 0) {
                texto = "Dios mio!! Casi muero ahí dento. Ese Luis Fonsi y su reggeaton...";
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                texto = "¡¡¡No destruirán la MÚSICA!!!";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                texto = "Ha sido una batalla muy dura y necesito fuerzas";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                texto = "Esperaaa. ¿¿Qué es eso que hay ahí??\nSeguro que me dará más fuerzas...";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 4) && (dato > 1000)) {
                texto = "...¡¡¡Contra aquellos que quieren retar mi RITMO!!!";
                contadorIntro++;
                dato = 0;
            } /*else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 5) && (dato > 1000)) {
                texto = "En unos días empezamos la gira.";
                contadorIntro++;
                dato = 0;
            }*/ else if (contadorIntro == 5 && (dato > 2000)) {
                introduccion2 = false;
                vez2 = false;
            }
        } else {
            if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo1") {
                if (x == 776 && y == 480) {
                    life = true;
                }
            }
            if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimI().start();
                if (x > 217) {
                    x -= delta * 0.4f;
                    derecha = false;
                    baile = false;
                    rectPer.setX(x);
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
                    rectPer.setX(x);
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
                    rectPer.setY(y + 100);
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
                    rectPer.setY(y + 100);
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
                    } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo3") {
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
        rectPer = new Rectangle(x, y + 100, ClaseEstatica.getPersonaje().getAnimD().getWidth(), 50);
        //rectPoci = new Rectangle(776, 480, pociVida.getWidth(), pociVida.getHeight());
        rectPer.setY(y + 100);
        rectPer.setX(x);
        if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo1") {
            introduccion1 = true;
            rectPoci = new Rectangle(776, 480, pociVida.getWidth(), pociVida.getHeight());
            texto = "";
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo2") {
            introduccion2 = true;
            rectPociF = new Rectangle(226, 480, pociFuerza.getWidth(), pociFuerza.getHeight());
            texto = "";
        } else if (ClaseEstatica.getUltimoEstado() == "EstadoPasillo3") {
            introduccion3 = true;
            texto = "";
        }
        contadorIntro = 0;
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
