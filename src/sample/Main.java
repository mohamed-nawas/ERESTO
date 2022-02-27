package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //primaryStage.setX(dimension.getWidth()/2 - primaryStage.getWidth()/2);
        //primaryStage.setY(dimension.getHeight()/2 - primaryStage.getHeight()/2);
        primaryStage.setX(0);
        primaryStage.setY(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
