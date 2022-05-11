package controller;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.Comparator;

public class ShortestQueueStrategy{

    public void addTask(ArrayList<Server> serverList, Task task) throws InterruptedException {
        //adds task to the server with the shortest waiting period
        serverList.sort(Comparator.comparing(Server::getWaitPer));
        Server server = serverList.get(0);
        serverList.remove(0);
        server.addTask(task);
        serverList.add(server);
        serverList.sort(Comparator.comparing(Server::getWaitPer));
    }
}
