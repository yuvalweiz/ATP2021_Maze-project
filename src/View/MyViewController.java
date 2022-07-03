package View;

import Model.IModel;
import Model.MyModel;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.MyMazeGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Logger;

public class MyViewController implements  IView {
    Image end = new Image(getClass().getResourceAsStream(("tmona.jpg")));
    ImageView myview=new ImageView();

    public void setMymodel(MyViewModel mymodel) {
        this.mymodel = mymodel;
    }

    public  MyViewModel mymodel;

    Logger logger = Logger.getLogger(String.valueOf(MyViewController.class));

    StringProperty update_player_position_row = new SimpleStringProperty();
    StringProperty update_player_position_col = new SimpleStringProperty();


    public MyMazeGenerator generator;
    public TextField textField_mazeRows;
    public TextField textField_mazeColumns;
    public MazeDisplayer mazeDisplayer;


    public void generateMaze(ActionEvent actionEvent) {

        if(generator == null)
            generator = new MyMazeGenerator();

        int rows = Integer.valueOf(textField_mazeRows.getText());
        int cols = Integer.valueOf(textField_mazeColumns.getText());
        IModel mod=mymodel.getModel();
        mod.setCOL(0);
        mod.setROW(0);

        mod.setMaze(generator.generate(rows,cols));
        int[][] maze = mod.getMaze().getMatrix();


        mazeDisplayer.drawMaze(maze);
    }
    public void set_update_player_position_row(String update_player_position_row) {
        this.update_player_position_row.set(update_player_position_row);
    }

    public void set_update_player_position_col(String update_player_position_col) {
        this.update_player_position_col.set(update_player_position_col);
    }
    public void solveMaze(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Solving maze...");
        alert.show();
    }




    public void UP() {
        mazeDisplayer.CLEAR(this.mymodel.getModel().getRowChar(),this.mymodel.getModel().getColChar());    ;

        this.mymodel.getModel().updateCharacterLocation(1);
        mazeDisplayer.MOVE(this.mymodel.getRowChar(),this.mymodel.getColChar());

    }
    public void DOWN() {
        mazeDisplayer.CLEAR(this.mymodel.getModel().getRowChar(),this.mymodel.getModel().getColChar());    ;
        this.mymodel.getModel().updateCharacterLocation(2);
        mazeDisplayer.MOVE(this.mymodel.getModel().getRowChar(),this.mymodel.getModel().getColChar());    }
    public void LEFT() {
        mazeDisplayer.CLEAR(this.mymodel.getModel().getRowChar(),this.mymodel.getModel().getColChar());    ;

        this.mymodel.getModel().updateCharacterLocation(3);
        mazeDisplayer.MOVE(this.mymodel.getRowChar(),this.mymodel.getColChar());    }
    public void RIGHT() {
        mazeDisplayer.CLEAR(this.mymodel.getModel().getRowChar(),this.mymodel.getModel().getColChar());    ;

        this.mymodel.getModel().updateCharacterLocation(4);
        mazeDisplayer.MOVE(this.mymodel.getRowChar(),this.mymodel.getColChar());    }


    public void keyPressed(KeyEvent keyEvent) throws FileNotFoundException {
        if (keyEvent.getKeyCode() ==8)
        {
                this.UP();


        }
        keyEvent.consume();

    }


    public void keyPressed(javafx.scene.input.KeyEvent event) {
        if (event.getText().equals("8")==true){
            this.UP();


        }
        event.consume();

    }
}
