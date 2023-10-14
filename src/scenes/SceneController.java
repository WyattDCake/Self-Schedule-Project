import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    String css = this.getClass().getResource("sceneDesign.css").toExternalForm();
    InputOutput inputOutput;
    ArrayList<Event> schedule;
    private Image mainImage = new Image("main.png");
    @FXML
    private Text displayText = new Text();
    @FXML
    private ImageView mainView = new ImageView();
    @FXML
    private DatePicker datePicker = new DatePicker();
    @FXML
    private TextField classID = new TextField();
    @FXML
    private TextField classAssignment = new TextField();

    @FXML
    public void initialize(){
        //input for schedule
        inputOutput = new InputOutput();
        schedule = inputOutput.inputSchedule();
        Collections.sort(schedule);
        //display schedule needs to be here
        displaySchedule();
        //set mainView = mainImage
        mainView.setImage(mainImage);
    }
  
    public void switchToMainScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToDisplayScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("displayScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToWorkScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("workScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSchoolScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("schoolScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToOtherScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("otherScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToEditScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("editScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
    public void exitProgram(ActionEvent event) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        inputOutput.close();
    }
    @FXML
    public void displaySchedule(){
        StringBuilder text = new StringBuilder();
        for(Event event : schedule){
            text.append(event.getName()).append("\n");
            text.append("   Due: "+event.getMonth()+"/"+event.getDay()+"\n");
        }
        displayText.setText(text.toString());
        System.out.println(text);
    }
    public void addEditButtons(){
        
    }

    @FXML
    public void addSchoolWork(ActionEvent event){
        String id = classID.getText();
        String assignment = classAssignment.getText();
        String task = id+": "+assignment;
        LocalDate date = datePicker.getValue();
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
    
}
