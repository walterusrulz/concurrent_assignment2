package concurrent_assignment2.A1;

import concurrent_assignment2.A_intro.Queue;

/**Use condition synchronization by means of busy wait.
 * 
 * What kind of variable do you need to implement busy
 * wait synchronization?
 * 
 * Set a meaningful name for such variable.
 * */
 
class CS_Queue implements Queue{
	int n=0;
	volatile boolean isLoaded = false;
        
	@Override
	public void read() {
		while(!isLoaded){
                    //do nothing
                } 
                System.out.println("Reading "+this.n);//printing values
                isLoaded = false;
		
	}

	@Override
	public void write(int x) {
		while(isLoaded){
                    //do nothing
                }
                this.n = x;//updating value...
	        System.out.println("Writing "+this.n);//printing values
                isLoaded = true;
	}

	@Override
	public void read(int ID) {
		// no need to implement this
		
	}
	
	
	
	
}


