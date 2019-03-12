/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent_assignment2.A1;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author walterus
 */
public class Reader implements Runnable{
    CS_Queue q;
    
    Reader (CS_Queue q){
        this.q = q;
        new Thread(this, "Reader").start();
    }
    @Override
    public void run() {
        for(int i=0; i<10;i++){
            try {
                Thread.sleep((long) (Math.random()*500));
            } catch (InterruptedException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
            q.read();
        }
    }
}
