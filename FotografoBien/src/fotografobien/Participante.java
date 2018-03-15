/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotografobien;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Lofer
 */
public class Participante extends Thread{
    private int numero;
    Buffer buf;
    
    public Participante(int numero, Buffer buf) {
        this.numero = numero;
        this.buf=buf;
    }
    
    public void run(){
        try{
            int tiempo=((int)(3000+2000*Math.random()));
            sleep(tiempo);
            buf.llegaMeta(numero);
        } catch (InterruptedException ie) {}
    }    
}
