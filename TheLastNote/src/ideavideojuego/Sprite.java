/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

/**
 *
 * @author Michael Lofer
 */
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends Image {

    protected Punto posicion;

    public Sprite(String ruta) throws SlickException {
        this(ruta, new Punto(0.0f, 0.0f));
    }

    public Sprite(String ruta, float x, float y) throws SlickException {
        this(ruta, new Punto(x, y));
    }

    public Sprite(String ruta, Punto posicion) throws SlickException {
        super(ruta);
        this.posicion = posicion;
    }

    public void draw() {
        super.draw(this.posicion.getX(), this.posicion.getY());
    }

    public Punto getPosicion() {
        return this.posicion;
    }

    public void setPosicion(float x, float y) {
        this.posicion = new Punto(x, y);
    }

    public void setPosicion(Punto posicion) {
        this.posicion = posicion;
    }
}