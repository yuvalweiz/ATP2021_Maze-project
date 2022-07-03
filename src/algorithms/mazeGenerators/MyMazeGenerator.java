package algorithms.mazeGenerators;

import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends  AMazeGenerator{
    @Override
    public Maze generate(int rows, int cols) {
        EmptyMazeGenerator visitsgen = new EmptyMazeGenerator();
        Maze visits=visitsgen.generate(rows, cols);
        Maze maze=visitsgen.generate(rows, cols);
        Stack<Position> stack = new Stack<Position>();
        int i,j;
        for (i=0;i<rows;i++)
        {
            for (j=0;j<cols;j++)
            {

                maze.matrix[i][j]=1;
            }
        }
        int x;
        int y;
        Random myRand = new Random();
        // r for row、c for column
        // Generate random r
        x = myRand.nextInt(rows);
        while (x % 2 == 0) {
            x = myRand.nextInt(rows);
        }
        // Generate random c
        y = myRand.nextInt(cols);
        while (y % 2 == 0) {
            y = myRand.nextInt(cols);
        }
        x=0;
        y=0;
        maze.matrix[x][y]=0;
        stack.push(new Position(x,y,maze));
        int total = (rows * cols)/4 ;
        int visited = 1;
        int random[] = new int[4];
        int totalrand;

        while (visited < total) {
            totalrand = 0;
            if (x > 1 && visits.matrix[x - 2][y] == 0)
                random[totalrand++] = 1;
            if (x < rows - 2 && visits.matrix[x + 2][y] == 0)
                random[totalrand++] = 2;
            if (y > 1 && visits.matrix[x][y - 2] == 0)
                random[totalrand++] = 3;
            if (y < cols - 2 && visits.matrix[x][y + 2] == 0)
                random[totalrand++] = 4;

            if (totalrand > 0) {
                switch(random[myRand.nextInt(totalrand)]) {
                    case 1: maze.matrix[x-2][y] = maze.matrix[x-1][y] = 0; //left

                        x -= 2;
                        visits.matrix[x][y]=1;
                        stack.push( new Position(x,y,maze));
                        visited++;
                        break;
                    case 2: maze.matrix[x+2][y] = maze.matrix[x+1][y] = 0; //right

                        x += 2;
                        visits.matrix[x][y]=1;
                        stack.push( new Position(x,y,maze));
                        visited++;
                        break;
                    case 3: maze.matrix[x][y-2] = maze.matrix[x][y-1] = 0;  //down

                        y -= 2;
                        visits.matrix[x][y]=1;
                        stack.push( new Position(x,y,maze));
                        visited++;
                        break;
                    case 4: maze.matrix[x][y+2] = maze.matrix[x][y+1] = 0;  //up

                        y += 2;
                        visits.matrix[x][y]=1;
                        stack.push( new Position(x,y,maze));
                        visited++;
                        visits.matrix[x][y]=1;
                        break;
                }
            }
            else {
                if (stack.isEmpty()==false)
                {
                    Position vert = stack.pop();

                    x = (int) vert.x ;
                    y = (int) vert.y ;

                }
                else
                {
                    break;
                }
            }
        }
        Position endpos = new Position(0,0,maze);


        for (i=0;i<rows;i++)
        {
            if (maze.matrix[i][cols-2]==0)
            {

                endpos.x=i;
                endpos.y=cols-1;
            }
        }
        maze.matrix[endpos.x][endpos.y]=0;

        maze.End=endpos;
        return maze;
    }
}
