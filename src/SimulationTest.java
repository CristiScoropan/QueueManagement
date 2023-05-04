import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationTest {
    private static final int N = 4;
    private static final int Q = 2;
    private static final int MAX_SIMULATION_TIME = 20;

    private static final int MIN_ARRIVAL_TIME = 2;
    private static final int MAX_ARRIVAL_TIME = 5;
    private static final int MIN_SERVICE_TIME = 2;
    private static final int MAX_SERVICE_TIME = 4;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        Scheduler scheduler = new Scheduler(2, 120);
        List<Task> tasks = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            int arrivalTime = random.nextInt(MAX_ARRIVAL_TIME - MIN_ARRIVAL_TIME + 1) + MIN_ARRIVAL_TIME;
            int serviceTime = random.nextInt(MAX_SERVICE_TIME - MIN_SERVICE_TIME + 1) + MIN_SERVICE_TIME;
            Task task = new Task(i, arrivalTime, serviceTime);
            tasks.add(task);
        }

        int currentTime = 0;
        while(currentTime < MAX_SIMULATION_TIME){
            StringBuilder stringBuilder1 = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();

            stringBuilder1.setLength(0);
            stringBuilder2.setLength(0);

            stringBuilder1.append("Waiting tasks: ");
            for(Task t: tasks){
                if(currentTime == t.getArrivalTime()){
                    scheduler.dispatchTask(t);
                }else{
                    stringBuilder1.append(tasks);
                }
            }
            int auxCurrentTime = currentTime;
            tasks.removeIf(task -> task.getArrivalTime() == auxCurrentTime);

            System.out.println(currentTime);
            System.out.println(stringBuilder1.toString());
            int i = 1;
            for(Server server: scheduler.getServers()){
                stringBuilder2.setLength(0);
                stringBuilder2.append("Queue ").append(i).append(": ");
                for(Task task: server.getTasks()){
                    stringBuilder2.append(task.toString()).append(" ");
                }
                System.out.println(stringBuilder2.toString());
                i++;
            }

            currentTime++;
            Thread.sleep(1000);
        }
    }
}
