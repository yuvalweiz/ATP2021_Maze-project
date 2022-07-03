package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends  AMazeGenerator{
    @Override
    public Maze generate(int rows, int cols) {
        Maze newmaze=new Maze(rows,cols);
        int i,j;
        for (i=0;i<rows;i++)
        {
            for (j=0;j<cols;j++)
            {
                newmaze.matrix[i][j]=0;
            }
        }
        return newmaze;
    }
}
