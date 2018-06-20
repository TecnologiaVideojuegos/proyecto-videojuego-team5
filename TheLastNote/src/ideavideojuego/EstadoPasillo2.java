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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoPasillo2 extends BasicGameState {

    private float x, y;
    private float ang;
    private Image fondo, dialDonald, narrador,fonsi;
    private boolean derecha, mover, baile, introduccion, dialpersonaje, dialfonsi;
    private Personaje DonaldTrap;
    private Animation DonaldD, DonaldI, DonaldC;
    private SpriteSheet spriteDolandD, spriteDonaldI, spriteDonaldC;
    private static UnicodeFont font;
    private int contadorIntro, dato;
    private String texto;

    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.x = 571; //Coordenadas donde empieza el personaje
        this.y = 257;
        fondo = new Image("Design/hallway2.png"); //Imagen de fondo
        narrador = new Image("Design/dialogoNarrador1.png");
        derecha = true;
        mover = false;
        baile = false;
        ang = 200f;

        spriteDonaldC = new SpriteSheet("Design/battleDonaldTrapSprite.png", 301, 322);
        spriteDolandD = new SpriteSheet("Design/DonaldTrapSprite1.png", 67, 176);
        spriteDonaldI = new SpriteSheet("Design/DonaldTrapSprite1.png", 112, 180);
        DonaldC = new Animation(spriteDonaldC, 150);
        DonaldD = new Animation(spriteDolandD, 150);
        DonaldI = new Animation(spriteDonaldI, 150);
        dialDonald = new Image("Design/dialogoDonaldTrap1.png");

        //Creación ENEMIGO
        Ataque Peluquin = new Ataque(45, 20, "Peluquin", "Lanzará su peluquin para causar un daño leve", 20, new Sound(("Musica/Sonidos/fx_trap1.ogg")));
        Ataque Trap = new Ataque(65, 10, "Bad Bunny", "Cantará una canción de su amigo Bad Bunny para causar un daño brutal a su enemigo", 30, new Sound(("Musica/Sonidos/fx_trap2.ogg")));
        Ataque Muro = new Ataque(100, 5, "Muro", "Lanzará un muro pagado por todos causando un daño LETAL!!!", 50, new Sound(("Musica/Sonidos/fx_trap3.ogg")));
        DonaldTrap = new Personaje(450, "Donald Trap", new SpriteSheet("Design/DonaldTrapSprite1.png", 70, 176), DonaldD, DonaldI, null, null, null, null, null, DonaldC, null, dialDonald, 0, 0, new Sound("Musica/Sonidos/fx_fail.ogg"), 0, 0);
        DonaldTrap.getAtaques().add(Peluquin);
        DonaldTrap.getAtaques().add(Trap);
        DonaldTrap.getAtaques().add(Muro);

        introduccion = true;
        texto = "";
        dialpersonaje = false;
        contadorIntro = 0;
        dialfonsi = true;
        fonsi = new Image("Design/dialogoLuisFonsi1.png");
        //potion = new Image("Design/potions/pt1.png");

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
            if (dialfonsi) {
                fonsi.draw();
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
            ClaseEstatica.getPersonaje().getMusicH8().play();
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
        if (introduccion) {
            if (contadorIntro == 0) {
                dialpersonaje = false;
                dialfonsi = true;
                texto = "¡ME LAS PAGARAS MALDITO HEROE!\n¡EL REAGEATON VIVIRA POR SIEMPRE PARA\nCONTROLAR LAS MENTES!";
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                dialpersonaje = true;
                dialfonsi = false;
                texto = "(Ya no sabe ni como decir Reggaeton..Ni siquiera yo se como se pronuncia esa cosa)";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                dialpersonaje = true;
                dialfonsi = false;
                texto = "(Guau, estos poderes...¡son increibles! No tengo ni idea de como he podido vencerle)";
                contadorIntro++;
                dato = 0;    
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                dialpersonaje = false;
                texto = "¡BIEN LUCHADO HEROE!\n¡La musica no se equivoco al elegirte como nuestro salvador!\n- ¿Preparado para la siguiente prueba?";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 4) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "¡Por supuesto!\n¡Puedo con cualquier cosa con estos poderes!";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 5) && (dato > 1000)) {
                dialpersonaje = false;

                texto = "JAJAJAJAJA, Tampoco te confies,\ntu siguiente rival es mucho mas poderoso de lo que crees…\n¡Posee una gran fortuna y su lacallo es uno de los músicos\nmás malvados que existen.";
                contadorIntro++;
                dato = 0;
            }else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 6) && (dato > 1000)) {
                dialpersonaje = true;

                texto = "¡Vamos a por él!";
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
                    ClaseEstatica.getPersonaje().getAnimD().stop();
                    ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
                    game.enterState(6, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

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
                    if ((y <= 257) && (x >= 500) && (x <= 600)) {
                        game.enterState(13, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
        ClaseEstatica.setEnemigo(DonaldTrap);
        mover = false;
        baile = false;
        if (ClaseEstatica.getUltimoEstado() == "EstadoCamerinoPas2") {
            this.x = 565;
            this.y = 257;
        } else {
            this.x = 30;
            this.y = 257;
        }
        ClaseEstatica.setUltimoEstado("EstadoPasillo2");
    }

}
