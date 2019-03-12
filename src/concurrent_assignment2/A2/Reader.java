/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent_assignment2.A2;

/**
 *
 * @author onsur
 */
public class Reader implements Runnable {
    Signalled_Queue q;

    public Reader (Signalled_Queue q){
            this.q=q;
            new Thread(this,"Reader").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep((long) (Math.random()*500));
                q.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            q.read();  
        }
    }
}
