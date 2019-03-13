
package concurrent_assignment2.B1;

/**
 * Search for the exact critical section,
 * and protect it with Dekker's algorithm.
 * */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;



public class Garden extends Applet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1108993515780510398L;
	Button goButton;
    Turnstile turnstile1;
    Turnstile turnstile2;
    Counter counter;
    NumberCanvas counterD;
    NumberCanvas turn1D;
    NumberCanvas turn2D;
//    Checkbox fixit;
    public final static int MAX = 20;


    public void init() {
        super.init();
	setBackground(Color.lightGray);
        // Set up Button 
        Panel p0= new Panel();
        p0.add(goButton = new Button(" Simulate "));
        goButton.setFont(new Font("Helvetica",Font.BOLD,24));
		
        goButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
           if (turnstile1==null && turnstile2==null)
              go();
          else if (!turnstile1.isAlive() && !turnstile2.isAlive())
              go();         
          }
        });
	Panel p=new Panel();
        Label lb=new Label("Ornamental Garden");
        lb.setFont(new Font("Arial",Font.BOLD,24));
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        p.add("South",lb);

        Panel p1= new Panel();
        p1.setLayout(new BorderLayout());
        
        p1.add("Center",p0);

        // Set up Display
        Panel p2 = new Panel();
        counterD = new NumberCanvas("Counter");
        turn1D   = new NumberCanvas("Turnstile 1",Color.ORANGE);
        turn2D   = new NumberCanvas("Turnstile 2",Color.ORANGE);
        counterD.setSize(150,100);
        turn1D.setSize(100,100);
        turn2D.setSize(100,100);
        p2.add(turn1D);
        p2.add(counterD);
        p2.add(turn2D);
        // Arrange Applet display
        setLayout(new BorderLayout());
        add("North",p);
        add("Center",p2);
        add("South",p1);
        
    }

    private void go() {
        
        counter = new Counter(counterD);
       
        turnstile1= new Turnstile(turn1D,counter,0);//added turn to constructor call
        turnstile2= new Turnstile(turn2D,counter,1);//added turn to constructor call
        turnstile1.start();
        turnstile2.start();
    }

}

class Counter {

    int value=0;
    NumberCanvas display;
    boolean[] entryR = {false, false};//will to enter CS
    volatile int turn = 0;//turn variable, by Dekker, when impass occurs

    Counter(NumberCanvas n) {
        display=n;
        display.setvalue(value);
    }

    void increment(int ID) {//need to know which Turnstile is accessing
        this.entryR[ID]= true;//set intention to enter CS by Turnstile.ID to true
        while(this.entryR[1-ID]){//if the other one is also signalling will to enter
            if(this.turn == 1-ID){//if it's the other one's turn
                this.entryR[ID] = false;//I lease my turn
                while(this.turn == 1 -ID){//While it's still the other one's turn
                    //do nothing
                }
                this.entryR[ID] = true;//Signal my will to enter CS
            }
        }//entering CS
        
        int temp = value;   //read[v]
        CC.ForceCC();
        value=temp+1;       //write[v+1]
        display.setvalue(value);
        
        //Exiting CS
        this.turn = 1-ID;
        this.entryR[ID] = false;
    }
}




class Turnstile extends Thread {
  NumberCanvas display;
  Counter people;
  int ID;//added new attribute ID (needed for synchro)

  Turnstile(NumberCanvas n,Counter c, int m)//constructor sets turn to input parameter
    { display = n; people = c; ID = m;}

  public void run() {
    try{
      display.setvalue(0);
      for (int i=1;i<=Garden.MAX;i++){
        Thread.sleep(500); //0.5 second
        display.setvalue(i);
        //pre
        people.increment(this.ID);//which Turnstile is accessing
        //post
      }
    } catch (InterruptedException e) {}
  }
}

class CC {
    public static void ForceCC() {
        if (Math.random()<0.5)
           try{Thread.sleep(200);} catch(InterruptedException e){};
            
    }
}
