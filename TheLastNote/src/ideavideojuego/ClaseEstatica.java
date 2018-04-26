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
public final class ClaseEstatica {
    private static Personaje personaje;
    
    public ClaseEstatica(){
    }

    public static Personaje getPersonaje() {
        return personaje;
    }

    public static void setPersonaje(Personaje personaje) {
        ClaseEstatica.personaje = personaje;
    }
}
