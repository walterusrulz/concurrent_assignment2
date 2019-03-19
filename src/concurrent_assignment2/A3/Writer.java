/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent_assignment2.A3;


/**
 *
 * @author onsur
 */
public class Writer implements Runnable{
    Signalled_2Readers_Queue q;
	
    public Writer(Signalled_2Readers_Queue q){
            this.q=q;
            new Thread(this, "Writer").start();
    }

    @Override
    public void run() {
        int i=0;
        for (int j = 0; j < 10; j++) {
            try {
                Thread.sleep((long) (Math.random()*500));
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            q.write(i++);
        }
    }
}