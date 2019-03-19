package concurrent_assignment2.A3;

import concurrent_assignment2.A_intro.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import concurrent_assignment2.A_intro.Queue;
/**
 * Use the synchronized keyword and signals.
 * 
 * You cannot decide whose's turn it is based on 
 * a 2 states variables, so know use a variable which
 * allows for more values (you need 3 states, that is, 3 turns).
 * 
 * Output should be: writer prints, both readers read, and so on...
 *
 */
 
class Signalled_2Readers_Queue implements Queue{
	int n=0;
	int turn = 0;
	
	
	@Override
	synchronized public void read(int ID) {           
            while(turn != ID){
               try {
                    this.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Signalled_2Readers_Queue.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            
            System.out.println("Read "+ ID +": " + n);
            
            // if (ID == 1) turn = 2;
            // else if (ID == 2) turn = 0;
            
            turn = (ID + 1) % 3;
            
            this.notifyAll();
        }
            
	
	

	@Override
	synchronized public void write(int x) {
            while(turn != 0){
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Signalled_2Readers_Queue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
            n = x;
            System.out.println("Write: " + x);
            turn = 1;
            this.notifyAll();
        }
	
	@Override
	public void read() {
		// no need to implement this
		
	}	
	
	
}


