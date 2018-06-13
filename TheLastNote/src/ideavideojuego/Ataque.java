/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Sound;

/**
 *
 * @author Michael Lofer
 */
public class Ataque {

    private int dmg, usos, probabilidadFallo, usosMax;
    private String nombre, descripcion;
    private Sound efecto;

    public Ataque(int dmg, int usos, String nombre, String descripcion, int probabilidadFallo, Sound efecto) {
        this.usosMax = usos;
        this.dmg = dmg;
        this.usos = usos;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.probabilidadFallo = probabilidadFallo;
        this.efecto = efecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUsosMax() {
        return usosMax;
    }

    public void setUsosMax(int usosMax) {
        this.usosMax = usosMax;
    }

    public int getProbabilidadFallo() {
        return probabilidadFallo;
    }

    public void setProbabilidadFallo(int probabilidadFallo) {
        this.probabilidadFallo = probabilidadFallo;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        this.usos = usos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sound getEfecto() {
        return efecto;
    }

    public void setEfecto(Sound efecto) {
        this.efecto = efecto;
    }

    public boolean isAcertado() {  //devuelve si acierta el ataque o no
        boolean acertado;
        int contador = 0;
        for (int i = 0; i < 10; i++) {
            int valor = (int) (Math.floor(Math.random() * 2)); //suma 0 o 1
            contador += valor;
            //System.out.println("Contador posibilidad -->"+contador);
        }
        contador = contador * 10; //lo multiplica por 10 para compararlo con la probablidad de fallo
        if (contador >= probabilidadFallo) { //si el contador es mayor que la probabilidad de fallo el ataque acierta
            acertado = true;
        } else {
            acertado = false;
        }
        return acertado;
    }
    
    public boolean isCritico(int x) {  //devuelve si el ataque es critico o no (daño x2), x es la probabilidad de fallo de crítico
        boolean critico;
        int contador = 0;
        for (int i = 0; i < 10; i++) {
            int valor = (int) (Math.floor(Math.random() * 2)); //suma 0 o 1
            contador += valor;
            //System.out.println("Contador posibilidad -->"+contador);
        }
        if (contador >= x) { //si el contador es mayor o igual que la probabilidad de fallo el ataque es critica
            critico = true;
        } else {
            critico = false;
        }
        return critico;
    }

    @Override
    public String toString() {
        return "Ataque{" + "dmg=" + dmg + ", usos=" + usos + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
}
