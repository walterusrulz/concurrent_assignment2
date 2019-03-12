package concurrent_assignment2.A3;

import concurrent_assignment2.A_intro.Queue;

public class ReaderIdentified implements Runnable{
	Queue q;
	int ID;
	
	public ReaderIdentified (Queue q, int id){
		this.q=q;
		ID=id;
		new Thread(this,"Reader").start();
	}
	
	public void run(){
		for(int i=0;i<10;i++){
			try {
				Thread.sleep((long) (Math.random()*500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			q.read(ID);
		}	
	}
}

