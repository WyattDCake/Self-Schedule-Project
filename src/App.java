import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        try{
        root = FXMLLoader.load(getClass().getResource("schedule.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        launch(args);
        //Scanner scanner = new Scanner(System.in);
        //UserInterface UI = new UserInterface(scanner);
        //UI.start();
    }
}
