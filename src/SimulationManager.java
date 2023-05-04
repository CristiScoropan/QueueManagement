import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;

public class SimulationManager implements Runnable {
    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;

    private int minArrivalTime;

    private int maxArrivalTime;
    private int numberOfServers;
    private int numberOfClients;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks;

    public SimulationManager() {
        generatedTasks = new ArrayList<>();
        frame = new SimulationFrame();
    }

    private void generateNRandomTasks(int numberOfTasks, int minProcessingTime, int maxProcessingTime, int minArrivalTime, int maxArrivalTime) {
        Random random = new Random();
        for (int i = 0; i < numberOfTasks; i++) {
            int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            int serviceTime = random.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            Task task = new Task(i, arrivalTime, serviceTime);
            generatedTasks.add(task);
        }
        generatedTasks.sort(Comparator.comparingInt(Task::getArrivalTime));
    }

    @Override
    public void run() {

        while(!frame.isSimulationStarted());

        prepareSimulation();

        int currentTime = 0;
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        String filePath = "testlog.txt";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        while(currentTime < timeLimit){

            stringBuilder1.setLength(0);
            stringBuilder2.setLength(0);

            stringBuilder1.append("Waiting tasks: ");
            for(Task t: generatedTasks){
                if(currentTime == t.getArrivalTime()){
                    scheduler.dispatchTask(t);
                }else{
                    stringBuilder1.append(t).append(" ");
                }
            }
            int auxCurrentTime = currentTime;
            generatedTasks.removeIf(task -> task.getArrivalTime() == auxCurrentTime);

            System.out.println(currentTime);
            System.out.println(stringBuilder1.toString());
            bufferedWriter.write(currentTime+"\n"+stringBuilder1.toString()+"\n");

            int i = 1;
            for(Server server: scheduler.getServers()){
                stringBuilder2.setLength(0);
                stringBuilder2.append("Queue ").append(i).append(": ");
                for(Task task: server.getTasks()){
                    stringBuilder2.append(task.toString()).append(" ");
                }
                System.out.println(stringBuilder2.toString());
                bufferedWriter.write(stringBuilder2.toString()+"\n");
                i++;
            }
            bufferedWriter.write("\n");
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareSimulation() {
         numberOfClients = Integer.parseInt(frame.getTextFieldN().getText());
         numberOfServers = Integer.parseInt(frame.getTextFieldQ().getText());
         timeLimit = Integer.parseInt(frame.getTextFieldSimulationInterval().getText());
         minArrivalTime = Integer.parseInt(frame.getTextFieldMinArrivalTime().getText());
         maxArrivalTime = Integer.parseInt(frame.getTextFieldMaxArrivalTime().getText());
         minProcessingTime = Integer.parseInt(frame.getTextFieldMinServiceTime().getText());
         maxProcessingTime = Integer.parseInt(frame.getTextFieldMaxServiceTime().getText());
        generateNRandomTasks(numberOfClients, minProcessingTime, maxProcessingTime, minArrivalTime, maxArrivalTime);
        scheduler = new Scheduler(numberOfServers, maxProcessingTime);
    }
    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager();
        Thread t = new Thread(gen);
        t.start();
    }
}
