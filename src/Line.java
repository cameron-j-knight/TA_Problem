import java.util.ArrayList;

/**
 * Created by Mike on 2/6/19.
 */
public class Line {
    int help;
    int maxrequest;
    ArrayList<Thread> wait;
    Runnable helping;
    boolean ready;
    int helped;
    public Line(int helptime, int cycles, int students){
        this.help = helptime;
        this.maxrequest = cycles*students;
        this.wait = new ArrayList<>();
        this.helping = null;
        this.ready = false;
        this.helped = 0;
    }

    public synchronized boolean closed(){
        return this.helped == this.maxrequest;
    }

    public synchronized int request(String name){
        if (this.wait.size() == 3) {
            this.helped++;
            return 0;
        }
        this.wait.add(Thread.currentThread());
        while (this.wait.indexOf(Thread.currentThread()) != 0 || this.helping != null){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.wait.remove(0);
        this.helping = Thread.currentThread();
        while(!this.ready){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (System.out){
            System.out.println("\t\t\t" + name + "is being helped");
        }
        try {
            Thread.sleep(help);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.helping = null;
        notifyAll();
        return 1;
    }

    public void ready() {
        ready = true;
        notifyAll();
        while (wait.size() != 0 || this.helping != null){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ready = false;
        notifyAll();
    }
}
