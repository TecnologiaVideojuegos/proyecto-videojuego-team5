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
    private Image fondo, dialDonald;
    private boolean derecha, mover, baile;
    private Personaje DonaldTrap;
    private Animation DonaldD, DonaldI, DonaldC;
    private SpriteSheet spriteDolandD, spriteDonaldI, spriteDonaldC;

    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.x = 571; //Coordenadas donde empieza el personaje
        this.y = 257;
        fondo = new Image("Design/hallway1.png"); //Imagen de fondo
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
        dialDonald = new Image("Design/DialPrueba.png");

        //Creación ENEMIGO
        Ataque Peluquin = new Ataque(45, 20, "Peluquin", "Lanzará su peluquin para causar un daño leve", 20, new Sound(("Musica/Sonidos/fx_trap1.ogg")));
        Ataque Trap = new Ataque(65, 10, "Bad Bunny", "Cantará una canción de su amigo Bad Bunny para causar un daño brutal a su enemigo", 30, new Sound(("Musica/Sonidos/fx_trap2.ogg")));
        Ataque Muro = new Ataque(100, 5, "Muro", "Lanzará un muro pagado por todos causando un daño LETAL!!!", 50, new Sound(("Musica/Sonidos/fx_trap3.ogg")));
        DonaldTrap = new Personaje(500, "Donald Trap", new SpriteSheet("Design/DonaldTrapSprite1.png", 70, 176), DonaldD, DonaldI, null, null, null, null, null, DonaldC, null, dialDonald, 0, 0, new Sound("Musica/Sonidos/fx_fail.ogg"));
        DonaldTrap.getAtaques().add(Peluquin);
        DonaldTrap.getAtaques().add(Trap);
        DonaldTrap.getAtaques().add(Muro);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
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
                if ((y <= 257) && (x >= 70) && (x <= 180)) {
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

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        ClaseEstatica.setEnemigo(DonaldTrap);
        mover = false;
        baile = false;
        if (ClaseEstatica.getUltimoEstado() == "EstadoCamerino2") {
            this.x = 128;
            this.y = 257;
        } else {
            this.x = 30;
            this.y = 257;
        }
        ClaseEstatica.setUltimoEstado("EstadoPasillo2");
    }

}
