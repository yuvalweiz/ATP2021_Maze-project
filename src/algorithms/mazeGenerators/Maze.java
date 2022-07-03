package algorithms.mazeGenerators;
import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Maze implements Serializable {    //general maze
    int rows;
    int cols;
    int[][] matrix;
    Position Start;
    Position End;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new int[rows][cols];
        Start = new Position(0, 0, this);
        End = null;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Position getStart() {
        return Start;
    }

    public Position getEnd() {
        return End;
    }

    public Maze(byte  [] b)
    {
        int rowsnum=0;
        int colsnum=0;
        int startX=0;
        int startY=0;
        int endX=0;
        int endY=0;
        int [] intarr=new int[6];
        intarr[0]=rowsnum;
        intarr[1]=colsnum;
        intarr[2]=startX;
        intarr[3]=startY;
        intarr[4]=endX;
        intarr[5]=endY;
        int i,j;
        int finalindex=0;
        int index=0;
        int sum=0;
        byte curr=b[index];
        for(i=0;i<6 && index<b.length;i++)
        {

            while (curr!=-1 )
            {
                finalindex=index;
                sum+=curr ;
                index++;

                    if (index<b.length) {
                        curr = b[index];
                    }
            }
            intarr[i]=sum ;
            sum=0;
            index++;
                if (index<b.length) {
                    curr = b[index];
                }


        }
        index++;
        rowsnum=intarr[0];
        colsnum=intarr[1];
        startX=intarr[2];
        startY=intarr[3];
        endX=intarr[4];
        endY=intarr[5];
        this.rows=rowsnum ;
        this.cols=colsnum;
        this.Start=new Position(intarr[2],intarr[3],this);
        this.End=new Position(intarr[4],intarr[5],this);
        int [][]mat = new int[rowsnum][colsnum];
        index--;
        for (i=0;i<rowsnum;i++)
        {
            for (j=0;j<colsnum;j++)
            {
                if (index>b.length-1)
                {
                    break;
                }
                mat[i][j]=b[index] & 0xFF;
                index++;
            }
        }


        this.matrix=mat;
    }

    public ArrayList<Byte> InTtoByte(ArrayList<Byte> mazeBytesInfo, int num) {
        if(num > 127){
            int i=0;
            while( num>127){
                mazeBytesInfo.add( (byte)127 );

                num = num -127;
            }
            mazeBytesInfo.add( (byte)num );
        }
        else{
            mazeBytesInfo.add( (byte)num );

        }

        return mazeBytesInfo;
    }



    public Position getStartPosition() {
        return Start;
    }

    public Position getGoalPosition() {
        return End;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setStart(Position start) {
        Start = start;
    }

    public void setEnd(Position end) {
        End = end;
    }

    public void print() {    //prints for Y growing when 'walking' down and X growing when 'walking' right
        if (this.End == null) {
            return;
        }
        char[][] chararray = new char[this.rows][this.cols];

        int i, j;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < cols; j++) {
                if (this.matrix[i][j] == 0) {
                    chararray[i][j] = '0';
                } else {
                    chararray[i][j] = '1';
                }
            }
        }
        chararray[this.Start.x][this.Start.y] = 'S';
        chararray[this.End.x][this.End.y] = 'E';
        for (i = 0; i < rows; i++) {
            for (j = 0; j < cols; j++) {
                System.out.print(chararray[i][j] + "\t");
            }
            System.out.println();

        }

    }

    public int[][] GetMatrix() {
        return this.matrix;
    }

    public int GetRows() {
        return this.rows;
    }

    public int GetCols()
    {
        return this.cols;
    }

    public byte[]toByteArray()
    {
        ArrayList<Byte> bytelist= new  ArrayList<Byte>();
        bytelist = InTtoByte(bytelist, rows);
        bytelist.add((byte)-1);
        bytelist = InTtoByte(bytelist, cols);
        bytelist.add((byte)-1);

        bytelist = InTtoByte(bytelist, Start.getRowIndex() );
        bytelist.add((byte)-1);

        bytelist = InTtoByte(bytelist, Start.getColumnIndex() );
        bytelist.add((byte)-1);

        bytelist = InTtoByte(bytelist, End.getRowIndex() );
        bytelist.add((byte)-1);

        bytelist = InTtoByte(bytelist, End.getColumnIndex());
        bytelist.add((byte)-1);



        int i,j;

        for(i=0;i<rows;i++)
        {
            for (j=0;j<this.cols;j++)
            {
                bytelist.add((byte)this.matrix[i][j]);
            }
        }
        byte[] bytearry = new byte[bytelist.size()];
        for (j=0;j<bytelist.size();j++)
        {
            bytearry[j]=bytelist.get(j);
        }


        return bytearry;

    }



}
