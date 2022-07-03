package algorithms.mazeGenerators;

public interface IMazeGenerator {  //the interface of a class that generate mazes
     Maze generate(int rows,int cols);
     long measureAlgorithmTimeMillis(int rows,int cols);

}

