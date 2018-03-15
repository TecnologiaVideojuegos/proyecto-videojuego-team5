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
public class Fotografo extends Thread{
    Buffer buf;

    public Fotografo(Buffer buf) {
        this.buf = buf;
    }

    public void run(){
        while(true && buf.getTotales()!=30){
            try{
                sleep(200);
                buf.fotografiar();
            } catch (InterruptedException ex) {}
        }
    }
}
