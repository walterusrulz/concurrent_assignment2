package concurrent_assignment2.A_intro;


public class Writer implements Runnable {
	Queue q;
	
	public Writer(Queue q){
		this.q=q;
		new Thread(this, "Writer").start();
	}
	
	public void run(){
		
		int i=0;
		for(int j=0;j<10;j++){
			try {
				Thread.sleep((long) (Math.random()*500));
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			q.write(i++);
		}	
	}
}