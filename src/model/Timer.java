package model;

public class Timer implements Runnable{
    private int time;
    private int simulationTime;

    public Timer(int simulationTime){
        this.time = 0;
        this.simulationTime = simulationTime;
    }

    public int getTime(){
        return time;
    }

    public void stopTimer(){
        simulationTime = -1;
    }

    @Override
    public void run() {
        while(time<=simulationTime){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            time++;
        }
    }


    @Override
    public String toString() {
        return "Time "+time;
    }
}
