import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            primaryStage.show();
        } catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        launch(args);
    }
}
