/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

/**
 *
 * @author Michael Lofer
 */
public final class ClaseEstatica {
    private static Personaje personaje, enemigo;
    private static Sound sonidoPaso;
    private static Music musicaMenu;
    
    public ClaseEstatica(){
    }

    public static Sound getSonidoPaso() {
        return sonidoPaso;
    }

    public static void setSonidoPaso(Sound sonidoPaso) {
        ClaseEstatica.sonidoPaso = sonidoPaso;
    }
    
    public static Personaje getPersonaje() {
        return personaje;
    }

    public static void setPersonaje(Personaje personaje) {
        ClaseEstatica.personaje = personaje;
    }

    public static Personaje getEnemigo() {
        return enemigo;
    }

    public static void setEnemigo(Personaje enemigo) {
        ClaseEstatica.enemigo = enemigo;
    }

    public static Music getMusicaMenu() {
        return musicaMenu;
    }

    public static void setMusicaMenu(Music musicaMenu) {
        ClaseEstatica.musicaMenu = musicaMenu;
    }
    
    
}
