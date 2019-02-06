/**
 * Created by Mike on 2/6/19.
 * Author: Cameron Knight 
 * Email: cjk1144@rit.edu
 */
public class TA implements Runnable{
	private Line line;
	private int helpTime;
	private int gamingTime;
	private String state;
	public TA( Line line, int helpTime, int gamingTime){
		this.line = line;
		this.helpTime = helpTime;
		this.gamingTime = gamingTime;
		this.state = "G";
	}

	public void run() {
		while(!line.closed()){
			this.state = "G";
            try {
                Thread.sleep(gamingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.state = "T";
			synchronized(System.out){
				System.out.println("\t\tTA Earl is thinking...");
			}
			notifyAll();
			line.ready();
		}
	}

}
