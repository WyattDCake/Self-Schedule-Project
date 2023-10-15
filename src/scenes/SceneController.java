import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private int currentPage;
    private int maxPerPage = 6;
    String css = this.getClass().getResource("sceneDesign.css").toExternalForm();
    InputOutput inputOutput;
    ArrayList<Event> schedule;
    private Image mainImage = new Image("main.png");

    @FXML
    private Text displayText = new Text();
    @FXML
    private ImageView mainView = new ImageView();
    @FXML
    private DatePicker classDate = new DatePicker();
    @FXML
    private DatePicker otherDate = new DatePicker();
    @FXML
    private DatePicker workDate = new DatePicker();
    @FXML
    private TextField classID = new TextField();
    @FXML
    private TextField classAssignment = new TextField();
    @FXML
    private TextField otherTask = new TextField();
    @FXML
    private TextField startTime = new TextField();
    @FXML
    private TextField endTime = new TextField();
    @FXML
    private Pagination displayPagination = new Pagination();

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
        //set up display pages
        displayPagination.setPageCount((int)Math.ceil((double) schedule.size()/maxPerPage));
        displayPagination.setPageFactory(this::createPage);
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
    }
    public void addEditButtons(){
        
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
    @FXML
    public void addOtherTask(ActionEvent event){
        String task = otherTask.getText();
        LocalDate date = otherDate.getValue();
        String m = date.format(DateTimeFormatter.ofPattern("MM"));
        String d = date.format(DateTimeFormatter.ofPattern("dd"));
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        schedule.add(new Event(task,3,month, day));
        inputOutput.outputSchedule(schedule);
        try{
        switchToMainScene(event);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    public void addWorkShift(ActionEvent event){
        String task = "Work:"+startTime.getText()+"-"+endTime.getText();
        LocalDate date = workDate.getValue();
        String m = date.format(DateTimeFormatter.ofPattern("MM"));
        String d = date.format(DateTimeFormatter.ofPattern("dd"));
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        schedule.add(new Event(task,2,month, day));
        inputOutput.outputSchedule(schedule);

        try{
        switchToMainScene(event);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    public Node createPage(int pageIndex){
        ListView<Event> eventView = new ListView<>();
        int fromIndex = pageIndex * maxPerPage;
        int toIndex = Math.min(fromIndex + maxPerPage, schedule.size());
        List<Event> eventString = schedule.subList(fromIndex, toIndex);
        //testing buttons
        eventView.setCellFactory(param -> new ListCell<Event>(){
            @Override
            protected void updateItem(Event item, boolean empty){
                super.updateItem(item,empty);
                if(empty || item == null){
                    setText(null);
                    setGraphic(null);
                } else{
                    Label label = new Label(item.display());

                    Button editButton = new Button ("Edit");

                    Button completedButton = new Button ("Completed");

                    editButton.setOnAction(event ->{
                        System.out.println("done");
                    });
                    
                    completedButton.setOnAction(event ->{
                        System.out.println("done");
                    });
                    
                    HBox eventHbox = new HBox(label);

                    HBox buttonHbox = new HBox(editButton,completedButton);

                    buttonHbox.setSpacing(50);

                    buttonHbox.setAlignment(Pos.CENTER_RIGHT);

                    HBox.setMargin(buttonHbox,new Insets(0,0,0,200));
                    
                    HBox hbox = new HBox(eventHbox,buttonHbox);
                    
                    setGraphic(hbox);
                }
            }

        });

        ObservableList<Event> observableEventList = FXCollections.observableArrayList((eventString));
        eventView.setItems(observableEventList);
        return eventView;
    }
    @FXML
    public void handlePreviousPage(){
        if(currentPage > 0){
            currentPage--;
            displayPagination.setCurrentPageIndex(currentPage);
        }
    }
    @FXML
    public void handleNextPage(){
        if(currentPage < displayPagination.getPageCount()-1){
            currentPage++;
            displayPagination.setCurrentPageIndex(currentPage);
        }
    }
}
