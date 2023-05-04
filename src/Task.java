public class Task {
    private int arrivalTime;
    private int serviceTime;
    private int id;
    public Task(int id, int arrivalTime, int serviceTime){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "(" +
                 id +
                ", " + arrivalTime +
                ", " + serviceTime +
                ')';
    }
}
