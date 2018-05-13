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
public class EstadoBatallaTrap extends BasicGameState{
    private Image fondo;
    private Sprite puntero;
    private static final Punto atacar = new Punto(925, 580);
    private static final Punto huir = new Punto(925, 630);
    private static final Punto a1 = new Punto(50, 620);
    private static final Punto a2 = new Punto(300, 620);
    private static final Punto a3 = new Punto(550, 620);
    private int indicador, dato, tEspera;
    private String texto, textoAtaque, textoHuir, textoAccionP, textoAccionM;
    private UnicodeFont fuente;
    private boolean turno; //si es true nosotros atacamos, sino --> la maquina
    
    @Override
    public int getID() {
       return 10;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/scenario1.png");
        puntero = new Sprite("Design/cursor1.png", atacar);
        turno=true;
        //java.awt.Font fuente = new java.awt.Font("Comic Sans MS", Font.PLAIN, 24):
        
        texto="";
        textoAccionP="";
        textoAccionM="";
        textoAtaque="¡ADVERTENCIA!, TE ENRENTAS A DONALD TRAP, PODRÁS ESCALAR SUS MUROS FRONTERIZOS?";
        textoHuir="¿ESCAPAR? ¡JÁ!";
        indicador=0;
        dato=0;
        tEspera=3000;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        puntero.draw();
        ClaseEstatica.getPersonaje().getAnimD().draw(300, 330);
        ClaseEstatica.getEnemigo().getAnimI().draw(600, 330);
        //ClaseEstatica.getPersonaje().getAnimI().draw(300, 150);
        g.drawString(ClaseEstatica.getPersonaje().getAtaques().get(0).getNombre(), 50, 600);
        g.drawString("Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(0).getUsos(), 50, 580);
        g.drawString(ClaseEstatica.getPersonaje().getAtaques().get(1).getNombre(), 300, 600);
        g.drawString("Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(1).getUsos(), 300, 580);
        g.drawString(ClaseEstatica.getPersonaje().getAtaques().get(2).getNombre(), 550, 600);
        g.drawString("Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(2).getUsos(), 550, 580);
        g.drawString("ATACAR", 850, 600);
        g.drawString("HUIR", 860, 650);
        if((texto.equals(textoAtaque)) || (texto.equals(textoHuir))){
            g.drawString(texto, 300, 700);
        }else{
            g.drawString(texto, 20, 700);  
        }
        g.drawString(textoAccionP, 50, 100);
        g.drawString(textoAccionM, 50, 150);
        g.drawString("Vida: "+ClaseEstatica.getPersonaje().getVida(), 290, 305);
        g.drawString("Vida: "+ClaseEstatica.getEnemigo().getVida(), 610, 305);
        if(!ClaseEstatica.getPersonaje().getMusicB().playing()){
            ClaseEstatica.getPersonaje().getMusicB().play();
        }
        g.drawString("El DELTA ES --> "+dato, 20, 20);
        if(turno){
            g.drawString("ES TU TURNO", 20, 40);
        }else{
            g.drawString("NO ES TU TURNO", 20, 40);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        dato+=delta;
        if(ClaseEstatica.getPersonaje().getVida()>0 && ClaseEstatica.getEnemigo().getVida()>0){
            if((!turno) &&(dato>tEspera)){ //<-------------------------------------------------------------------------- AQUI
                        textoAccionM=ClaseEstatica.getEnemigo().ataqueEnemigo(ClaseEstatica.getPersonaje());
                        dato=0;
                        turno=true;
                        ClaseEstatica.getClick().play();
                }
            if(entrada.isKeyPressed(Input.KEY_UP) && indicador<2){
                indicador=0;
                puntero.setPosicion(atacar);
                texto=textoAtaque;
                if(!ClaseEstatica.getClick().playing())
                    ClaseEstatica.getClick().play();
            }else if(entrada.isKeyPressed(Input.KEY_DOWN) && indicador<2){
                indicador=1;
                puntero.setPosicion(huir);
                if(!ClaseEstatica.getClick().playing())
                    ClaseEstatica.getClick().play();
            }else if(entrada.isKeyPressed(Input.KEY_RIGHT) && indicador>=2){
                if(!ClaseEstatica.getClick().playing())
                    ClaseEstatica.getClick().play();
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
                if(!ClaseEstatica.getClick().playing())
                    ClaseEstatica.getClick().play();
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
                if(!ClaseEstatica.getClick().playing())
                    ClaseEstatica.getClick().play();
                indicador=0;
                puntero.setPosicion(atacar);
                texto="";
            }else if(entrada.isKeyPressed(Input.KEY_ENTER)){
                if(!ClaseEstatica.getClick().playing())
                    ClaseEstatica.getClick().play();
                if(indicador==0){
                    indicador=2;
                    puntero.setPosicion(a1);
                    texto=ClaseEstatica.getPersonaje().getAtaques().get(0).getDescripcion();
                }else if(indicador==1){
                    texto=textoHuir;
                }else if(indicador==2){
                    if(turno){
                        textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 0);
                        turno=false;
                        dato=0;
                    }
                }else if(indicador==3){
                    if(turno){
                        textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 1);
                        turno=false;
                        dato=0;
                    }
                }else if(indicador==4){
                    if(turno){
                        textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 2);
                        turno=false;
                        dato=0;
                    }
                }
            }
        }else{
            if(ClaseEstatica.getPersonaje().getVida()>0){
                System.out.println("ENHORABUENA, HAS GANADO EL COMBATE, PASARÁS AL SIGUIENTE PASILLO");
                game.enterState(7);
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
