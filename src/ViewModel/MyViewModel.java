package ViewModel;

import Model.IModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

    public class MyViewModel extends Observable implements Observer {

        private IModel model;
        private Maze maze;

        private int rowChar;
        private int colChar;
        private int rowGoal;
        private int colGoal;
        private boolean isReachesToGoal;

        public IModel getModel() {
            return model;
        }

        public MyViewModel(IModel model) {
            this.model = model;
            this.model.assignObserver(this);
            this.maze = null;
        }

        public void refreshStrategies() {
            model.refreshStrategies();
        }

        @Override
        public void update(Observable o, Object arg) {
            if (arg == "loaded"){
                rowChar = model.getRowChar();
                colChar = model.getColChar();
                rowGoal = model.getRowGoal();
                colGoal = model.getColGoal();
                isReachesToGoal= model.getReachGoal();
                maze = model.getMaze();
                maze.setStart(new Position(rowChar,colChar));
                maze.setEnd(new Position(rowGoal,colGoal));
                setChanged();
                notifyObservers("loaded");
            }
            else if (o instanceof IModel) {
                if (maze == null) { //Generate maze
                    this.maze = model.getMaze();
                } else {
                    Maze maze = model.getMaze();
                    if (maze == this.maze) { // Move Character
                        int rowChar = model.getRowChar();
                        int colChar = model.getColChar();
                        boolean isreach= model.getReachGoal();
                        if (this.colChar == colChar && this.rowChar == rowChar){ // Solve Maze
                            getSolution();
                        }
                        else //Update location
                        {
                            this.rowChar = rowChar;
                            this.colChar = colChar;
                            this.isReachesToGoal=isreach;
                        }
                    }
                    else{
                        this.maze = maze;
                        rowChar = model.getRowChar();
                        colChar = model.getColChar();
                        isReachesToGoal=model.getReachGoal();
                    }
                }
                setChanged();
                notifyObservers(arg);
            }
        }

        public void generateMaze(int row, int col) {
            this.model.generateMaze(row, col);
        }

        public void moveCharacter(KeyEvent keyEvent) {
            int direction = switch (keyEvent.getCode()) {
                case NUMPAD8 -> 1; //UP
                case UP -> 1;
                case NUMPAD2 -> 2; //Down
                case DOWN -> 2;
                case NUMPAD4 -> 3; //Left
                case LEFT ->3;
                case NUMPAD6 -> 4; //Right
                case RIGHT ->4 ;
                case NUMPAD9  -> 5; //UP-RIGHT
                case NUMPAD7 -> 6; //UP-LEFT
                case NUMPAD3-> 7; //DOWN-RIGHT
                case NUMPAD1 -> 8; //DOWN-LEFT
                default -> -1;
            };
            model.updateCharacterLocation(direction);
        }

        public void moveCharacter(KeyCode keyCode) {
            int direction = switch (keyCode) {
                case NUMPAD8 -> 1; //UP
                case UP -> 1;
                case NUMPAD2 -> 2; //Down
                case DOWN -> 2;
                case NUMPAD4 -> 3; //Left
                case LEFT ->3;
                case NUMPAD6 -> 4; //Right
                case RIGHT ->4 ;
                case NUMPAD9  -> 5; //UP-RIGHT
                case NUMPAD7 -> 6; //UP-LEFT
                case NUMPAD3-> 7; //DOWN-RIGHT
                case NUMPAD1 -> 8; //DOWN-LEFT
                default -> -1;
            };
            model.updateCharacterLocation(direction);
        }

        public int getRowChar() {
            return rowChar;
        }

        public int getColChar() {
            return colChar;
        }

        public void setRowChar(int rowChar) {
            this.rowChar = rowChar;
        }

        public void setColChar(int colChar) {
            this.colChar = colChar;
        }

        public int getRowGoal() {
            return rowGoal;
        }

        public int getColGoal() {
            return colGoal;
        }

        public Maze getMaze() {
            return maze;
        }



        public Solution getSolution(){
            return model.getSolution();
        }

        public boolean reachGoal(){
            return model.getReachGoal();
        }






    }

