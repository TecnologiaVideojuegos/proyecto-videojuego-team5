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
    private Image fondo;
    private boolean derecha, mover, baile;
    private Rectangle perR, perE;
    private boolean colision, dialpersonaje, dialmalo;
    private int estado,dato,contadorIntro;
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
        estado = 0;
        mover=false;
        baile=false;
        fondo = new Image("Design/scenario1.png"); //Imagen de fondo
        derecha = true;
        ang = 200f;
        puntero = new Sprite("Design/cursor1.png");
        sonido = new Sound("Musica/Sonidos/fx_audience.ogg");
        dialpersonaje=false;
        dialmalo=false;
        texto="";
        contadorIntro=0;
        
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
        if (mover) {
            if (derecha) {
                ClaseEstatica.getPersonaje().getAnimD().draw(personajex, personajey);

            }else  if(baile){
                ClaseEstatica.getPersonaje().getBaile().draw(personajex, personajey);
            }else {
                ClaseEstatica.getPersonaje().getAnimI().draw(personajex, personajey);
            }
        } else {
            ClaseEstatica.getPersonaje().getAnimD().stop();
            ClaseEstatica.getPersonaje().getAnimD().setCurrentFrame(0);
        }
        mover=true;
        

         if (colision) {
            if (dialpersonaje) {
                ClaseEstatica.getPersonaje().getDial().draw();
                font.drawString(270, 570, texto);
            }
            else{
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
        dato+=delta;
        if (container.getInput().isKeyDown(Input.KEY_M)) {
            ClaseEstatica.getMusicSilence().play();
            //music.resume();

        }
        if (container.getInput().isKeyDown(Input.KEY_N)) {
            ClaseEstatica.getMusicSilence().pause();
        }
        if (container.getInput().isKeyDown(Input.KEY_B)) {
            derecha=false;
            baile=true;
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
        if (!colision) {
            if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
                ClaseEstatica.getPersonaje().getAnimD().stop();
                ClaseEstatica.getPersonaje().getAnimI().start();
                if (personajex > 0) {
                    personajex -= delta * 0.4f;
                    perR.setX(personajex);
                    derecha = false;
                    baile=false;
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
                    baile=false;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                }
            } else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimD().start();
                if (personajey > 293) {
                    personajey -= delta * 0.4f;
                    perR.setY(personajey+100);
                    derecha = true;
                    baile=false;
                    if (!ClaseEstatica.getSonidoPaso().playing()) {
                        ClaseEstatica.getSonidoPaso().play();
                    }
                }
            } else if (container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_S)) {
                ClaseEstatica.getPersonaje().getAnimI().stop();
                ClaseEstatica.getPersonaje().getAnimD().start();
                if (personajey < 556) {
                    personajey += delta * 0.4f;
                    perR.setY(personajey+100);
                    derecha = true;
                    baile=false;
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
            if(contadorIntro==0){
                    dialpersonaje = true;
                    dialmalo = false;
                    texto = "¡TÚ! ¡SI TÚ! ¡Eres perfecto para el papel! ¿Qué papel?";
                    contadorIntro++;
                }
                else if(container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro==1) && (dato>1000)){
                    dialpersonaje = false;
                    dialmalo = true;
                    texto = "No es un simple papel que no lleva a \nninguna parte, es un papel hacia… ¡EL ÉXITO!";
                    contadorIntro++;
                    dato=0;
                }
                else if(container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro==2) && (dato>1000)){
                    dialpersonaje = true;
                    dialmalo = false;
                    texto = "Ya te veo ahí, brillando,una estrella sobre el escenario,\ngente eufórica animándote hasta conseguir ese orgasmo musical";
                    contadorIntro++;
                    dato=0;
                }
                else if(container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro==3) && (dato>1000)){
                    dialpersonaje = false;
                    dialmalo = true;
                    texto = "¿Eh? ¿Qué quién soy? No importa para nada quién,\nlo importante es que el DESTINO nos ha puesto aquí. ";
                    contadorIntro++;
                    dato=0;
                }
                else if(container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro==4) && (dato>1000)){
                    dialpersonaje = true;
                    dialmalo = false;
                    texto = "Así que venga, sin rechistar, metete en el camerino\ny ponte algo de ropa.";
                    contadorIntro++;
                    dato=0;
                }
                else if(container.getInput().isKeyDown(Input.KEY_ENTER) && (contadorIntro==5) && (dato>1000)){
                    dialpersonaje = true;
                    dialmalo = false;
                    texto = "En unos días empezamos la gira.";
                    contadorIntro++;
                    dato=0;
                }else if(contadorIntro==6 && (dato > 2000)){
                    dialpersonaje = false;
                    dialmalo = false;
                    game.enterState(11, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                }
            

            /*if (container.getInput().isKeyPressed(Input.KEY_RIGHT)) {
                if (estado == 0) {
                    estado = 1;
                }
            } else if (container.getInput().isKeyPressed(Input.KEY_LEFT)) {
                if (estado == 1) {
                    estado = 0;
                }
            } else if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
                if (estado == 0) {
                    game.enterState(9, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                } else if (estado == 1) {
                    if (derecha) {
                        this.personajex = 595; //Coordenadas donde empieza el personaje
                        this.personajey = 359;
                        perR.setX(personajex);
                        perR.setY(personajey);
                    } else {
                        this.personajex = 799;
                        this.personajey = 359;
                        perR.setX(personajex);
                        perR.setY(personajey);
                    }
                }
            }*/
        }

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        perR = new Rectangle(personajex, personajey+100, ClaseEstatica.getPersonaje().getAnimD().getWidth(), 50);
        perE = new Rectangle(enemigox, enemigoy+100, ClaseEstatica.getEnemigo().getAnimD().getWidth(), 50);
        this.personajex = 343; //Coordenadas donde empieza el personaje
        this.personajey = 349;
        perR.setY(personajey+100);
        perR.setX(personajex);
        colision = false;
        mover=false;
        baile=false;
    }

     @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        sonido.stop();
    }
}
