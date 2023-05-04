import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer){
        servers = new ArrayList<>();
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        strategy = new ConcreteStrategyTime();
        for(int i = 0; i < maxNoServers; i++) {
            Server server = new Server(maxTasksPerServer);
            Thread thread = new Thread(server);
            thread.start();
            servers.add(server);
        }
    }

    public void setSelectionStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }

    public List<Server> getServers(){
        return servers;
    }
}
