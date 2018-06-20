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

                texto = "¡Heroe! Ha llegado el momento de tu tercera y ultima batalla.\nTendrás que derrotar al maravilloso presidente de Korea, Kim Jong Dos,\nel cual esta enseñando a la gente la cultura del K-POP\nAdemás tiene armamento Nuclear, que siempre funciona";
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "Armamento nuclear…\n¡¡MIC ESTO SE PUEDE AVISAR ANTES DE EMPEZAR LA AVENTURA!!\n¡¡¿TU CREES QUE SI EL ANTERIOR CASI ME DESTROZA,\nYO VOY A PODER ACABAR CON SU EJERCITO Y TODO SU ARMAMENTO NUCLEAR?!";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                dialpersonaje = false;

                texto = "¡Claro que no puedes! JAJAJAJAJAJAJA";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "¿Mic?";
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
                    dialpersonaje = false;
                    texto = "Majimag Jukkaji.";
                    contadorIntro++;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "¿Perdón?";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "Ejem, disculpa";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "Aquel que se hace llamar heroe de la musica,\nte estaba esperando.";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 4) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "¡YO FUI EL QUE TE TRAJO AQUI PARA DEMOSTRAR\nAl MUNDO MI INMENSO PODER! ¡SOY EL DUEÑO Y SEÑOR SUPREMO DE TODO!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 5) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "El que me trajo aquí...";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 6) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "¡ESPERA ERES-!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 7) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "¡SI! ¡EFECTIVAMENTE! YO ERA MIC";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 8) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "Pero...¿qué sentido tiene que me traigas aquí?";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 9) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "¿No está claro? PARA TENERTE COMO EXCLAVO, AL IGUAL QUE LUIS Y DONALD";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 10) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "¿Exclavo? ¿Quieres decir que ellos...?";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 11) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "Ellos vinieron a mi. Luis con su Reggaeton, Donald con su Trap. Estilos musicales sencillos";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 12) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "Yo les di un sentido. Vender y reproducir está mala música da mucho dinero, ¿sabias?";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 13) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "¡Mi proposito es tener poder! Y el dinero es poder.";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 14) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "Así que, decidí tener a alguien que pudiera generar más ingresos.";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 15) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "Necesitaba un nuevo género músical. Algo que me ayudará a generar ingresos...";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 16) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "Algo no...alguien...";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 17) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "Y es ahí donde entras tú";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 18) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "Maldito...";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 19) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "Personalmente me hubiera gustado traer la música de mi nación pero...";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 20) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "DEFINITIVAMENTE ERES MEJOR QUE ESO ¡ACABASTES CON ELLOS COMO SI NADA!\n¡ERES EL MEJOR GENERO QUE HE CONOCIDO!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 21) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "Si crees que me voy a doblegar...Estás equivocado...";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 22) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "¡¡¡LUCHARÉ!!!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 23) && (dato > 1000)) {
                    dialpersonaje = true;
                    texto = "¡¡¡LUCHARÉ HASTA LA ÚLTIMA NOTA!!!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 24) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "¡¡¡ADELANTE, HEROE!!!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 25) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "¡¡¡DEMUESTRAME LO BUENA QUE ES TU MUSICA!!!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 26) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "¡JAJAJAJAJAJAJAJAJA!";
                    contadorIntro++;
                    dato = 0;
                } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 27) && (dato > 1000)) {
                    dialpersonaje = false;
                    texto = "JAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJA\nJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJA\nJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJA\nJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJAJA\n";
                    contadorIntro++;
                    dato = 0;
                } else if (contadorIntro == 28 && (dato > 2000)) {
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
