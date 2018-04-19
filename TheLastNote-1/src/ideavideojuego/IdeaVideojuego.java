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
        Ataque TrumpetGuy = new Ataque(70, 5, "TrumpetGuy", "Usará la mítica canción de Eurovisión para causar un daño bestial!!", 50);
        Ataque Guitarrazo = new Ataque(30, 20, "Guitarrazo", "Lanzará su guitarra para causar un daño leve", 15);
        Ataque Peluco = new Ataque(70, 20, "Peluco", "Lanzará su increible peluco para causar un daño LETAL!!!", 60);
        Ataque AhoraSoyMejor = new Ataque(30, 20, "AhoraSoyMejor", "Cantará de una forma horrenda una versión de AhoraSoyMejor para destrozar los timpanos del rival",15);
        
        Personaje AlfredoMercurio = new Personaje(1000, "Alfredo Mercurio");
        AlfredoMercurio.getAtaques().add(TrumpetGuy);
        AlfredoMercurio.getAtaques().add(Guitarrazo);
        
        Personaje DonaldTrap = new Personaje(1000, "Donald Trap");
        DonaldTrap.getAtaques().add(Peluco);
        DonaldTrap.getAtaques().add(AhoraSoyMejor);
        
        while(AlfredoMercurio.getVida()>0 && DonaldTrap.getVida()>0){
            System.out.println("--------------------------------------------------------------------------------------------");
            AlfredoMercurio.atacar(DonaldTrap);
            System.out.println("--------------------------------------------------------------------------------------------");
            DonaldTrap.ataqueEnemigo(AlfredoMercurio);
        }
        System.out.println("SE HA TERMIADO EL COMBATE");
    }
    
}
