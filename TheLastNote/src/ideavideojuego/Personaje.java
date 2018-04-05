/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import java.util.ArrayList;

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
    
    
    
}
