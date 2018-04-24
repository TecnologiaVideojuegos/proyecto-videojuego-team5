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
public class EstadoCamerino extends BasicGameState{
    private AppGameContainer contenedor;
    private float x,y;
    private Animation anim,alfredoD,alfredoI;
    private SpriteSheet sprite,spriteAlfredoD,spriteAlfredoI;
    private float ang;
    private Music music;
    private boolean derecha;
    private Personaje personaje;
    
    @Override
    public int getID() {
        return 2;
    }
    
    public EstadoCamerino(Personaje personaje){
        this.personaje = personaje;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.x = 20; //Coordenadas donde empieza el personaje
        this.y = 193;
        music = new Music("Musica/rock_hall.ogg", false);
        spriteAlfredoD = new SpriteSheet("Design/FreddieWalkSMALL_V2.png",18,41);
        spriteAlfredoI = new SpriteSheet("Design/FreddieWalkSMALL_V1.png",18,41);
        alfredoD = new Animation(spriteAlfredoD,100);
        alfredoI = new Animation(spriteAlfredoI,100);
        derecha=true;
        ang = 30f;
        contenedor.getGraphics().setBackground(Color.gray);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if(derecha){
            alfredoD.draw(x,y);
        }
        else{
            alfredoI.draw(x,y);
        }
        g.drawString("UNTIL THE LAST NOTE", 30, 30);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
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
    }
    
}
