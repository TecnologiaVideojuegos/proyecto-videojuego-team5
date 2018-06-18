/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Álvaro Zamorano
 */
public class Principal extends StateBasedGame {

    private AppGameContainer contenedor;

    public Principal() throws SlickException {
        super("Pantalla Principal");
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(1080, 720, false);
        contenedor.setShowFPS(false);
        contenedor.start();
    }

    /**
     * Inicializar la lista de estados(Pantallas,niveles) del juego
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new EstadoMenu());
        this.addState(new EstadoSeleccion());
        this.addState(new EstadoInfo());
        this.addState(new EstadoCamerino());
        this.addState(new EstadoPasillo1());
        this.addState(new EstadoCamerinoPas1());
        this.addState(new EstadoEscenarioReag1());
        this.addState(new EstadoPasillo2());
        this.addState(new EstadoEscenarioTrap2());
        this.addState(new EstadoPasillo3());
        this.addState(new EstadoEscenarioKPOP3());
        this.addState(new EstadoBatallaTrap());
        this.addState(new EstadoBatallaKPOP());
        this.addState(new EstadoBatallaReaggeton());
        this.addState(new EstadoVictoria());
    }

    public static void main(String[] args) {
        try {
            new Principal();
        } catch (SlickException slick) {
            slick.printStackTrace();
        }
    }

}
