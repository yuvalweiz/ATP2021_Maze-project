package Server;

import java.io.InputStream;
import java.io.OutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.io.*;


public class ServerStrategySolveSearchProblem implements  IServerStrategy{
    private String tempDirectoryPath = System.getProperty("/tmp"); //java.io.tmpdir
    private Lock lockk = new ReentrantLock(true);
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient,Configurations conf) {
        try {
            Solution sol;
            ObjectInputStream Clientinput = new ObjectInputStream(inFromClient);
            ObjectOutputStream Clientoutput = new ObjectOutputStream(outToClient);

            Maze maze = (Maze) Clientinput.readObject();
            String path = tempDirectoryPath+"maze"+maze.toString();
            lockk.lock();
            File f = new File(path);
            if (!f.exists()) {
                lockk.unlock();
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                ASearchingAlgorithm algo = Configurations.Newsearchalgo();
                sol = algo.solve(searchableMaze);
                lockk.lock();
                final boolean newFile = f.createNewFile();
                FileOutputStream fileoutput = new FileOutputStream(path);
                fileoutput.flush();
                ObjectOutputStream out = new ObjectOutputStream(fileoutput);
                out.flush();
                out.writeObject(sol);
                out.flush();
            } else {
                FileInputStream fileinput = new FileInputStream(path);
                ObjectInputStream oin = new ObjectInputStream(fileinput);
                sol = (Solution) oin.readObject();
                fileinput.close();
                oin.close();
            }
            lockk.unlock();
            Clientoutput.writeObject(sol);
            Clientoutput.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private boolean isFolderExist(String path){
        return Files.exists(Paths.get(path));
    }
}
