/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoSeleccion extends BasicGameState {

    /*private static final Punto ALFREDO;
    private static final Punto MOLDOVA;
    private static final Punto LUDWIG;*/
    private Sprite ALFREDO;
    private Sprite MOLDOVA;
    private Sprite LUDWIG;
    private Image fondo;
    private int indicador;
    private Sprite puntero;
    private Personaje AlfredoMercurio,LudwigvanMozart,MoldovaSax;
    private SpriteSheet spriteAlfredoD, spriteAlfredoI,spriteLudwigD,spriteLudwigI,spriteMoldovaD,spriteMoldovaI;
    private Animation alfredoD,alfredoI,ludwigD,ludwigI,moldovaD,moldovaI;
    /*static {
        ALFREDO = new Punto(0,0);
        MOLDOVA = new Punto(300, 0);
        LUDWIG = new Punto(600, 0);
    }*/
    
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
        this.fondo = new Image("Design/escenario.jpg");
        ALFREDO = new Sprite("Design/FreddieStillBIG.png",500,380);
        MOLDOVA = new Sprite("Design/SaxGuyStillBIG.png", 500,380);
        LUDWIG = new Sprite("Design/BombinStillBIG.png", 500,380);
        //puntero = new Sprite("Design/cursor1.png", ALFREDO);
        this.indicador = 0;
        //personaje = new Personaje(200, "Alfredo Mercurio");
        
        spriteAlfredoD = new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164);
        spriteAlfredoI = new SpriteSheet("Design/FreddieWalk_V3.png", 67 ,164);
        alfredoD = new Animation(spriteAlfredoD,100);
        alfredoI = new Animation(spriteAlfredoI,100);
        Music musicAlfredoB = new Music("Musica/rock_8bit.ogg", false);
        Music musicAlfredoH = new Music("Musica/rock_hall.ogg", false);
        
        spriteLudwigD = new SpriteSheet("Design/BombinWalkSprite_V4.png", 71, 167);
        spriteLudwigI = new SpriteSheet("Design/BombinWalkSprite_V3.png", 71, 167);
        ludwigD = new Animation(spriteLudwigD,100);
        ludwigI = new Animation(spriteLudwigI,100);
        Music musicLudwigB = new Music("Musica/classic_8bit.ogg", false);
        Music musicLudwigH = new Music("Musica/classic_8hit.ogg", false);
        
        spriteMoldovaD = new SpriteSheet("Design/SaxGuyWalkSprite_V4.png", 67, 176);
        spriteMoldovaI = new SpriteSheet("Design/SaxGuyWalkSprite_V3.png", 67, 176);
        moldovaD = new Animation(spriteMoldovaD,100);
        moldovaI = new Animation(spriteMoldovaI,100);
        Music musicMoldovaB = new Music("Musica/classic_8bit.ogg", false);
        Music musicMoldovaH = new Music("Musica/classic_8hit.ogg", false);
        
        Ataque Guitarrazo = new Ataque(30, 20, "Guitarrazo", "Lanzará su guitarra para causar un daño leve", 10);
        Ataque Mama = new Ataque(65, 10, "Mama", "Inflingirá un daño brutal en los tímpanos del enemigo", 10);
        Ataque DiscoPlatino = new Ataque(80, 5, "Disco de platino", "Lanzará uno de sus discos de platino a la yugular para causar un daño LETAL!!!", 10);
        
        Ataque Saxofonazo = new Ataque(35, 20, "Saxofonazo", "Lanzará un saxofón para causar un daño leve", 10);
        Ataque BaileCadera = new Ataque(50, 10, "Baile de cadera", "Usará la mítica canción de Eurovisión para causar un daño bestial!!", 10);
        Ataque SaxGuy = new Ataque(60, 5, "TrumpetGuy", "Usará la mítica canción de Eurovisión para causar un daño LETAL!!", 10);
        
        Ataque Pianazo = new Ataque(15, 20, "Pianazo", "Lanzará un piano para causar un daño leve", 10);
        Ataque MetricaExacta = new Ataque(20, 10, "Metrica Exacta", "Regañará al enemigo por no llevar el ritmo acorde e inflingirá daño por humillación", 10);
        Ataque PelucoVictoriano = new Ataque(35, 5, "Peluco Victoriano", "Lanzará su tremenda peluca para destrozar los sueños capilares del enemigo, causando un daño LETAL!!!", 10);
        
        LudwigvanMozart = new Personaje(400,"Ludwin van Mozart",new SpriteSheet("Design/BombinWalkSprite_V4.png", 71, 167),ludwigD,ludwigI, musicLudwigB, musicLudwigH);
        LudwigvanMozart.getAtaques().add(Pianazo);
        LudwigvanMozart.getAtaques().add(MetricaExacta);
        LudwigvanMozart.getAtaques().add(PelucoVictoriano);
       
        AlfredoMercurio = new Personaje(200, "Alfredo Mercurio", new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164), alfredoD, alfredoI, musicAlfredoB, musicAlfredoH);
        AlfredoMercurio.getAtaques().add(Guitarrazo);
        AlfredoMercurio.getAtaques().add(Mama);
        AlfredoMercurio.getAtaques().add(DiscoPlatino);
        
        MoldovaSax = new Personaje(300, "Moldova Sax", new SpriteSheet("Design/SaxGuyWalkSprite_V4.png", 70, 176), moldovaD, moldovaI, musicMoldovaB, musicMoldovaH);
        MoldovaSax.getAtaques().add(Saxofonazo);
        MoldovaSax.getAtaques().add(BaileCadera);
        MoldovaSax.getAtaques().add(SaxGuy);
    }
        

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        if(indicador==0){
            ALFREDO.draw();
        }
        else if(indicador==1){
            MOLDOVA.draw();
        }
        else if(indicador==2){
            LUDWIG.draw();
        }
        //puntero.draw();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        if(entrada.isKeyPressed(Input.KEY_RIGHT)){
            if (indicador==0){
                indicador=1;
                //puntero.setPosicion(MOLDOVA);
            }
            else if(indicador==1){
                indicador=2;
                //puntero.setPosicion(LUDWIG);
            }
        }else if(entrada.isKeyPressed(Input.KEY_LEFT)){
            if(indicador==1){
                indicador=0;
                //puntero.setPosicion(ALFREDO);
            }
            else if(indicador==2){
                indicador=1;
                //puntero.setPosicion(MOLDOVA);
            }
        }else if(entrada.isKeyPressed(Input.KEY_ENTER)){
            if(indicador==0){
                ClaseEstatica.setPersonaje(AlfredoMercurio);
                game.enterState(2);
            }
            else if(indicador ==1){
                ClaseEstatica.setPersonaje(MoldovaSax);
                game.enterState(2);
            }
            else if (indicador ==2){
                ClaseEstatica.setPersonaje(LudwigvanMozart);
                game.enterState(2);
            }
        } 
    }
}
