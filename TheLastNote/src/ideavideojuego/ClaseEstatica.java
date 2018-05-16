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
    private static Sound sonidoPaso, click, fail;
    private static Music musicaMenu,musicSilence;
    private static String ultimoAtaque;
    private static boolean ataqueAcertado;
    
    public ClaseEstatica() {
    }

    public static Sound getSonidoPaso() {
        return sonidoPaso;
    }

    public static Sound getFail() {
        return fail;
    }

    public static void setFail(Sound fail) {
        ClaseEstatica.fail = fail;
    }

    public static void setSonidoPaso(Sound sonidoPaso) {
        ClaseEstatica.sonidoPaso = sonidoPaso;
    }

    public static String getUltimoAtaque() {
        return ultimoAtaque;
    }

    public static void setUltimoAtaque(String ultimoAtaque) {
        ClaseEstatica.ultimoAtaque = ultimoAtaque;
    }

    public static boolean isAtaqueAcertado() {
        return ataqueAcertado;
    }

    public static void setAtaqueAcertado(boolean ataqueAcertado) {
        ClaseEstatica.ataqueAcertado = ataqueAcertado;
    }

    public static Sound getClick() {
        return click;
    }

    public static void setClick(Sound click) {
        ClaseEstatica.click = click;
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

    public static Music getMusicSilence() {
        return musicSilence;
    }

    public static void setMusicSilence(Music musicSilence) {
        ClaseEstatica.musicSilence = musicSilence;
    }
    

}
