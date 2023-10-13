import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
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
    /* all from failed Slider attempt
    @FXML 
    private Slider startTimeSlider = new Slider();
    @FXML
    private Slider endTimeSlider = new Slider();
    @FXML 
    private Label startLabel = new Label();
    @FXML
    private Label endLabel = new Label();
    */
    @FXML
    private Text displayText = new Text();
    @FXML
    private ImageView mainView = new ImageView();

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
        /*startTimeSlider settings
        startTimeSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
        startLabel.setText("Shift Starts at: " + getTime(newValue.intValue()));
        });
        */
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
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    public void displaySchedule(){
        StringBuilder text = new StringBuilder();
        for(Event event : schedule){
            text.append(event.getName()).append("\n");
            text.append("   Due: "+event.getMonth()+"/"+event.getDay()+"\n");
        }
        displayText.setText(text.toString());
    }
    public void addEditButtons(){
        
    }
    /* failed slider experiement
    public String getTime(int time){
        String stringAMPM = " AM";
        int hour = 0;
        String min;
        while(time > 4){
            time-=4;
            hour++;
        }
        if(hour > 12){
            stringAMPM =" PM";
            hour-=12;
        }
        switch(time){
            case 0:
                min = ":00";
                break;
            case 1:
                min = ":15";
                break;
            case 2:
                min = ":30";
                break;
            case 3:
                min = ":45";
                break;
            default:
                min = "error";
                break;
        }
        return (String.valueOf(hour)+min+stringAMPM);
    }
    */
}
