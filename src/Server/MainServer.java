package Server;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        server.start();
    }
}
