package Server;

import Server.IServerStrategy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.net.SocketTimeoutException;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;

    public void setStrategy(IServerStrategy strategy) {
        this.strategy = strategy;
    }

    private boolean stop;
    public Configurations Conf;
    private ThreadPoolExecutor ThreadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.ThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.Conf = Configurations.getinstance();

    }
    public void start()
    {
        ThreadPool.setCorePoolSize(Configurations.getThredsNumber());
        new Thread(this::sstart).start();
    }
    private void handleClient(Socket cs)
    {
        try {
            strategy.applyStrategy(cs.getInputStream(),cs.getOutputStream(),this.Conf);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void sstart(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());
                    ThreadPool.execute(()->handleClient(clientSocket));
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ThreadPool.shutdown();
                    try{
                        clientSocket.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                } catch (SocketTimeoutException e){

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        stop = true;
    }
}
