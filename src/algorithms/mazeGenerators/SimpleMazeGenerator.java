package algorithms.mazeGenerators;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int cols) {
        Random rd = new Random();
        Maze newmaze=new Maze(rows,cols);
        int i,j;
        for (i=0;i<rows;i++)
        {
            for (j=0;j<cols;j++)
            {
                if (rd.nextBoolean()==true)
                {
                    newmaze.matrix[i][j]=0;
                }
                else
                {
                    newmaze.matrix[i][j]=1;
                }
            }
        }
        return newmaze;
    }
}
