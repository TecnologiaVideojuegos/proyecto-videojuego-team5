/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotografobien;

/**
 *
 * @author Michael Lofer
 */
public class Main {
    public static void main(String[] args){
        Buffer buf = new Buffer(3);
        Fotografo fotografo = new Fotografo(buf);
        for(int i=0; i<30; i++){
            Participante p = new Participante(i+1, buf);
            p.start();
        }
        fotografo.start();
    }
}
