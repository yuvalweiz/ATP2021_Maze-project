package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient,Configurations conf) {
        try {
            ByteArrayOutputStream outBarr = new ByteArrayOutputStream();
            ObjectOutputStream Clientoutput = new ObjectOutputStream(outToClient);
            ObjectInputStream Clientinput = new ObjectInputStream(inFromClient);
            MyCompressorOutputStream comp = new MyCompressorOutputStream(outBarr);
            int[] rowscols = (int[])Clientinput.readObject();
            AMazeGenerator mazeGenerator = Configurations.Newmazegenerator();
            Maze maze = mazeGenerator.generate(rowscols[0] , rowscols[1]);
            comp.write(maze.toByteArray());
            Clientoutput.writeObject(outBarr.toByteArray());
            Clientoutput.flush();
            Clientoutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
