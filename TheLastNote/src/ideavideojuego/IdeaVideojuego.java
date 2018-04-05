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
public class IdeaVideojuego {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Ataque TrumpetGuy = new Ataque(70, 5, "TrumpetGuy", "Usará la mítica canción de Eurovisión para causar un daño bestial!!");
        Ataque Guitarrazo = new Ataque(30, 20, "Guitarrazo", "Lanzará su guitarra para causar un daño leve");
        
        Personaje AlfredoMercurio = new Personaje(100, "Alfredo Mercurio");
        AlfredoMercurio.getAtaques().add(TrumpetGuy);
        AlfredoMercurio.getAtaques().add(Guitarrazo);
        
        System.out.println(AlfredoMercurio);
    }
    
}
