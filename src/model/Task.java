package model;

public class Task {

    private int ID;
    private int arriveTime;
    private int serviceTime;

    public Task(int ID, int arriveTime, int serviceTime) {
        this.ID = ID;
        this.arriveTime = arriveTime;
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getID() {
        return ID;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public void decrementServiceTime(){
        this.serviceTime--;
    }

    @Override
    public String toString(){
        return "("+this.getID()+","+this.getArriveTime()+","+this.getServiceTime()+");";
    }
}

