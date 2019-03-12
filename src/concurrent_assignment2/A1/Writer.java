/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent_assignment2.A1;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author walterus
 */
public class Writer implements Runnable {
    CS_Queue q;
    
    Writer(CS_Queue q){
        this.q=q;
        new Thread(this, "Writer").start();
    }
    @Override
    public void run() {
        int i = 0;
        for(int j=0;j<10;j++){
            try {
                Thread.sleep((long) (Math.random()*500));
            } catch (InterruptedException ex) {
                Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
            }
            q.write(i++);
        }
    }
    
    
}
