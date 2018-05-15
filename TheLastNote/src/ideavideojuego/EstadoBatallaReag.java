/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;


/**
 *
 * @author Álvaro Zamorano
 */
public class EstadoBatallaReag extends BasicGameState{
    private Image fondo, hud;
    private Sprite puntero;
    private static final Punto atacar = new Punto(378, 614);
    private static final Punto huir = new Punto(731, 607);
    private static final Punto a1 = new Punto(236, 514);
    private static final Punto a2 = new Punto(474, 513);
    private static final Punto a3 = new Punto(787, 511);
    private int indicador, dato, tEspera;
    private String texto, ataque, textoAtaque, textoHuir, textoAccionP, textoAccionM, message;
    private boolean turno; //si es true nosotros atacamos, sino --> la maquina
    private static UnicodeFont font;
    
    @Override
    public int getID() {
       return 9;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo = new Image("Design/battlev1background.png");
        //hud = new Image("Design/battlev1menu.png");
        puntero = new Sprite("Design/cursor1.png", atacar);
        turno=true;
        java.awt.Font fuenteAWT = new java.awt.Font("Comic Sans MS", 0, 24);
        font = new UnicodeFont(fuenteAWT);
        font.addAsciiGlyphs();
        ColorEffect colorFuente = new ColorEffect(java.awt.Color.MAGENTA);
        font.getEffects().add(colorFuente);
        font.loadGlyphs();
        //java.awt.Font fuente = new java.awt.Font("Comic Sans MS", Font.PLAIN, 24):
        
        texto="";
        message="";
        ataque="";
        textoAccionP=""; //ataque del aliado
        textoAccionM=""; //ataque del enemigo
        textoAtaque="¡ADVERTENCIA!, TE ENRENTAS A LUIS FONSI, PODRÁS CONTRA SU FLOW?";
        textoHuir="¿ESCAPAR? ¡JÁ!";
        indicador=0;
        dato=0;
        tEspera=3000;
        System.out.println("Ultimo ataque -->"+ataque);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(org.newdawn.slick.Color.cyan);
        fondo.draw();
        hud.draw();
        puntero.draw();
        ClaseEstatica.getPersonaje().getAnimD().draw(300, 330);
        ClaseEstatica.getEnemigo().getAnimI().draw(600, 330);
        g.drawString(message, 10, 10);
        if(ClaseEstatica.getPersonaje().getAnimD().isStopped()){
            ClaseEstatica.getPersonaje().getAnimD().start();
        }
        //ClaseEstatica.getPersonaje().getAnimI().draw(300, 150);
        /*g.drawString(ClaseEstatica.getPersonaje().getAtaques().get(0).getNombre(), 50, 600);
        g.drawString("Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(0).getUsos(), 50, 580);
        g.drawString(ClaseEstatica.getPersonaje().getAtaques().get(1).getNombre(), 300, 600);
        g.drawString("Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(1).getUsos(), 300, 580);
        g.drawString(ClaseEstatica.getPersonaje().getAtaques().get(2).getNombre(), 550, 600);
        g.drawString("Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(2).getUsos(), 550, 580);
        g.drawString("ATACAR", 850, 600);
        g.drawString("HUIR", 860, 650);
        g.drawString("HUIR", 860, 650);*/

        if((texto.equals(textoAtaque)) || (texto.equals(textoHuir))){
            g.drawString(texto, 840, 604);
        }else{
            g.drawString(texto, 20, 700);  
        }
        /*g.drawString(textoAccionP, 833, 550);
        g.drawString(textoAccionM, 833, 610);*/
        if(indicador==2){
            font.drawString(825, 550, "Daño: "+ClaseEstatica.getPersonaje().getAtaques().get(0).getDmg());
            font.drawString(825, 580, "Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(1).getUsos());
            font.drawString(825, 610, "Probabilidad: "+(100-ClaseEstatica.getPersonaje().getAtaques().get(0).getProbabilidadFallo())+"%");
        }else if(indicador==3){
            g.drawString("Daño: "+ClaseEstatica.getPersonaje().getAtaques().get(1).getDmg(), 833, 550);
            g.drawString("Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(1).getUsos(), 833, 565);
            g.drawString("Probabilidad: "+(100-ClaseEstatica.getPersonaje().getAtaques().get(1).getProbabilidadFallo())+"%", 833, 580);
        }else if(indicador==4){
            g.drawString("Daño: "+ClaseEstatica.getPersonaje().getAtaques().get(2).getDmg(), 833, 550);
            g.drawString("Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(2).getUsos(), 833, 565);
            g.drawString("Probabilidad: "+(100-ClaseEstatica.getPersonaje().getAtaques().get(2).getProbabilidadFallo())+"%", 833, 580);
        }else{
            g.drawString("", 833, 550);
            g.drawString("", 833, 565);
        }
        g.drawString("Vida: "+ClaseEstatica.getPersonaje().getVida(), 833, 510);
        g.drawString("Vida: "+ClaseEstatica.getEnemigo().getVida(), 833, 530);
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
        if(ataque.equals("SaxGuy") && ClaseEstatica.isAtaqueAcertado()){
            tEspera=6000;
        }else{
            tEspera=3000;
        }
        if(ClaseEstatica.getPersonaje().getVida()>0 && ClaseEstatica.getEnemigo().getVida()>0){
            if((!turno)&&(dato>tEspera)){ //<-------------------------------------------------------------------------- AQUI
                        textoAccionM=ClaseEstatica.getEnemigo().ataqueEnemigo(ClaseEstatica.getPersonaje());
                        dato=0;
                        turno=true;
                        ClaseEstatica.getClick().play();
                }
            if(indicador<2){
                if(entrada.isKeyPressed(Input.KEY_LEFT)){
                    indicador=0;
                    puntero.setPosicion(atacar);
                    texto=textoAtaque;
                    if(!ClaseEstatica.getClick().playing())
                        ClaseEstatica.getClick().play();
                }else if(entrada.isKeyPressed(Input.KEY_RIGHT)){
                    indicador=1;
                    puntero.setPosicion(huir);
                    if(!ClaseEstatica.getClick().playing())
                        ClaseEstatica.getClick().play();
                }
            }else{
                if(entrada.isKeyPressed(Input.KEY_RIGHT) && indicador>=2){
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
                }
            }
            if(entrada.isKeyPressed(Input.KEY_ENTER)){
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
                        ataque=ClaseEstatica.getUltimoAtaque();
                    }
                }else if(indicador==3){
                    if(turno){
                        textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 1);
                        turno=false;
                        dato=0;
                        ataque=ClaseEstatica.getUltimoAtaque();
                    }
                }else if(indicador==4){
                    if(turno){
                        textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 2);
                        turno=false;
                        dato=0;
                        ataque=ClaseEstatica.getUltimoAtaque();
                    }
                }     
            }
        }else{
            if(ClaseEstatica.getPersonaje().getVida()>0){
                if(dato>tEspera){
                    System.out.println("ENHORABUENA, HAS GANADO EL COMBATE, PASARÁS AL SIGUIENTE PASILLO");
                    game.enterState(5);
                }
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
           hud=ClaseEstatica.getPersonaje().getHUD();
        }
       
       public void mouseClicked(int button, int x, int y, int clickCount) {
		if (clickCount == 1) {
			message = "Single Click: "+button+" "+x+","+y;
		}
		if (clickCount == 2) {
			message = "Double Click: "+button+" "+x+","+y;
		}
	}
    
}
