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

        
	@Override
	synchronized public void read() {
            
            System.out.println("Read: " + n);
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Signalled_Queue.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	@Override
	synchronized public void write(int x) {
            System.out.println("Write: " + x);
            n = x;
            this.notifyAll();
	}

	@Override
	public void read(int ID) {
		// no need to implement this
		
	}
	
	
}


