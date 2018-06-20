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
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
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

    private float personajex, personajey, enemigox, enemigoy;
    private Sprite puntero;
    private String message, texto;
    private float ang;
    private Image fondo, pociVida, narrador;
    private Music music;
    private boolean derecha, mover, baile;
    private Rectangle perR, perE, rectPoci;
    private boolean colision, dialpersonaje, life, introduccion, dialnarrador;
    private int dato, contadorIntro;
    private static UnicodeFont font;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.personajex = 343; //Coordenadas donde empieza el personaje
        this.personajey = 349;
        this.enemigox = 681;
        this.enemigoy = 349;
        mover = false;
        baile = false;
        fondo = new Image("Design/scenario1.png"); //Imagen de fondo
        pociVida = new Image("Design/Potion_V2.png");
        derecha = true;
        ang = 200f;
        puntero = new Sprite("Design/cursor1.png");
        narrador = new Image("Design/dialogoNarrador1.png");
        message = "";
        dialpersonaje = false;
        dialnarrador = false;
        texto = "";
        life = false;
        introduccion = true;

        java.awt.Font fuenteAWT = new java.awt.Font("Rockwell Condensed", 0, 24);
        font = new UnicodeFont(fuenteAWT);
        font.addAsciiGlyphs();
        ColorEffect colorFuente = new ColorEffect(java.awt.Color.WHITE);
        font.getEffects().add(colorFuente);
        font.loadGlyphs();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if (!ClaseEstatica.getPersonaje().getMusicH8().playing()) {
            ClaseEstatica.getPersonaje().getMusicH8().play();
        }
        fondo.draw();
        ClaseEstatica.getEnemigo().getAnimI().draw(enemigox, enemigoy);
        if (!life) {
            pociVida.draw(912, 530);
        }
        if (introduccion) {
            if (dialpersonaje) {
                ClaseEstatica.getPersonaje().getDial().draw();
                font.drawString(270, 570, texto);
            } else {
                narrador.draw();
                font.drawString(270, 570, texto);
            }
        }
        if (mover) {
            if (derecha) {
                ClaseEstatica.getPersonaje().getAnimD().draw(personajex, personajey);

            } else if (baile) {
                ClaseEstatica.getPersonaje().getBaile().draw(personajex, personajey);
            } else {
                ClaseEstatica.getPersonaje().getAnimI().draw(personajex, personajey);
            }
        } else {
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
        }
        mover = true;
        //ClaseEstatica.getEnemigo().getAnimI().draw(enemigox, enemigoy);
        if (colision) {
            if (dialnarrador) {
                narrador.draw();
                font.drawString(270, 570, texto);
            } else {
                if (dialpersonaje) {
                    ClaseEstatica.getPersonaje().getDial().draw();
                    font.drawString(270, 570, texto);
                } else {
                    ClaseEstatica.getEnemigo().getDial().draw();
                    font.drawString(270, 570, texto);
                }
            }           
        }
        g.drawString(message, 10, 10);
        //g.drawString("Coordenadas :" + personajex + ", " + personajey, 30, 30);
        g.drawString("UNTIL THE LAST NOTE", 30, 30);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        ang += delta * 0.4f;
        dato += delta;
        if (container.getInput().isKeyDown(Input.KEY_M)) {
            music.play();

        }
        if (container.getInput().isKeyDown(Input.KEY_N)) {
            music.pause();
        }
        if (container.getInput().isKeyDown(Input.KEY_B)) {
            derecha = false;
            baile = true;
            ClaseEstatica.getPersonaje().getAnimI().stop();
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getBaile().start();
        }
        if (perR.intersects(perE)) {
            colision = true;
            if (derecha) {
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
            } else {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimI().setCurrentFrame(0);
            }
        }
        if (!perR.intersects(perE)) {
            colision = false;
        }
        if (perR.intersects(rectPoci)) {
            if (!life) {
                life = true;
                ClaseEstatica.getPersonaje().addPociVida();
            }
        }
        if (introduccion) {
            if (contadorIntro == 0) {
                dialpersonaje = false;

                texto = "¡Heroe! Ha llegado el momento de tu primera\ngran batalla. Tendras que derrotar al malvado Luis Fonsi,\nel cual esta controlando la mente del publico del escenario\ncon su asquerosa musica reggeatonera.";
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "Pero… ¿tu crees que lo voy a poder\nconseguir? ¿Que pasa si muero?";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                dialpersonaje = false;

                texto = "Amigo… ¡ERES EL HEROE DE ESTA AVENTURA!\nSal ahí y demuestra todo lo que la buena musica\nte ha enseñado";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "(Si casi no me has dicho nada...)";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 4) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "..lo intentaré.";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 5) && (dato > 1000)) {
                dialpersonaje = false;

                texto = "Antes de nada, ¿ves esa poción de ahí?";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 6) && (dato > 1000)) {
                dialpersonaje = false;

                texto = "Es una Poción de Vida, podrás usarla en el combate pulsando V.\nAunque al usarla gastarás tu turno";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 7) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "(Quizás me venga bien cogerla)";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 8) && (dato > 1000)) {
                dialpersonaje = false;

                texto = "Además de Pociones de Vida también te podrás encontrar pociones de Fuerza, \nque te otorgarán más fuerza durante el combate\n\nPara usar una Poción de Fuerza, pulsa la tecla F";
                contadorIntro++;
                dato = 0;
            } else if (contadorIntro == 9 && (dato > 1000)) {
                dialpersonaje = false;
                contadorIntro = 0;
                introduccion = false;
            }
        } else {
            if (!colision) {
                if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
                    ClaseEstatica.getPersonaje().getAnimD().stop();
                    ClaseEstatica.getPersonaje().getAnimI().start();
                    if (personajex > 0) {
                        personajex -= delta * 0.4f;
                        perR.setX(personajex);
                        derecha = false;
                        baile = false;
                        if (!ClaseEstatica.getSonidoPaso().playing()) {
                            ClaseEstatica.getSonidoPaso().play();
                        }
                    }
                } else if (container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyDown(Input.KEY_D)) {
                    ClaseEstatica.getPersonaje().getAnimI().stop();
                    ClaseEstatica.getPersonaje().getAnimD().start();
                    if ((personajex < 1016)) {
                        personajex += delta * 0.4f;
                        perR.setX(personajex);
                        derecha = true;
                        baile = false;
                        if (!ClaseEstatica.getSonidoPaso().playing()) {
                            ClaseEstatica.getSonidoPaso().play();
                        }
                    }
                } else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
                    ClaseEstatica.getPersonaje().getAnimI().stop();
                    ClaseEstatica.getPersonaje().getAnimD().start();
                    if (personajey > 293) {
                        personajey -= delta * 0.4f;
                        perR.setY(personajey + 100);
                        derecha = true;
                        baile = false;
                        if (!ClaseEstatica.getSonidoPaso().playing()) {
                            ClaseEstatica.getSonidoPaso().play();
                        }
                    }
                } else if (container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_S)) {
                    ClaseEstatica.getPersonaje().getAnimI().stop();
                    ClaseEstatica.getPersonaje().getAnimD().start();
                    if (personajey < 556) {
                        personajey += delta * 0.4f;
                        perR.setY(personajey + 100);
                        derecha = true;
                        baile = false;
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
                //dato = 0;           
                if (contadorIntro == 0) {
                    dialpersonaje = false;
                    dialnarrador = false;
                    texto = "¿Quién eres y qué haces aqué?\n¿Cómo has conseguido burlar la seguridad del backstage?\nSi quieres un autografo tendrás que esperar afuera, con el resto de fans";
                    contadorIntro++;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                    dialpersonaje = true;
                    dialnarrador = false;
                    texto = "(¿Este es Luis Fonsi? ¿Este es el culpable de que el pasillo esté así?)";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                    dialnarrador = true;
                    dialpersonaje = false;
                    texto = "\nVamos hombre…deja de temblar un poco\n¡QUE ERES EL HEROE DE ESTA AVENTURA!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                    dialpersonaje = true;
                    dialnarrador = false;
                    texto = "(Es verdad...Si, tengo que ser fuerte, y enfretarme a él)";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 4) && (dato > 1000)) {
                    dialpersonaje = true;
                    dialnarrador = false;
                    texto = "Preparate para conocer…\n¡PARA CONOCER EL PODER DE LA BUENA MUSICA!";
                    contadorIntro++;
                    dato = 0;    
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 5) && (dato > 1000)) {
                    dialpersonaje = false;
                    dialnarrador = false;
                    texto = "Muy bien, ¡PREPARATE PARA MORIR!\n¡NO TENDRE NADA DE PIEDAD CONTIGO MOSQUITO INSIGNIFICANTE!";
                    contadorIntro++;
                    dato = 0;
                } else if (contadorIntro == 6 && (dato > 1000)) {     
                    game.enterState(9, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                }


                
            }
        }

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        this.personajex = 343; //Coordenadas donde empieza el personaje
        this.personajey = 349;
        perR = new Rectangle(personajex, personajey + 120, ClaseEstatica.getPersonaje().getAnimD().getWidth(), 50);
        perE = new Rectangle(enemigox - 20, enemigoy + 120, ClaseEstatica.getEnemigo().getAnimD().getWidth(), 50);
        rectPoci = new Rectangle(912, 530, pociVida.getWidth(), pociVida.getHeight());
        perR.setY(personajey + 100);
        perR.setX(personajex);
        colision = false;
        mover = false;
        baile = false;
        contadorIntro = 0;
    }

    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (clickCount == 1) {
            message = "Single Click: " + button + " " + x + "," + y;
        }
        if (clickCount == 2) {
            message = "Double Click: " + button + " " + x + "," + y;
        }
    }
}
