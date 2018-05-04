/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoBatallaReag extends BasicGameState{
    private Image fondo;
    private Sprite puntero;
    private static final Punto atacar = new Punto(925, 580);
    private static final Punto huir = new Punto(925, 630);
    private static final Punto a1 = new Punto(50, 620);
    private static final Punto a2 = new Punto(300, 620);
    private static final Punto a3 = new Punto(550, 620);
    private int indicador;
    private String texto, textoAtaque, textoHuir, textoAccionP, textoAccionM;
    private UnicodeFont fuente;
    
    @Override
    public int getID() {
       return 9;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/scenario1.png");
        puntero = new Sprite("Design/cursor1.png", atacar);
        //java.awt.Font fuente = new java.awt.Font("Comic Sans MS", Font.PLAIN, 24):
        
        texto="";
        textoAccionP="";
        textoAccionM="";
        textoAtaque="¡ADVERTENCIA!, TE ENRENTAS A LUIS FONSI, PODRÁS CONTRA SU FLOW?";
        textoHuir="¿ESCAPAR? ¡JÁ!";
        indicador=0;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        puntero.draw();
        ClaseEstatica.getPersonaje().getAnimD().draw(300, 330);
        ClaseEstatica.getEnemigo().getAnimI().draw(600, 330);
        //ClaseEstatica.getPersonaje().getAnimI().draw(300, 150);
        g.drawString(ClaseEstatica.getPersonaje().getAtaques().get(0).getNombre(), 50, 600);
        g.drawString(ClaseEstatica.getPersonaje().getAtaques().get(1).getNombre(), 300, 600);
        g.drawString(ClaseEstatica.getPersonaje().getAtaques().get(2).getNombre(), 550, 600);
        g.drawString("ATACAR", 850, 600);
        g.drawString("HUIR", 860, 650);
        if((texto.equals(textoAtaque)) || (texto.equals(textoHuir))){
            g.drawString(texto, 400, 700);
        }else{
            g.drawString(texto, 20, 700);  
        }
        g.drawString(textoAccionP, 50, 100);
        g.drawString(textoAccionM, 50, 150);
        g.drawString("Vida: "+ClaseEstatica.getPersonaje().getVida(), 290, 305);
        g.drawString("Vida: "+ClaseEstatica.getEnemigo().getVida(), 610, 305);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        if(ClaseEstatica.getPersonaje().getVida()>0 && ClaseEstatica.getEnemigo().getVida()>0){
            if(entrada.isKeyPressed(Input.KEY_UP) && indicador<2){
                indicador=0;
                puntero.setPosicion(atacar);
                texto=textoAtaque;
            }else if(entrada.isKeyPressed(Input.KEY_DOWN) && indicador<2){
                indicador=1;
                puntero.setPosicion(huir);
            }else if(entrada.isKeyPressed(Input.KEY_RIGHT) && indicador>=2){
                if(indicador<5){
                    indicador++;
                    if (indicador==2){
                        puntero.setPosicion(a1);
                        texto=ClaseEstatica.getPersonaje().getAtaques().get(0).getDescripcion();
                    }else if(indicador==3){
                        puntero.setPosicion(a2);
                        texto=ClaseEstatica.getPersonaje().getAtaques().get(1).getDescripcion();
                    }else if(indicador==4){
                        puntero.setPosicion(a3);
                        texto=ClaseEstatica.getPersonaje().getAtaques().get(2).getDescripcion();
                    }
                }
            }else if(entrada.isKeyPressed(Input.KEY_LEFT) && indicador>=2){
                if(indicador>2){
                    indicador--;
                    if (indicador==2){
                        puntero.setPosicion(a1);
                        texto=ClaseEstatica.getPersonaje().getAtaques().get(0).getDescripcion();
                    }else if(indicador==3){
                        puntero.setPosicion(a2);
                        texto=ClaseEstatica.getPersonaje().getAtaques().get(1).getDescripcion();
                    }else if(indicador==4){
                        puntero.setPosicion(a3);
                        texto=ClaseEstatica.getPersonaje().getAtaques().get(2).getDescripcion();
                    }
                }
            }else if(entrada.isKeyPressed(Input.KEY_ESCAPE)){
                indicador=0;
                puntero.setPosicion(atacar);
                texto="";
            }else if(entrada.isKeyPressed(Input.KEY_ENTER)){
                if(indicador==0){
                    indicador=2;
                    puntero.setPosicion(a1);
                    texto=ClaseEstatica.getPersonaje().getAtaques().get(0).getDescripcion();
                }else if(indicador==1){
                    texto=textoHuir;
                }else if(indicador==2){
                    textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 0);
                    textoAccionM=ClaseEstatica.getEnemigo().ataqueEnemigo(ClaseEstatica.getPersonaje());
                }else if(indicador==3){
                    
                    textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 1);
                    textoAccionM=ClaseEstatica.getEnemigo().ataqueEnemigo(ClaseEstatica.getPersonaje());
                }else if(indicador==4){
                    textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 2);
                    textoAccionM=ClaseEstatica.getEnemigo().ataqueEnemigo(ClaseEstatica.getPersonaje());
                }
            }
        }else{
            if(ClaseEstatica.getPersonaje().getVida()>0){
                System.out.println("ENHORABUENA, HAS GANADO EL COMBATE, PASARÁS AL SIGUIENTE PASILLO");
                game.enterState(5);
            }else{
                System.out.println("OH NOO, HAS PERDIDO, VOLVERÁS AL CAMERINO");
                ClaseEstatica.getPersonaje().restaurarTodo();
                ClaseEstatica.getEnemigo().restaurarTodo();
                game.enterState(2);
            }
        }
    } 
    
    @Override
       public void enter(GameContainer container, StateBasedGame game) throws SlickException {
           ClaseEstatica.getPersonaje().getMusicB().play();
    }
    
}
