package algorithms.search;
import java.io.Serializable;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState implements Serializable{
     final Position currentpos;
    public MazeState (Position currentpos,AState camefrom,int cost)
    {
        super(camefrom,cost);
        this.currentpos=currentpos;

    }
    public MazeState (int row,int col)
    {
        super();
        this.currentpos=new Position(row,col);

    }

    @Override
    public String toString() {
        return currentpos.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this.toString().equals(obj.toString()) ) return true;

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MazeState state1 = (MazeState) obj;
        if (currentpos==null)
        {
            return currentpos.equals(state1.currentpos);
        }
        else
        {
            return state1.currentpos == null;
        }

    }
}
