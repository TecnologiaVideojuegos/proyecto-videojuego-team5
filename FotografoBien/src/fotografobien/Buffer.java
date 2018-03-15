/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fotografobien;

import static java.lang.Thread.sleep;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Michael Lofer
 */
public class Buffer {
    private Object[] buf;
    private int capacidadMaxima=0, capacidadActual=0, in=0, out=0, totales=0;
    private Lock cerrojo = new ReentrantLock();
    private Condition lleno = cerrojo.newCondition();
    private Condition vacio = cerrojo.newCondition();
    
    public Buffer(int max){
        capacidadMaxima=max;
        buf = new Object[max];
    }
    
    public int getCapacidadActual(){
        return capacidadActual;
    }
    
    public int getTotales(){
        return totales;
    }
    
    public void llegaMeta(int numero) throws InterruptedException{
        cerrojo.lock();
        while(capacidadActual==capacidadMaxima){
            lleno.await();
        }
        try{
            buf[in]=numero;
            capacidadActual++;
            totales++;
            System.out.println("(in): "+in+", LLEGA A META - "+numero+" CAPACIDAD: "+capacidadActual+"-----TOTALES: "+totales);
            in=(in+1)%capacidadMaxima;
            if(capacidadActual==3){
                vacio.signalAll();
            }
            //vacio.signal();
        }catch(Exception e){}
        finally{
            cerrojo.unlock();
        }
    }
    
    public void fotografiar() throws InterruptedException{
        cerrojo.lock();
        while(capacidadActual!=capacidadMaxima){ //==0?
            vacio.await();
        }
        try{
            System.out.println("FOTO A CORREDORES: "+buf[0]+"-"+buf[1]+"-"+buf[2]);
            sleep(1000);
            for(int i=0; i<3; i++){
                System.out.println("(out): "+out+", SACANDO PARTICIPANTES");
                buf[out]=null;
                capacidadActual--;
                out=(out+1)%capacidadMaxima;
                lleno.signal();
            }
            
        }catch(Exception e){}
        finally{
            cerrojo.unlock();
        }
    }
}
