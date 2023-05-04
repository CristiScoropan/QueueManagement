import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private volatile boolean simulationOngoing;
    private AtomicInteger taskCount;

    public Server(int maxTasksPerServer){
        tasks = new LinkedBlockingQueue<>(maxTasksPerServer);
        taskCount = new AtomicInteger(0);
        simulationOngoing = true;
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        taskCount.addAndGet(newTask.getServiceTime());
    }

    public void run(){
        while(simulationOngoing){
            try {
                if(tasks.peek() != null) {
                    Thread.sleep(1000);
                    tasks.peek().setServiceTime(tasks.peek().getServiceTime() - 1);
                    taskCount.decrementAndGet();

                    if(tasks.peek().getServiceTime() == 0){
                        tasks.take();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public BlockingQueue<Task> getTasks(){
        return tasks;
    }

    public AtomicInteger getTaskCount() {
        return taskCount;
    }

    public void stop(){
        simulationOngoing = false;
    }
}
