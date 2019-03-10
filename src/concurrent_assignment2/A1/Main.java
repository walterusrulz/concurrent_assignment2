package concurrent_assignment2.A1;

import concurrent_assignment2.A_intro.Reader;
import concurrent_assignment2.A_intro.Writer;



public class Main {

	public static void main(String args[]){
		CS_Queue queue;
		
		queue=new CS_Queue();
		
		Thread iWrite = new Thread(new Writer(queue));
                Thread iRead = new Thread(new Reader(queue));
		
                iWrite.start();
                iRead.start();
	

		
		
		
		
	}
}
