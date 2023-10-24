import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SchoolSceneController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private InputOutput inputOutput;
    private ArrayList<Event> schedule;
    private String css = this.getClass().getResource("sceneDesign.css").toExternalForm();


    @FXML
    private DatePicker classDate = new DatePicker();
    @FXML
    private TextField classID = new TextField();
    @FXML
    private TextField classAssignment = new TextField();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //input for schedule
        inputOutput = new InputOutput();
        schedule = inputOutput.inputSchedule();
        Collections.sort(schedule);
    }

    @FXML
    public void addSchoolWork(ActionEvent event){
        String id = classID.getText();
        String assignment = classAssignment.getText();
        String task = id+": "+assignment;
        LocalDate date = classDate.getValue();
        String m = date.format(DateTimeFormatter.ofPattern("MM"));
        String d = date.format(DateTimeFormatter.ofPattern("dd"));
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        schedule.add(new Event(task,1,month, day));
        inputOutput.outputSchedule(schedule);
        try{
        switchToMainScene(event);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public void switchToMainScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}