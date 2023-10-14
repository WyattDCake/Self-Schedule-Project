import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("sceneDesign.css").toExternalForm();
            scene.getStylesheets().add(css);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Schedule");
            Image icon = new Image("schedule.png");
            primaryStage.getIcons().add(icon);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event->{
                event.consume();
                logout(primaryStage);
            });

        } catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        launch(args);
    }
    public void logout(Stage stage){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Make Sure to Save");
        alert.setHeaderText("Exitting via 'X' will not save data \nGo to quit on main page");
        alert.setContentText("Are You fine without saving?: ");
        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
    }
}
