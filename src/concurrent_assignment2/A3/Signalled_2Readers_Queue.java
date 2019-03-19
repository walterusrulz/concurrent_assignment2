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
	boolean turn = true;
	
	
	@Override
	synchronized public void read(int ID) {
            while(turn){
               try {
                    this.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Signalled_2Readers_Queue.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            
            if(ID == 1){
                System.out.println("Read "+ ID +": " + n);
                this.notify();
            }else if(ID == 2){
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Signalled_2Readers_Queue.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Read "+ ID +": " + n);
                turn=true;
            }
            
            
            
                      
            
            // TODO Auto-generated method stub
		
	}
	

	@Override
	synchronized public void write(int x) {
            if(!turn){
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Signalled_2Readers_Queue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
            n = x;
            System.out.println("Write: " + x);
            turn = false;
            this.notifyAll();
        }
	
	@Override
	public void read() {
		// no need to implement this
		
	}

	
	
	
}


