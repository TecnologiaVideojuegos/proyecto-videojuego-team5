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
public class EstadoEscenarioKPOP3 extends BasicGameState {

    private float personajex, personajey, enemigox, enemigoy;
    private Sprite puntero;
    private float ang;
    private Image fondo, pociVida, pociForce, narrador;
    private boolean derecha, mover, baile;
    private Rectangle perR, perE, rectPoci, rectForce;
    private boolean colision, dialpersonaje, life, force, introduccion;
    private int dato, contadorIntro;
    private Sound sonido;
    private String texto;
    private static UnicodeFont font;

    @Override
    public int getID() {
        return 8;
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
        derecha = true;
        ang = 200f;
        puntero = new Sprite("Design/cursor1.png");
        sonido = new Sound("Musica/Sonidos/fx_audience.ogg");
        pociVida = new Image("Design/Potion_V2.png");
        pociForce = new Image("Design/Potion_V1.png");
        narrador = new Image("Design/dialogoNarrador1.png");
        dialpersonaje = false;
        texto = "";
        contadorIntro = 0;
        life = false;
        force = false;
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
        if (!sonido.playing()) {
            sonido.play();
        }
        fondo.draw();
        ClaseEstatica.getEnemigo().getAnimI().draw(enemigox, enemigoy);
        if (!life) {
            pociVida.draw(400, 600);
        }
        if (!force) {
            pociForce.draw(175, 500);
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

        if (colision) {
            if (dialpersonaje) {
                ClaseEstatica.getPersonaje().getDial().draw();
                font.drawString(270, 570, texto);
            } else {
                ClaseEstatica.getEnemigo().getDial().draw();
                font.drawString(270, 570, texto);
            }
        }

        //g.drawString("Coordenadas :" + personajex + ", " + personajey, 30, 30);
        g.drawString("UNTIL THE LAST NOTE", 30, 30);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        ang += delta * 0.4f;
        dato += delta;
        if (container.getInput().isKeyDown(Input.KEY_M)) {
            ClaseEstatica.getMusicSilence().play();
            //music.resume();

        }
        if (container.getInput().isKeyDown(Input.KEY_N)) {
            ClaseEstatica.getMusicSilence().pause();
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
        if (perR.intersects(rectForce)) {
            if (!force) {
                force = true;
                ClaseEstatica.getPersonaje().addPociFuerza();
            }
        }
        if (introduccion) {
            if (contadorIntro == 0) {
                dialpersonaje = false;

                texto = "¡Heroe! Ha llegado el momento de tu primera\ngran batalla. Tendras que derrotar al malvado Luis Fonsi,\nel cual esta controlando la mente del publico del escenario\ncon su asquerosa musica reggeatonera.";
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "(Asustado) Pero… ¿tu crees que lo voy a poder\nconseguir? ¿Que pasa si muero?";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                dialpersonaje = false;

                texto = "Amigo… ¡ERES EL HEROE DE ESTA AVENTURA!\nSal ahi y demuestra todo lo que la buena musica\nte ha enseñado";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "(Cada vez mas asustado) A ver que consigo…";
                contadorIntro++;
                dato = 0;
            } else if (contadorIntro == 4 && (dato > 2000)) {
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
                    if (personajex < 1016) {
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
                if (contadorIntro == 0) {
                    dialpersonaje = true;
                    texto = "¡TÚ! ¡SI TÚ! ¡Eres perfecto para el papel! ¿Qué papel?";
                    contadorIntro++;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "No es un simple papel que no lleva a \nninguna parte, es un papel hacia… ¡EL ÉXITO!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "Ya te veo ahí, brillando,una estrella sobre el escenario,\ngente eufórica animándote hasta conseguir ese orgasmo musical";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "¿Eh? ¿Qué quién soy? No importa para nada quién,\nlo importante es que el DESTINO nos ha puesto aquí. ";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 4) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "Así que venga, sin rechistar, metete en el camerino\ny ponte algo de ropa.";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 5) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "En unos días empezamos la gira.";
                    contadorIntro++;
                    dato = 0;
                } else if (contadorIntro == 6 && (dato > 2000)) {
                    game.enterState(11, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                }

            }
        }

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        perR = new Rectangle(personajex, personajey + 100, ClaseEstatica.getPersonaje().getAnimD().getWidth(), 50);
        perE = new Rectangle(enemigox, enemigoy + 100, ClaseEstatica.getEnemigo().getAnimD().getWidth(), 50);
        rectPoci = new Rectangle(400, 600, pociVida.getWidth(), pociVida.getHeight());
        rectForce = new Rectangle(175, 500, pociForce.getWidth(), pociForce.getHeight());
        this.personajex = 343; //Coordenadas donde empieza el personaje
        this.personajey = 349;
        perR.setY(personajey + 100);
        perR.setX(personajex);
        colision = false;
        mover = false;
        baile = false;
        contadorIntro = 0;
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        sonido.stop();
    }
}
