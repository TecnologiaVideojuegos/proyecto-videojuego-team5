/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import java.util.ArrayList;
import java.util.Scanner;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Michael Lofer
 */
public class Personaje {
    private int vida, vidaMax;
    private String nombre;
    private ArrayList<Ataque> ataques;
    private SpriteSheet spritePJ;

    public Personaje(int vida, String nombre/*, SpriteSheet sprite*/) {
        this.vidaMax=vida;
        this.vida=vida;
        this.nombre = nombre;
        ataques = new ArrayList<>();
        /*spritePJ=sprite;*/
    }

    public Personaje(){
        
    }
    public int getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }

    public SpriteSheet getSpritePJ() {
        return spritePJ;
    }

    public void setSpritePJ(SpriteSheet spritePJ) {
        this.spritePJ = spritePJ;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ataque> getAtaques() {
        return ataques;
    }

    public void setAtaques(ArrayList<Ataque> ataques) {
        this.ataques = ataques;
    }

    @Override
    public String toString() {
        return "Personaje{" + "vida=" + vida + ", nombre=" + nombre + ", ataques=" + ataques + '}';
    }
    
    public void atacar(Personaje penemigo){
        if(this.getVida()>0){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Selecciona un ataque: ");
                for(int i=0; i<this.getAtaques().size(); i++){
                    System.out.println(i+". "+this.getAtaques().get(i).getNombre()+" USOS: "+this.getAtaques().get(i).getUsos());
                }
                int seleccion = scanner.nextInt();
                if(this.getAtaques().get(seleccion).getUsos()!=0){ //si aún tiene usos disponibles del ataque elegido
                    this.getAtaques().get(seleccion).setUsos(this.getAtaques().get(seleccion).getUsos()-1); //le restamos un uso a ese ataque
                    if(this.getAtaques().get(seleccion).isAcertado()){
                        penemigo.setVida(penemigo.getVida()-this.getAtaques().get(seleccion).getDmg());
                        System.out.println("Ahora "+penemigo.getNombre()+" tiene: "+penemigo.getVida()+" vida");
                    }else{
                        System.out.println("OHHH que pena!!! "+this.getNombre()+" ha FALLADO EL ATAQUE!!!");
                    }
                }else{
                    System.out.println("NO TE QUEDAN USOS");
                }
        }else{
            System.out.println("¡HAS MUERTO!");
        }
    }
    
    public void ataqueEnemigo(Personaje personajeBueno){
        int ataqueEnemigo=0;
        boolean usosSufiecientes=false;
        if(this.getVida()>0){
            while(!usosSufiecientes){
                ataqueEnemigo = (int)Math.floor(Math.random()*3);
                if(this.getAtaques().get(ataqueEnemigo).getUsos()>0){
                    usosSufiecientes=true;
                }
            }
            if(this.getAtaques().get(ataqueEnemigo).isAcertado()){ //si acierta el ataque
                personajeBueno.setVida(personajeBueno.getVida()-this.getAtaques().get(ataqueEnemigo).getDmg());
                System.out.println(this.getNombre()+" usó: "+this.getAtaques().get(ataqueEnemigo).getNombre());
                System.out.println("Ahora "+personajeBueno.getNombre()+" tiene: "+personajeBueno.getVida()+ " vida");
            }else{
                System.out.println("OHHH que pena!!! "+this.getNombre()+" ha FALLADO EL ATAQUE!!!");
            }
        }
    }
    
    public void restaurarTodo(){ //restaurará todos los stats del personaje
        vida=vidaMax;
        for(int i=0; i<ataques.size(); i++){
            ataques.get(i).setUsos(ataques.get(i).getUsosMax());
        }
    }
}
