package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.io.Serializable;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable, Serializable{
    Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    public ArrayList<AState> getAllPossibleStates(AState a)     //returns all the states that are reachable
    {
        ArrayList<AState> statelist=new ArrayList<AState>();
        MazeState m= (MazeState)a;
        Position pos=m.currentpos;
        int x=pos.getRowIndex();
        int y= pos.getColumnIndex();
        int cols=maze.GetCols();
        int rows=maze.GetRows();
        if (x > 0 && maze.GetMatrix()[x-1][y]!=1 )
            statelist.add(new MazeState(new Position(x-1,y,maze),m,m.cost+10));

        if (x < rows - 1 && maze.GetMatrix()[x+1][y]!=1)
            statelist.add(new MazeState(new Position(x+1,y,maze),m,m.cost+10));

        if (y > 0 && maze.GetMatrix()[x][y-1]!=1)
            statelist.add(new MazeState(new Position(x,y-1,maze),m,m.cost+10));

        if (y < cols - 1 && maze.GetMatrix()[x][y+1]!=1)
            statelist.add(new MazeState(new Position(x,y+1,maze),m,m.cost+10));

        if (x > 0 && y>0 && maze.GetMatrix()[x-1][y-1]!=1 && (maze.GetMatrix()[x][y-1]!=1|| maze.GetMatrix()[x-1][y]!=1) )
            statelist.add(new MazeState(new Position(x-1,y-1,maze),m,m.cost+15));

        if (x <rows-1  && y<cols-1 && maze.GetMatrix()[x+1][y+1]!=1 && (maze.GetMatrix()[x][y+1]!=1|| maze.GetMatrix()[x+1][y]!=1) )
            statelist.add(new MazeState(new Position(x+1,y+1,maze),m,m.cost+15));

        if (x <rows-1  && y>0 && maze.GetMatrix()[x+1][y-1]!=1 && (maze.GetMatrix()[x+1][y]!=1|| maze.GetMatrix()[x][y-1]!=1))
            statelist.add(new MazeState(new Position(x+1,y-1,maze),m,m.cost+15));

        if (x > 0  && y<cols-1 && maze.GetMatrix()[x-1][y+1]!=1 && (maze.GetMatrix()[x][y+1]!=1|| maze.GetMatrix()[x-1][y]!=1))
            statelist.add(new MazeState(new Position(x-1,y+1,maze),m,m.cost+15));


        return  statelist;

    }
    @Override
    public MazeState getStartState() {
        return new MazeState(maze.getStartPosition(),null,0);
    }

    @Override
    public MazeState getGoalState() {
        return new MazeState(maze.getGoalPosition(),null,0);
    }
}
