/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import java.util.Scanner;

/**
 *
 * @author Michael Lofer
 */
public class IdeaVideojuego {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        //INICIALIZACIÓN DE LOS ATAQUES
        Ataque Guitarrazo = new Ataque(30, 20, "Guitarrazo", "Lanzará su guitarra para causar un daño leve", 10);
        Ataque Mama = new Ataque(65, 10, "Mama", "Inflingirá un daño brutal en los tímpanos del enemigo", 10);
        Ataque DiscoPlatino = new Ataque(80, 5, "Disco de platino", "Lanzará uno de sus discos de platino a la yugular para causar un daño LETAL!!!", 10);
       
        Ataque Saxofonazo = new Ataque(35, 20, "Saxofonazo", "Lanzará un saxofón para causar un daño leve", 10);
        Ataque BaileCadera = new Ataque(50, 10, "Baile de cadera", "Usará la mítica canción de Eurovisión para causar un daño bestial!!", 10);
        Ataque SaxGuy = new Ataque(60, 5, "TrumpetGuy", "Usará la mítica canción de Eurovisión para causar un daño LETAL!!", 10);
        
        Ataque Pianazo = new Ataque(15, 20, "Pianazo", "Lanzará un piano para causar un daño leve", 10);
        Ataque MetricaExacta = new Ataque(20, 10, "Metrica Exacta", "Regañará al enemigo por no llevar el ritmo acorde e inflingirá daño por humillación", 10);
        Ataque PelucoVictoriano = new Ataque(35, 5, "Peluco Victoriano", "Lanzará su tremenda peluca para destrozar los sueños capilares del enemigo, causando un daño LETAL!!!", 10);
        
        Ataque Microfonazo = new Ataque(10, 20, "Microfonazo", "Lanzará un micrófono para causar un daño leve", 10);
        Ataque Flow = new Ataque(30, 10, "Flow", "Moverá sus caderas para causar un daño brutal en la vista del enemigo", 10);
        Ataque Despacito = new Ataque(40, 5, "Despacito", "Cantará su mitica canción Despacito para causar daño letal en los oidos del enemigo", 10);
        
        Ataque Peluquin = new Ataque(10, 20, "Peluquin", "Lanzará su peluquin para causar un daño leve", 10);
        Ataque Trap = new Ataque(30, 10, "Bad Bunny", "Cantará una canción de su amigo Bad Bunny para causar un daño brutal a su enemigo", 10);
        Ataque Muro = new Ataque(40, 5, "Muro", "Lanzará un muro pagado por todos causando un daño LETAL!!!", 10);
        
        Ataque Misilazo = new Ataque(10, 20, "Misilazo", "Lanzará un misil para causar un daño leve", 10);
        Ataque Kpop = new Ataque(30, 10, "Ritmo K-POP", "Moverá su cuerpo al ritmo de K-POP para causar un daño brutal a su enemigo", 10);
        Ataque Nuclear = new Ataque(40, 5, "Ataque nuclear", "Lanzará un ataque nuclear para causar un daño LETAL!!!", 10);
        
        //INICIALIACIÓN DE LOS PERSONAJES Y SE AÑADEN LOS ATAQUES
        Personaje AlfredoMercurio = new Personaje(200, "Alfredo Mercurio");
        AlfredoMercurio.getAtaques().add(Guitarrazo);
        AlfredoMercurio.getAtaques().add(Mama);
        AlfredoMercurio.getAtaques().add(DiscoPlatino);
        
        Personaje MoldovaSax = new Personaje(300,"Moldova Sax");
        MoldovaSax.getAtaques().add(Saxofonazo);
        MoldovaSax.getAtaques().add(BaileCadera);
        MoldovaSax.getAtaques().add(SaxGuy);
        
        Personaje LudwigvanMozart = new Personaje(400,"Ludwin van Mozart");
        LudwigvanMozart.getAtaques().add(Pianazo);
        LudwigvanMozart.getAtaques().add(MetricaExacta);
        LudwigvanMozart.getAtaques().add(PelucoVictoriano);
        
        Personaje LuisFonsi = new Personaje(250,"Ludis Fonsi");
        LuisFonsi.getAtaques().add(Microfonazo);
        LuisFonsi.getAtaques().add(Flow);
        LuisFonsi.getAtaques().add(Despacito);
        
        Personaje DonaldTrap = new Personaje(250, "Donald Trap");
        DonaldTrap.getAtaques().add(Peluquin);
        DonaldTrap.getAtaques().add(Trap);
        DonaldTrap.getAtaques().add(Muro);
        
        Personaje KimJongDos = new Personaje(250, "Kim Jong-Dos");
        KimJongDos.getAtaques().add(Misilazo);
        KimJongDos.getAtaques().add(Kpop);
        KimJongDos.getAtaques().add(Nuclear);
        
        
        
        while(AlfredoMercurio.getVida()>0 && DonaldTrap.getVida()>0){
            System.out.println("--------------------------------------------------------------------------------------------");
            AlfredoMercurio.atacar(DonaldTrap);
            System.out.println("--------------------------------------------------------------------------------------------");
            DonaldTrap.ataqueEnemigo(AlfredoMercurio);
        }
        System.out.println("SE HA TERMIADO EL COMBATE");
    }
    
}
