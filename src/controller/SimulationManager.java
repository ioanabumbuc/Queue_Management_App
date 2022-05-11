package controller;

import model.*;
import view.View;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class SimulationManager implements Runnable{
    private final int nbTasks;
    private final String outFile;
    private final Timer timer;
    private final ShortestQueueStrategy shortestQueueStrategy;
    private final Scheduler scheduler;
    private final ArrayList<Task> taskList = new ArrayList<>();

    public SimulationManager(View view){
        //get simulation data from the user interface
        int nbServers = view.getNbQueues();
        int simulationTime = view.getSimulationTime();
        int maxArriveTime = view.getMaxArrivalTime();
        int minArriveTime = view.getMinArrivalTime();
        int maxServiceTime = view.getMaxServiceTime();
        int minServiceTime = view.getMinServiceTime();
        shortestQueueStrategy = new ShortestQueueStrategy();
        outFile = view.getOutFile();
        nbTasks = view.getNbClients();
        scheduler = new Scheduler(nbServers);
        timer = new Timer(simulationTime);
        for(int i = 1; i<= nbTasks; i++){
            Random r = new Random();
            int arriveTime = r.nextInt(maxArriveTime - minArriveTime)+ minArriveTime;
            int serviceTime = r.nextInt(maxServiceTime - minServiceTime)+ minServiceTime;
            taskList.add(new Task(i,arriveTime,serviceTime));
        }

    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    @Override
    public void run() {
        double avgWaitTime = 0;
        try{
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(outFile));
            Thread thread = new Thread(timer);
            thread.start();
            taskList.sort(Comparator.comparing(Task::getArriveTime));//sort the tasks according to their arrival time
            //to always take the tasks in order that they appear
            outputFile.write(timer.toString()+"\n"+"Waiting clients:"+this.taskListToString()+"\n"+scheduler.toString()+"\n");
            Thread.sleep(1000);
            avgWaitTime += scheduler.tasksInQueues();
            while(thread.isAlive()){
                Thread.sleep(1);
                while(!taskList.isEmpty()){
                    if(taskList.get(0).getArriveTime() > timer.getTime()){
                        break;//it's not time for the task yet
                    }
                    //call shortest queue strategy if the task's arrival time has come
                    shortestQueueStrategy.addTask(scheduler.getServers(),taskList.remove(0));
                }
                if(taskList.isEmpty() && scheduler.tasksInQueues()==0){
                    timer.stopTimer(); //no more clients waiting or in queues => stop simulation
                }
                outputFile.write(timer.toString()+"\n"+"Waiting clients:"+this.taskListToString()+"\n"+scheduler.toString()+"\n");
                avgWaitTime += scheduler.tasksInQueues();
                Thread.sleep(999);
            }
            avgWaitTime = avgWaitTime / nbTasks;
            outputFile.write("Average waiting time is "+avgWaitTime);
            outputFile.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String taskListToString(){
        StringBuilder s = new StringBuilder();
        if(!this.getTaskList().isEmpty()){
            for(Task task:this.getTaskList()){
                s.append(task.toString()).append(" ");
            }
        }
        return s.toString();
    }
}
