package model;

import java.util.concurrent.LinkedBlockingQueue;

public class Server implements Runnable {
    private final LinkedBlockingQueue<Task> tasks;
    private int waitPer;
    private boolean running;
    private final int queueID;

    public Server(int queueID) {
        this.tasks = new LinkedBlockingQueue<>();
        this.waitPer = 0;
        this.running = false;
        this.queueID = queueID;
    }

    public int getWaitPer() {
        return waitPer;
    }

    public int getQueueID(){
        return queueID;
    }

    public LinkedBlockingQueue<Task> getTasks(){
        return this.tasks;
    }

    public void addTask(Task task) throws InterruptedException {
        //adds a task to the current server and
        //adds its service time to the overall waiting period
        this.tasks.put(task);
        this.waitPer += task.getServiceTime();
        if(tasks.size()==1){
            //start running when a task is in queue
            startRun();
        }
    }

    public void startRun(){
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(running){
            //solve tasks one by one, decrementing the service time
            // each time a second passes
            try {
                Thread.sleep(998);
                Task task = tasks.peek();
                if( task != null) {
                    task.decrementServiceTime();
                    this.waitPer--;
                    if(task.getServiceTime()<=0){
                        tasks.take();
                    }
                Thread.sleep(2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(tasks.isEmpty()){
                //stop running when the queue is empty
                running = false;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("Queue " + this.getQueueID() + ":");
        if(this.getTasks().isEmpty()){
            s.append("closed");
        }else{
            for(Task task:this.getTasks()){
                s.append(task.toString());
            }
        }
        return s.toString();
    }


}

