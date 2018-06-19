/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoPasillo3 extends BasicGameState {

    private float x, y;
    private float ang;
    private Image fondo, dialKim, narrador, trap;
    private int cX = 1080, cY = 607;
    private boolean derecha, mover, baile, introduccion, dialpersonaje, dialtrap;
    private Personaje KimJongDos;
    private Animation KimD, KimI, KimC;
    private SpriteSheet spriteKimD, spriteKimI, spriteKimC;
    private int contadorIntro, dato;
    private String texto;
    private static UnicodeFont font;

    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.x = 571; //Coordenadas donde empieza el personaje
        this.y = 257;
        fondo = new Image("Design/hallway3.png"); //Imagen de fondo
        narrador = new Image("Design/dialogoNarrador1.png");
        derecha = true;
        mover = false;
        baile = false;
        ang = 200f;

        spriteKimC = new SpriteSheet("Design/battleKimJongSprite.png", 301, 322);
        spriteKimD = new SpriteSheet("Design/KimJong2Sprite1.png", 112, 180);
        spriteKimI = new SpriteSheet("Design/KimJong2Sprite1.png", 112, 180);
        KimC = new Animation(spriteKimC, 150);
        KimD = new Animation(spriteKimD, 150);
        KimI = new Animation(spriteKimI, 150);
        dialKim = new Image("Design/dialogoKimJong1.png");

        Ataque Misilazo = new Ataque(40, 20, "Misilazo", "Lanzará un misil para causar un daño leve", 20, new Sound(("Musica/Sonidos/fx_kim1.ogg")));
        Ataque Kpop = new Ataque(80, 10, "Ritmo K-POP", "Moverá su cuerpo al ritmo de K-POP para causar un daño brutal a su enemigo", 40, new Sound(("Musica/Sonidos/fx_kim2.ogg")));
        Ataque Nuclear = new Ataque(120, 5, "Ataque nuclear", "Lanzará un ataque nuclear para causar un daño LETAL!!!", 60, new Sound(("Musica/Sonidos/fx_kim3.ogg")));
        KimJongDos = new Personaje(1000, "Kim Jong-Dos", new SpriteSheet("Design/KimJong2Sprite1.png", 70, 176), KimD, KimI, null, null, null, null, null, KimC, null, dialKim, 0, 0, new Sound("Musica/Sonidos/fx_fail.ogg"), 0, 0);
        KimJongDos.getAtaques().add(Misilazo);
        KimJongDos.getAtaques().add(Kpop);
        KimJongDos.getAtaques().add(Nuclear);

        introduccion = true;
        texto = "";
        dialpersonaje = false;
        contadorIntro = 0;
        dialtrap = true;
        trap = new Image("Design/dialogoDonaldTrap1.png");

        java.awt.Font fuenteAWT = new java.awt.Font("Rockwell Condensed", 0, 24);
        font = new UnicodeFont(fuenteAWT);
        font.addAsciiGlyphs();
        ColorEffect colorFuente = new ColorEffect(java.awt.Color.WHITE);
        font.getEffects().add(colorFuente);
        font.loadGlyphs();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        if (introduccion) {
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
            if (dialtrap) {
                trap.draw();
                font.drawString(270, 570, texto);
            } else {
                if (dialpersonaje) {
                    ClaseEstatica.getPersonaje().getDial().draw();
                    font.drawString(270, 570, texto);
                } else {
                    narrador.draw();
                    font.drawString(270, 570, texto);
                }
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
            ClaseEstatica.getMusicSilence().play();

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
        if (introduccion) {
            if (contadorIntro == 0) {
                dialpersonaje = false;
                dialtrap = true;
                texto = "(Derrotado) ¡NOOOOOOO! ¡COMO HE PODIDO PERDER! ¡DIOS DEL TRAP NO ME QUIERO IIIIIR!";
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                dialpersonaje = true;
                dialtrap = false;
                texto = "(Bastante destrozado y pensando) Que suerte he tenido… pensaba que no lo iba a contar. Puffff";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                dialpersonaje = false;
                texto = "(Euforico) ¡BIEN LUCHADO HEROE, HAS SUPERADO LA SEGUNDA PRUEBA! ¡Estas a punto de salvar a la musica! - ¿Preparado para la siguiente prueba?";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "(Muy Cansado) ¡COMO QUIERES QUE SIGA LUCHANDO SI NO ME TENGO NI EN PIE!";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 4) && (dato > 1000)) {
                dialpersonaje = false;

                texto = "(Riendose muy fuerte) JAJAJAJAJA, Tranquilo heroe, en el escenario puedes encontrar alguna pocion que recuperará tus fuerzas.";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 5) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "(Aliviado) Menos mal (Suspiro)";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 6) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "(Pensando) Segun lo que dijo mic al entrar en este mundo, solo me queda un jefe… A saber lo que me voy a encontrar… Este sitio cada vez esta mas destrozado";
                contadorIntro++;
                dato = 0;
            }else if (contadorIntro == 7 && (dato > 2000)) {
                dialpersonaje = false;
                contadorIntro = 0;
                introduccion = false;
            }
        } else {
            if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimI().start();
                if (x > 0) {
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
                if (x < 1018) {
                    x += delta * 0.4f;
                    derecha = true;
                    baile = false;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                } else {
                    ClaseEstatica.getEnemigo().restaurarTodo();
                    //ClaseEstatica.setEnemigo(KimJongDos);
                    ClaseEstatica.getPersonaje().getAnimD().stop();
                    ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
                    game.enterState(8, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                }
            } else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimD().start();
                if (y > 257) {
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
                if (y < 354) {
                    y += delta * 0.4f;
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
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        ClaseEstatica.setEnemigo(KimJongDos);
        mover = true;
        baile = false;
        this.x = 30; //Coordenadas donde empieza el personaje
        this.y = 257;
    }

}
