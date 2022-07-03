
package algorithms.mazeGenerators;
import java.io.Serializable;

public class Position implements Serializable {   //used to identify a cell in the maze matrix
    int x;
    int y;
    boolean visited;






    public Position(int x, int y, Maze m) {
        this.x = x;
        this.y = y;
        visited = false;
    }
    public Position (int row,int col)
    {
        //super();
        this.x=row;
        this.y= col;


    }
    public int getRowIndex()
    {
        return this.x;
    }
    public int getColumnIndex()
    {
        return this.y;
    }
    @Override
    public String toString() {
        return "{"+y+","+x+"}";
    }
    public boolean equals(Position other) {
        return (other.x == x && other.y == y)? true: false;
    }
    public void visitme()
    {
        visited=true;
    }
    public boolean isVisited()
    {
        return visited;
    }

}


