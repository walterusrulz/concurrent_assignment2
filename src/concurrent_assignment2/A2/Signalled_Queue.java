package concurrent_assignment2.A2;

import concurrent_assignment2.A_intro.Queue;

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
	public void read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read(int ID) {
		// no need to implement this
		
	}
	
	
}


