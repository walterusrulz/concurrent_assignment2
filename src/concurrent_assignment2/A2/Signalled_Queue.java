package concurrent_assignment2.A2;

import concurrent_assignment2.A_intro.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use the synchronized keyword and signals so that
 * you do not need to busy wait.
 * But of course you still need your variable to know
 * whose's turn it is.
 *
 */
 
class Signalled_Queue implements Queue{
	int n=0;
        boolean canWrite = true;
        
	@Override
	synchronized public void read() {
            if(canWrite) {
                //Waits turn
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Signalled_Queue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            System.out.println("Read: " + n);
            canWrite = true;
            
            this.notify();
	}

	@Override
	synchronized public void write(int x) {
            if(!canWrite){
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Signalled_Queue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            n = x;
            System.out.println("Write: " + x);
            canWrite = false;
            this.notify();
            
            
	}

	@Override
	public void read(int ID) {
		// no need to implement this
		
	}
	
	
}


