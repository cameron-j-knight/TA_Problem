public class Student implements Runnable{
    private Line line;
    private int cycle;
    private int ctime;
    private int timesHelped;
    private String name;
    public Student(Line l,int n, int codingTime, String name){
        this.line = l;
        this.cycle = n;
        this.ctime = codingTime;
        this.timesHelped = 0;
        this.name = name;
    }

    public void run(){
        for (int x = 0;x<this.cycle;x++){
            System.out.println("Student " + name + " is coding");
            try {
                Thread.sleep(ctime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (System.out) {
                System.out.println("\tStudent " + name + " is going to get help");
            }
            if (line.request(this.name) == 0){
                synchronized (System.out) {
                    System.out.println("\tStudent " + name + " could not get help line is full");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            this.timesHelped ++;
        }
    }

    public int getTimesHelped(){
        return this.timesHelped;
    }
}