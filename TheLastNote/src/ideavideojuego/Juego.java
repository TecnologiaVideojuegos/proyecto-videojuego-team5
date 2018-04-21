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
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
/**
 *
 * @author Álvaro Zamorano
 */
public class Juego extends BasicGame{
    
    private AppGameContainer contenedor;
    private float x,y;
    private String texto;
    private int tiempo;
    private Animation anim;
    private SpriteSheet sprite;
    private float ang = 30f;
    private int cX = 600,cY=400;
    
    
    public Juego() throws  SlickException{
        super("Primera Pantalla") ;
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(cX, cY, false);
        contenedor.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.x = 30;
        this.y = 40;
        texto = "Hello World";
        sprite = new SpriteSheet("testdata/DudeWalking.png",28,49);
        anim = new Animation(sprite,100);
        contenedor.getGraphics().setBackground(Color.gray);
        anim.stop();
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
		
        if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
            anim.start();
            if(x>0){
                x -= delta * 0.1f;
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
            anim.start();
            if(x<572){
                x += delta * 0.1f;
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_UP)) {
            anim.start();
            if(y>0){
                y -= delta * 0.1f;
            }
	}
        else if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
            anim.start();
            if(y<350){
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
        anim.draw(x, y);
        g.drawString("Coordenadas :" + x + ", " + y, 30, 30);
        
    }
    
    
    public static void main(String args[]){
        try{
            new Juego();
        }catch(org.newdawn.slick.SlickException e){}
    }
    
    
}
