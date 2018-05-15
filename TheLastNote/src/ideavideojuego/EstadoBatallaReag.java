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
    private static final Punto atacar = new Punto(345, 529);
    private static final Punto huir = new Punto(709, 529);
    private static final Punto a1 = new Punto(150, 465);
    private static final Punto a2 = new Punto(410, 465);
    private static final Punto a3 = new Punto(675, 465);
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
        puntero = new Sprite("Design/cursor1.png", atacar);
        turno=true;
        java.awt.Font fuenteAWT = new java.awt.Font("Rockwell Condensed", 0, 24);
        font = new UnicodeFont(fuenteAWT);
        font.addAsciiGlyphs();
        ColorEffect colorFuente = new ColorEffect(java.awt.Color.WHITE);
        font.getEffects().add(colorFuente);
        font.loadGlyphs();
        
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
        //ClaseEstatica.getPersonaje().getAnimD().draw(300, 330);
        //ClaseEstatica.getEnemigo().getAnimI().draw(600, 330);
        ClaseEstatica.getPersonaje().getPJBatalla().draw();
        ClaseEstatica.getEnemigo().getPJBatalla().draw();
        font.drawString(10, 10, message);
        if(ClaseEstatica.getPersonaje().getAnimD().isStopped()){
            ClaseEstatica.getPersonaje().getAnimD().start();
        }

        if((texto.equals(textoAtaque)) || (texto.equals(textoHuir))){
            font.drawString(80, 630, texto);
        }else{
            //g.drawString(texto, 80, 640);  
        }
        
        if(indicador==2){
            font.drawString(832, 485, "Daño: "+ClaseEstatica.getPersonaje().getAtaques().get(0).getDmg());
            font.drawString(832, 510, "Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(0).getUsos());
            font.drawString(832, 535, "Probabilidad: "+(100-ClaseEstatica.getPersonaje().getAtaques().get(0).getProbabilidadFallo())+"%");
        }else if(indicador==3){
            font.drawString(832, 485, "Daño: "+ClaseEstatica.getPersonaje().getAtaques().get(1).getDmg());
            font.drawString(832, 510, "Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(1).getUsos());
            font.drawString(832, 535, "Probabilidad: "+(100-ClaseEstatica.getPersonaje().getAtaques().get(1).getProbabilidadFallo())+"%");
        }else if(indicador==4){
            font.drawString(832, 485, "Daño: "+ClaseEstatica.getPersonaje().getAtaques().get(2).getDmg());
            font.drawString(832, 510, "Usos: "+ClaseEstatica.getPersonaje().getAtaques().get(2).getUsos());
            font.drawString(832, 535, "Probabilidad: "+(100-ClaseEstatica.getPersonaje().getAtaques().get(2).getProbabilidadFallo())+"%");
        }else{
            g.drawString("", 833, 550);
            g.drawString("", 833, 565);
        }
        font.drawString(170, 25, "Vida: "+ClaseEstatica.getPersonaje().getVida());
        font.drawString(760, 55, "Vida: "+ClaseEstatica.getEnemigo().getVida());
        if(!ClaseEstatica.getPersonaje().getMusicB().playing()){
            ClaseEstatica.getPersonaje().getMusicB().play();
        }
        font.drawString(400, 20, "El DELTA ES --> "+dato);
        if((turno) && (dato>tEspera)){
            font.drawString(832, 457, "ES TU TURNO", org.newdawn.slick.Color.green);
            font.drawString(80, 630, textoAccionM);
        }else{
            font.drawString(832, 457, "NO ES TU TURNO", org.newdawn.slick.Color.red);
            font.drawString(80, 630, textoAccionP);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada = container.getInput();
        dato+=delta;
        if(ataque.equals("SaxGuy") && ClaseEstatica.isAtaqueAcertado()){
            tEspera=8500;
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
                    if(indicador<=4){
                        if (indicador<=3)
                            indicador++;
                        System.out.println("Indicador --> "+indicador);
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
                    if((turno) && (dato>tEspera)){
                        textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 0);
                        turno=false;
                        dato=0;
                        ataque=ClaseEstatica.getUltimoAtaque();
                    }
                }else if(indicador==3){
                    if((turno) && (dato>tEspera)){
                        textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 1);
                        turno=false;
                        dato=0;
                        ataque=ClaseEstatica.getUltimoAtaque();
                    }
                }else if(indicador==4){
                    if((turno) && (dato>tEspera)){
                        textoAccionP=ClaseEstatica.getPersonaje().atacar(ClaseEstatica.getEnemigo(), 2);
                        turno=false;
                        dato=0;
                        ataque=ClaseEstatica.getUltimoAtaque();
                    }
                }     
            }
        }else{
            /*if(ClaseEstatica.getPersonaje().getVida()>0){
                if(dato>tEspera){
                    System.out.println("ENHORABUENA, HAS GANADO EL COMBATE, PASARÁS AL SIGUIENTE PASILLO");
                    game.enterState(5);
                }
            }else{
                System.out.println("OH NOO, HAS PERDIDO, VOLVERÁS AL CAMERINO");
                ClaseEstatica.getPersonaje().restaurarTodo();
                ClaseEstatica.getEnemigo().restaurarTodo();
                game.enterState(2);
            }*/
            System.out.println("HOLA");
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
