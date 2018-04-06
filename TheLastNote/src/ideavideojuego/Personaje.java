/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Michael Lofer
 */
public class Personaje {
    private int vida;
    private String nombre;
    private ArrayList<Ataque> ataques;

    public Personaje(int vida, String nombre) {
        this.vida = vida;
        this.nombre = nombre;
        ataques = new ArrayList<>();
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
                    System.out.println(i+". "+this.getAtaques().get(i).getNombre());
                }
                int seleccion = scanner.nextInt();
                penemigo.setVida(penemigo.getVida()-this.getAtaques().get(seleccion).getDmg());
                System.out.println("Ahora "+penemigo.getNombre()+" tiene: "+penemigo.getVida()+" vida");
        }else{
            System.out.println("¡HAS MUERTO!");
        }
    }
    
    public void ataqueEnemigo(Personaje personaje){
        if(this.getVida()>0){
            int ataqueEnemigo = (int) Math.random()*2;
            personaje.setVida(personaje.getVida()-this.getAtaques().get(ataqueEnemigo).getDmg());
            System.out.println(this.getNombre()+" usó: "+this.getAtaques().get(ataqueEnemigo).getNombre());
            System.out.println("Ahora "+personaje.getNombre()+" tiene: "+personaje.getVida()+ " vida");
        }
    }
}
