package View;

import Model.IModel;
import Model.MyModel;
import View.MyViewController;
import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
//import org.apache.log4j.BasicConfigurator;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyView.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Maze-Project");
        Scene scene = new Scene(root,800,800);

        primaryStage.setScene(scene);
        primaryStage.show();
        //BasicConfigurator.configure();
        IModel model = new MyModel();
        MyViewModel viewModel = new MyViewModel(model);
        MyViewController view = fxmlLoader.getController();
       view.setMymodel(viewModel);
     //  viewModel.addObserver(view);

    }

    public static void main(String[] args) {
        launch(args);
    }
}