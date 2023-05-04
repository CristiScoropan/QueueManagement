import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        Server serverWithShortestTime = servers.get(0);
        int shortestTime = serverWithShortestTime.getTaskCount().get();

        for (Server server : servers) {
            int waitingTime = server.getTaskCount().get();
            if (waitingTime < shortestTime) {
                serverWithShortestTime = server;
                shortestTime = waitingTime;
            }
        }

        servers.get(servers.indexOf(serverWithShortestTime)).addTask(t);
    }
}
