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
public class EstadoCamerino extends BasicGameState {

    private float x, y;
    private float ang;
    private Image fondo, narrador;
    private boolean derecha, mover, baile, introduccion, dialpersonaje, textoPerdido;

    private int dato, contadorIntro;

    private String texto;

    private static UnicodeFont font;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/camerino1.png"); //Imagen de fondo
        narrador = new Image("Design/dialogoNarrador1.png");
        ClaseEstatica.setSonidoPaso(new Sound("Musica/Sonidos/fx_paso.ogg"));
        derecha = true;
        mover = false;
        baile = false;
        contadorIntro = 0;
        ang = 200f;
        introduccion = true;
        texto = "";
        contadorIntro = 0;
        dialpersonaje = false;
        textoPerdido = false;
        
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
            if (dialpersonaje) {
                ClaseEstatica.getPersonaje().getDial().draw();
                font.drawString(270, 570, texto);
            } else {
                narrador.draw();
                font.drawString(270, 570, texto);
            }
        }
        if(textoPerdido){
            narrador.draw();
            font.drawString(270, 570, texto);
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
        /*if (container.getInput().isKeyDown(Input.KEY_DELETE)) 
            game.enterState(1);*/
        if (introduccion) {
            if (contadorIntro == 0) {
                dialpersonaje = false;
                
                texto = "¡TÚ! ¡SI TÚ! ¡Has sido elegido para\nser el héroe que salvará a la buena música\nde su fatídico futuro!";
                contadorIntro++;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 1) && (dato > 1000)) {
                dialpersonaje = true;
          
                texto = "¡YO! ¡PERO QUE DICES! ¡DONDE ESTOY!\n¡QUE HAGO AQUI! ¡QUIEN ERES TÚ!";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 2) && (dato > 1000)) {
                dialpersonaje = false;
              
                texto = "¡Amigo, has sido elegido por la musica\npara ser el heroe de esta aventura!\nMe llamo Mic, y soy el que te acompañará a lo largo de\ntu camino.";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 3) && (dato > 1000)) {
                dialpersonaje = true;
             
                texto = "¿Que clase de sueño es este? Solo\nrecuerdo quedarme dormido viendo un documental\nsobre la evolución de la musica desde la clasica\nhasta el jazz y el rock.";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 4) && (dato > 1000)) {
                dialpersonaje = true;
              
                texto = "(Está claro que esto es un sueño...)";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 5) && (dato > 1000)) {
                dialpersonaje = true;
              
                texto = "(No... Un sueño no...)";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 6) && (dato > 1000)) {
                dialpersonaje = true;
              
                texto = "(Una pesadilla.)";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 7) && (dato > 1000)) {
                dialpersonaje = true;
              
                texto = "Vale... Vale.";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 8) && (dato > 1000)) {
                dialpersonaje = true;
              
                texto = "Esto...señor Mic. ¿Qué puedo hacer para ayudarle?";
                contadorIntro++;
                dato = 0;
            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 9) && (dato > 1000)) {
                dialpersonaje = false;
               
                texto = "Lo primero sera vestirte adecuadamente segun tus\npoderes. Y despues tendras que salir de este\ncamerino e indagar por los pasillos en busca de tu\nprimer rival, el tirano del reggeaton, ¡Luis Fonsi!,y su temible despacito.";
                contadorIntro++;
                dato = 0;

            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 10) && (dato > 1000)) {
                dialpersonaje = true;
           
                texto = "(Puff… ¡Como odio esa canción! Al menos\ntenemos un enemigo en común. Ayudaré a esta voz\ny haré lo que me diga hasta vencer al odioso\nreggaeton y así podré volver a mi vida.)";
                contadorIntro++;
                dato = 0;

            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 11) && (dato > 1000)) {
                dialpersonaje = true;
           
                texto = "Esta bien Mic, te ayudaré, pero…\n¿cómo combato contra ellos?";
                contadorIntro++;
                dato = 0;

            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 12) && (dato > 1000)) {
                dialpersonaje = false;
  
                texto = "¡Me alegra oir eso! Tienes 3 poderes, segun tu\nestilo de musica seran de una manera u otra.\nTú solo tienes que dejarte llevar por el ritmo de la\nbuena musica y los jefes seran pan comido para ti.";
                contadorIntro++;
                dato = 0;

            } else if (container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro == 13) && (dato > 1000)) {
                dialpersonaje = true;
   
                texto = "¡Vamos a ello!";
                contadorIntro++;
                dato = 0;

            } else if (contadorIntro == 14 && (dato > 2000)) {
                dialpersonaje = false;

                contadorIntro = 0;
                introduccion = false;
                
            }

        }
        if (ClaseEstatica.getUltimoEstado() == "EstadoBatalla" && textoPerdido) {
            if (contadorIntro == 0) {
                dialpersonaje = false;
                dato = 0;
                texto = "OHHH. ¡Qué pena! Perdiste contra uno de esos\nque quieren destruir nuestro futuro. Tendrás que empezar de nuevo\n¡¡DEMUÉSTRALES QUIÉN ES EL MEJOR!!";
                contadorIntro++;
            }else if (container.getInput().isKeyDown(Input.KEY_ENTER) && contadorIntro == 1 && (dato > 2000)) {
                dialpersonaje = false;
                contadorIntro = 0;
                textoPerdido = false;                
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
        if(ClaseEstatica.getUltimoEstado()=="EstadoBatalla"){
            textoPerdido = true;
        }

    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        ClaseEstatica.setUltimoEstado("EstadoCamerino");
    }
}
