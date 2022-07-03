package Model;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

import java.io.File;
import java.net.UnknownHostException;
import java.util.Observer;

public interface IModel {
    public void generateMaze(int rows, int cols);
    public Maze getMaze();
    public void updateCharacterLocation(int direction);
    public boolean getReachGoal();
    public void assignObserver(Observer observer);
    public int getRowChar();
    public int getColChar();
    public int getRowGoal();
    public int getColGoal();
    //public Solution getSolution();
    public void restartServers();
    public void refreshStrategies();
    public void setMaze(Maze maze) ;
    public void setCOL(int num) ;
    public void setROW(int num) ;


    Solution getSolution();
}

