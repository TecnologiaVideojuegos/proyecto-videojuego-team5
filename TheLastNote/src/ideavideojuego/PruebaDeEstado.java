/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SpriteSheet;
/**
 *
 * @author Álvaro Zamorano
 */
public class PruebaDeEstado extends BasicGame{
    
    private AppGameContainer contenedor;
    private float x,y;
    private String texto;
    private int tiempo;
    private Animation anim;
    private SpriteSheet sprite;
    private float ang = 30f;
    private Image fondo;
    private int cX = 600,cY=360;
    private Music music;
    
    
    public PruebaDeEstado() throws  SlickException{
        super("Primera Pantalla") ;
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(cX, cY, false);//Indicamos cuantos pixeles tendra la ventana
        contenedor.setShowFPS(false);//No se muestran los fps
        contenedor.start();
        
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.x = 20; //Coordenadas donde empieza el personaje
        this.y = 193;
        //texto = "Hello World";
        fondo = new Image("Design/hallway1.png"); //Imagen de fondo
        sprite = new SpriteSheet("testdata/DudeWalking.png",28,49);//Sprites del personaje
        anim = new Animation(sprite,100);//Animation del personaje
        music = new Music("Musica/rock_hall.ogg", false);
        //contenedor.getGraphics().setBackground(Color.gray);
        //anim.stop();
        //anim.setAutoUpdate(true);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        /*tiempo += delta;
        if(tiempo > 50){
            tiempo = 0;
            x += 2;
            if(x > 500){
                x = 30;
            }
        }*/
        ang += delta * 0.1f;
	if (container.getInput().isKeyDown(Input.KEY_M)){
			music.play();
                        //music.resume();                       
        }
        if (container.getInput().isKeyDown(Input.KEY_N)){
			music.pause();
        }
        if (container.getInput().isKeyDown(Input.KEY_LEFT) || container.getInput().isKeyDown(Input.KEY_A)) {
            anim.start();
            if(x>0){
                x -= delta * 0.1f;
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_RIGHT) || container.getInput().isKeyDown(Input.KEY_D)) {
            anim.start();
            if(x<572){
                x += delta * 0.1f;
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_UP) || container.getInput().isKeyDown(Input.KEY_W)) {
            anim.start();
            if(y>160){
                y -= delta * 0.1f;
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_DOWN) || container.getInput().isKeyDown(Input.KEY_S) ) {
            anim.start();
            if(y<217){
                y += delta * 0.1f;
            }
	}
        else{
            anim.stop();
            anim.setCurrentFrame(6);
        }
        //anim.update(delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        /*g.setColor(Color.green);
        g.drawString(texto, x, y);*/
        //music.play();
        fondo.draw();
        anim.draw(x, y);
        //g.drawString("Coordenadas :" + x + ", " + y, 30, 30);
        g.drawString("UNTIL THE LAST NOTE", 30, 30);    
    }
    
    
    public static void main(String args[]){
        try{
            new PruebaDeEstado();
        }catch(org.newdawn.slick.SlickException e){}
    }
    
    
}