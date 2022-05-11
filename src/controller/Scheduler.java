package controller;

import model.Server;
import java.util.ArrayList;
import java.util.Comparator;

public class Scheduler {
    private final ArrayList<Server> servers = new ArrayList<>();

    public Scheduler(int nbServers){
        for(int i=0;i<nbServers;i++){
            servers.add(new Server(i));
        }
    }

    public ArrayList<Server> getServers(){
        return servers;
    }

    public int tasksInQueues(){
        int nbTasks = 0;
        for(Server server:servers){
            nbTasks+=server.getTasks().size();
        }
        return nbTasks;
    }

    public void sortByQueueID(){
        servers.sort(Comparator.comparing(Server::getQueueID));
    }

    public String toString(){
        StringBuilder s= new StringBuilder();
        if(!this.getServers().isEmpty()){
            this.sortByQueueID();
            for(Server server:this.getServers()){
                s.append(server.toString()).append("\n");
            }
        }
        return s.toString();
    }
}
