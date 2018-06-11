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
public class EstadoCamerino extends BasicGameState {

    private float x, y;
    private float ang;
    private Image fondo,narrador;
    private boolean derecha, mover, baile, introduccion, dialpersonaje, dialnarr;

    private int dato, contadorIntro;

    private String texto;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/camerino.png"); //Imagen de fondo
        narrador = new Image("Design/DialPrueba.png");
        ClaseEstatica.setSonidoPaso(new Sound("Musica/Sonidos/fx_paso.ogg"));
        //ClaseEstatica.setFail(new Sound("Musica/Sonidos/fx_fail.ogg"));
        derecha = true;
        mover = false;
        baile = false;
        contadorIntro = 0;
        ang = 200f;
        introduccion = true;
        texto = "";
        contadorIntro = 0;
        dialpersonaje = false;
        dialnarr = false;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        if (introduccion) {
            if (dialpersonaje) {
                ClaseEstatica.getPersonaje().getDial().draw();
                g.drawString(texto, 330, 560);
            } else {
                narrador.draw(300, 500);
                g.drawString(texto, 330, 560);
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
        g.drawString("Coordenadas :" + x + ", " + y, 30, 30);
        //g.drawString("UNTIL THE LAST NOTE", 30, 30);
        if (!ClaseEstatica.getPersonaje().getMusicH8().playing()) {
            ClaseEstatica.getPersonaje().getMusicH8().play();
        }
        mover = true;
        g.drawString("Contador Intro :" + contadorIntro, 30, 50);
        System.out.println("Contador Intro :" + contadorIntro);
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
        if (introduccion) {
            if (contadorIntro == 0) {
                dialpersonaje = false;
                dialnarr = true;
                texto = "¡TÚ! ¡SI TÚ! ¡Has sido elegido para\nser el héroe que salvará a la buena música\n de su fatídico futuro!";
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                dialpersonaje = true;
                dialnarr = false;
                texto = "¡YO! ¡PERO QUE DICES! ¡DONDE ESTOY!\n¡QUE HAGO AQUI! ¡QUIEN ERES TÚ!";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                dialpersonaje = false;
                dialnarr = true;
                texto = "¡Amigo, has sido elegido por la musica\npara ser el heroe de esta aventura!\nYo soy el que te acompañara a lo largo de\ntu camino.";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                dialpersonaje = false;
                dialnarr = true;
                texto = "¿Que clase de sueño es este…? Solo\nrecuerdo quedarme dormido viendo un documental\nsobre la evolucion de la musica desde la clasica\nhasta el jazz y el rock.\nVoy a ver que quiere la voz esta…";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 4) && (dato > 1000)) {
                dialpersonaje = true;
                dialnarr = false;
                texto = "Señor o señora voz, ¿que tengo que\nhacer para ayudarte?";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 5) && (dato > 1000)) {
                dialpersonaje = false;
                dialnarr = true;
                texto = "Lo primero sera vestirte adecuadamente segun tus\npoderes. Y despues tendras que salir de este\ncamerino e indagar por los pasillos en busca de tu\nprimer rival, el tirano del reggeaton, ¡Luis Fonsi!,\ny su temible y malsonante despacito.";
                contadorIntro++;
                dato = 0;

            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 6) && (dato > 1000)) {
                dialpersonaje = true;
                dialnarr = false;
                texto = "Puff… ¡Como odio esa cancion! Al menos\ntenemos un enemigo en comun. Ayudare a esta voz\ny haré lo que me diga hasta vencer al odioso\nreggeaton y asi podre volver a mi vida.";
                contadorIntro++;
                dato = 0;

            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 7) && (dato > 1000)) {
                dialpersonaje = true;
                dialnarr = false;
                texto = "Esta bien voz, te ayudare, pero…\n¿cómo combato contra ellos?";
                contadorIntro++;
                dato = 0;

            }else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 8) && (dato > 1000)) {
                dialpersonaje = false;
                dialnarr = true;
                texto = "¡Me alegra oir eso! Tienes 3 poderes, segun tu\nestilo de musica seran de una manera u otra.\nTú solo tienes que dejarte llevar por el ritmo de la\nbuena musica y los jefes seran pan comido\npara ti.";
                contadorIntro++;
                dato = 0;

            }
            else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 9) && (dato > 1000)) {
                dialpersonaje = true;
                dialnarr = false;
                texto = "¡Vamos a ello!";
                contadorIntro++;
                dato = 0;

            }else if (contadorIntro == 10 && (dato > 2000)) {
                dialpersonaje = false;
                dialnarr = false;
                introduccion = false;
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
                    game.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
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
        this.x = 354; //Coordenadas donde empieza el personaje
        this.y = 270;
        mover = false;
        baile = false;
        ClaseEstatica.getPersonaje().getAnimD().stop();
        ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
        ClaseEstatica.setUltimoEstado("EstadoCamerino");
    }

}
