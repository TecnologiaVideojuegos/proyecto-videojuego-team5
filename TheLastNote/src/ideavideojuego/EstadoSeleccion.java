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
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoSeleccion extends BasicGameState {

    private static final Punto ALFREDO;
    private static final Punto MOLDOVA;
    private static final Punto LUDWIG;
    private Sprite botonALFREDO;
    private Sprite botonMOLDOVA;
    private Sprite botonLUDWIG;
    private Image fondo;
    private int indicador;
    private Sprite puntero;
    private IdeaVideojuego p;
    private Personaje personaje;
    private Personaje AlfredoMercurio;
    private SpriteSheet prueba, spriteAlfredoD, spriteAlfredoI;
    private Animation anim,alfredoD,alfredoI;
    
    static {
        ALFREDO = new Punto(0,0);
        MOLDOVA = new Punto(300, 0);
        LUDWIG = new Punto(600, 0);
    }
    
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.fondo = new Image("Design/seleccion.jpg");
        this.botonALFREDO = new Sprite("Design/FreddyStill.png", ALFREDO);
        this.botonMOLDOVA = new Sprite("Design/SaxoStill.png", MOLDOVA);
        this.botonLUDWIG = new Sprite("Design/BombinStill.png", LUDWIG);
        puntero = new Sprite("Design/PunteroLoL.png", ALFREDO);
        this.indicador = 0;
        //personaje = new Personaje(200, "Alfredo Mercurio");
        
        spriteAlfredoD = new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164);
        spriteAlfredoI = new SpriteSheet("Design/FreddieWalk_V3.png", 67 ,164);
        Ataque Guitarrazo = new Ataque(30, 20, "Guitarrazo", "Lanzará su guitarra para causar un daño leve", 10);
        Ataque Mama = new Ataque(65, 10, "Mama", "Inflingirá un daño brutal en los tímpanos del enemigo", 10);
        Ataque DiscoPlatino = new Ataque(80, 5, "Disco de platino", "Lanzará uno de sus discos de platino a la yugular para causar un daño LETAL!!!", 10);
        
        alfredoD = new Animation(spriteAlfredoD,100);
        alfredoI = new Animation(spriteAlfredoI,100);
        
        alfredoD.stop();
        alfredoI.stop();
        AlfredoMercurio = new Personaje(200, "Alfredo Mercurio", new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164), alfredoD, alfredoI);
        AlfredoMercurio.getAtaques().add(Guitarrazo);
        AlfredoMercurio.getAtaques().add(Mama);
        AlfredoMercurio.getAtaques().add(DiscoPlatino);
        
    }
        

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        botonALFREDO.draw();
        botonMOLDOVA.draw();
        botonLUDWIG.draw();
        puntero.draw();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        if(entrada.isKeyPressed(Input.KEY_RIGHT)){
            if (indicador==0){
                indicador=1;
                puntero.setPosicion(MOLDOVA);
            }
            else if(indicador==1){
                indicador=2;
                puntero.setPosicion(LUDWIG);
            }
        }else if(entrada.isKeyPressed(Input.KEY_LEFT)){
            if(indicador==1){
                indicador=0;
                puntero.setPosicion(ALFREDO);
            }
            else if(indicador==2){
                indicador=1;
                puntero.setPosicion(MOLDOVA);
            }
        }else if(entrada.isKeyPressed(Input.KEY_ENTER)){
            if(indicador==0 || indicador ==1 || indicador ==2){
                //game.enterState(3);
                //game.addState(new EstadoPasillo1(personaje));
                ClaseEstatica.setPersonaje(AlfredoMercurio);
                System.out.println("NOMBRE DEL PERSONAJE ELEGIDO --> "+ClaseEstatica.getPersonaje().getNombre());
                game.enterState(2);
            }
        } 
    }
}
