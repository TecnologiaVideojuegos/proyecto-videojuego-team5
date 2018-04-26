/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
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
public class EstadoEscenarioReag1 extends BasicGameState{
    private AppGameContainer contenedor;
    private float x,y;
    private String texto;
    private int tiempo;
    private Animation anim,alfredoD,alfredoI;
    private SpriteSheet sprite,spriteAlfredoD,spriteAlfredoI;
    private float ang;
    private Image fondo;
    private int cX = 1080,cY=607;
    private Music music;
    private boolean derecha;
    private Personaje LuisFonsi;
    private Personaje personaje;
    
    @Override
    public int getID() {
        return 4;
    }
    
    /*public PantallaInicio(Personaje personaje){
        this.personaje=personaje;
    }*/

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.x = 20; //Coordenadas donde empieza el personaje
        this.y = 257;
        //texto = "Hello World";
        fondo = new Image("Design/hallway1.png"); //Imagen de fondo
        //sprite = new SpriteSheet("testdata/DudeWalking.png",28,49);//Sprites del personaje
        //anim = new Animation(sprite,100);//Animation del personaje
        music = new Music("Musica/rock_hall.ogg", false);
        spriteAlfredoD = new SpriteSheet("Design/FreddieWalk_V4.png", 69, 164);
        spriteAlfredoI = new SpriteSheet("Design/FreddieWalk_V3.png", 67 ,164);
        alfredoD = new Animation(spriteAlfredoD,100);
        alfredoI = new Animation(spriteAlfredoI,100);
        derecha=true;
        ang = 200f;
        //contenedor.getGraphics().setBackground(Color.gray);
        //anim.stop();
        //anim.setAutoUpdate(true);
        
        //Creación ENEMIGO
        Ataque Microfonazo = new Ataque(10, 20, "Microfonazo", "Lanzará un micrófono para causar un daño leve", 10);
        Ataque Flow = new Ataque(30, 10, "Flow", "Moverá sus caderas para causar un daño brutal en la vista del enemigo", 10);
        Ataque Despacito = new Ataque(40, 5, "Despacito", "Cantará su mitica canción Despacito para causar daño letal en los oidos del enemigo", 10);
        //LuisFonsi = new Personaje(250,"Ludis Fonsi", new SpriteSheet("Design/SaxoStill.png", 170, 410));
        /*LuisFonsi.getAtaques().add(Microfonazo);
        LuisFonsi.getAtaques().add(Flow);
        LuisFonsi.getAtaques().add(Despacito);*/
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        /*g.setColor(Color.green);
        g.drawString(texto, x, y);*/
        fondo.draw();
        //anim.draw(x, y);
        //LuisFonsi.getSpritePJ().draw(40, 200);
        if(derecha){
            alfredoD.draw(x,y);
        }
        else{
            alfredoI.draw(x,y);
        }
        //g.drawString("Coordenadas :" + x + ", " + y, 30, 30);
        g.drawString("UNTIL THE LAST NOTE", 30, 30);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        ang += delta * 0.4f;
	if (container.getInput().isKeyDown(Input.KEY_M)){
			music.play();                       
                        //music.resume();
                        
                        
        }
        if (container.getInput().isKeyDown(Input.KEY_N)){
			music.pause();
        }
        if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
            alfredoD.stop();
            alfredoI.start();
            if(x>0){
                x -= delta * 0.4f;
                derecha=false;
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyDown(Input.KEY_D)) {
            alfredoI.stop();
            alfredoD.start();
            if(x<1018){
                x += delta * 0.4f;
                derecha=true;
            }else{
                //game.enterState(4);

            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
            alfredoI.stop();
            alfredoD.start();
            if(y>257){
                y -= delta * 0.4f;
                derecha=true;
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_S) ) {
            alfredoI.stop();
            alfredoD.start();
            if(y<354){
                y += delta * 0.4f;
                derecha=true;
            }
	}
        else{
            if (derecha){
                alfredoD.stop();
                alfredoD.setCurrentFrame(0);
            }
            else{
                alfredoI.stop();
                alfredoI.setCurrentFrame(0);
            }
        }            
    }
    
}
