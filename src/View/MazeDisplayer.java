package View;

import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.File;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

import javafx.scene.image.Image;

import javax.swing.*;

public class MazeDisplayer extends Canvas {
    private Maze maze;
    private int row_player =0;
    private int column_player =0;
    private int row_goal;
    private int col_goal;

    private boolean firstRun = true;
   // private Solution solution = null;
    private boolean reachGoal = false;
    private int charDirection = 0;
    StringProperty imageFileNameWall = new SimpleStringProperty();
    StringProperty imageFileNamePlayer = new SimpleStringProperty();
    StringProperty imageGoalIcon = new SimpleStringProperty();
    StringProperty imageSolution= new SimpleStringProperty();
    StringProperty imageFinish= new SimpleStringProperty();
    StringProperty imageTree= new SimpleStringProperty();
    StringProperty finalimage= new SimpleStringProperty();


    StringProperty imageBackground1 = new SimpleStringProperty();
    StringProperty imageBackground2 = new SimpleStringProperty();
    StringProperty imageBackground3= new SimpleStringProperty();
    StringProperty imageBackground4= new SimpleStringProperty();

    StringProperty imagePlayerUp = new SimpleStringProperty();
    StringProperty imagePlayerDown = new SimpleStringProperty();
    StringProperty imagePlayerLeft= new SimpleStringProperty();
    StringProperty imagePlayerRight= new SimpleStringProperty();
    Image end = new Image(getClass().getResourceAsStream(("tmona.jpg")));


    public void drawMaze(int[][] maze) {

        MyMazeGenerator gen = new MyMazeGenerator();
        this.maze=gen.generate(maze.length,maze[0].length);
        this.maze.setMatrix(maze);


        draw();

        }

    private void draw() {

        if (maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            int rows = maze.getRows();
            int cols = maze.getMatrix()[0].length;

            double cellHeight = canvasHeight / rows;
            double cellWidth = canvasWidth / cols;

            GraphicsContext graphicsContext = getGraphicsContext2D();
            //clear the canvas:


            graphicsContext.setFill(Color.WHITE);

            for (int k = 0; k < rows; k++) {
                for (int l = 0; l < cols; l++) {


                    double x = l * cellWidth;
                    double y = k * cellHeight;

                    graphicsContext.fillRect(x, y, cellWidth, cellHeight);
                }
            }

            graphicsContext.setFill(Color.BLACK);
           // Image start = new Image();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    if (maze.getMatrix()[i][j] == 1) {
                        //if it is a wall:
                        double x = j * cellWidth;
                        double y = i * cellHeight;
                        graphicsContext.fillRect(x, y, cellWidth, cellHeight);


                    }
                }

            }
            graphicsContext.setFill(Color.RED);
            graphicsContext.fillRect(0,0, cellWidth, cellHeight);

            //graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);
            graphicsContext.fillRect(maze.getEnd().getColumnIndex()*cellWidth,maze.getEnd().getRowIndex()*cellHeight, cellWidth, cellHeight);

        }
    }
    public void endMAZE()  {
        //InputStream stream = new FileInputStream("tmona.jpg");
        Image im = new Image("tmona.jpg");
        ImageView vie = new ImageView();
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        int rows = maze.getRows();
        int cols = maze.getMatrix()[0].length;

        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;

        GraphicsContext graphicsContext = getGraphicsContext2D();
        //clear the canvas:
        graphicsContext.clearRect(0, 0, canvasWidth, canvasHeight);
        //vie.
       // String m = "sound.mp3";
        graphicsContext.drawImage(im,10,10,im.getWidth(),im.getHeight());
        //AudioClip od = new AudioClip(this.getClass().getResource("sound.wav").toString());
        //String s = "sound.mp3";
        //Media sound= new Media(Paths.get(s).toUri().toString());
        //MediaPlayer mediap= new MediaPlayer(sound);
       // mediap.play();

        }


    public void MOVE(int x,int y)
    {
        if (x==maze.getEnd().getRowIndex()&&y==maze.getEnd().getColumnIndex())
        {
            endMAZE();
            return;
        }
        int rows = maze.getRows();
        int cols = maze.getMatrix()[0].length;

        double canvasHeight = getHeight();
        double canvasWidth = getWidth();

        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFill(Color.RED);
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        graphicsContext.fillRect(y*cellWidth, x*cellHeight, cellWidth, cellHeight);

    }
    public void CLEAR (int x,int y)
    {
        int rows = maze.getRows();
        int cols = maze.getMatrix()[0].length;
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFill(Color.WHITE);
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        graphicsContext.fillRect(y*cellWidth, x*cellHeight, cellWidth, cellHeight);
    }
    public int getRow_player() {return row_player; }
    public int getColumn_player() {
        return column_player;
    }


            private void drawPlayerAndGoal(double cellHeight, double cellWidth, GraphicsContext graphicsContext , int direction) {
            }


}
