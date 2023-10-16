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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private int currentPage;
    private int maxPerPage = 6;
    private String css = this.getClass().getResource("sceneDesign.css").toExternalForm();
    private InputOutput inputOutput;
    private ArrayList<Event> schedule;
    private Image mainImage = new Image("main.png");
    @FXML
    private Stage mainStage = new Stage();
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
    private AnchorPane editPane = new AnchorPane();

    @FXML
    public void initialize(){
        //input for schedule
        inputOutput = new InputOutput();
        schedule = inputOutput.inputSchedule();
        Collections.sort(schedule);
        //set mainView = mainImage
        mainView.setImage(mainImage);
        //set up display pages
        displayPagination.setPageCount((int)Math.ceil((double) schedule.size()/maxPerPage));
        displayPagination.setPageFactory(this::createPage);
    }
  
    public void switchToMainScene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        mainStage.setScene(scene);
        mainStage.show();
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
    @FXML
    public void switchToEditPopUp(Event task){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editScenePopUp.fxml"));
            Parent root = loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("Edit Task");
            Scene editScene = new Scene(root);
            
            editPane = (AnchorPane) root.lookup("#editPane");

            Label editLabel = new Label(task.display());
            Font italicFont = Font.font("System",FontPosture.ITALIC,20);

            TextField editName = new TextField();
            editName.setPromptText(task.getName());

            DatePicker editDate = new DatePicker();
            editDate.setPromptText(task.getDate());

            Button saveButton = new Button("Save");

            saveButton.setOnAction(event -> {
                if(editName.getText().isEmpty() || editDate.getValue() == null){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("No Data Entered");
                    alert.setHeaderText("Please Set Data");
                    alert.setContentText("I'll go add some");
                    alert.showAndWait();
                    return;
                }
                LocalDate date = editDate.getValue();
                int month = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("MM")));
                int day = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("dd")));
                schedule.add(new Event(editName.getText(),task.getID(),month,day));
                schedule.remove(schedule.indexOf(task));
                inputOutput.outputSchedule(schedule);
                editStage.close();
            });

            saveButton.setLayoutX(501);
            saveButton.setLayoutY(160);
            editLabel.setLayoutX(143);
            editLabel.setLayoutY(14);
            editName.setLayoutX(97);
            editName.setLayoutY(88);
            editDate.setLayoutX(353);
            editDate.setLayoutY(88);

            editLabel.setFont(italicFont);

            editPane.getChildren().add(saveButton);
            editPane.getChildren().add(editLabel);
            editPane.getChildren().add(editName);
            editPane.getChildren().add(editDate);

            editScene.getStylesheets().add(css);
            editStage.setScene(editScene);
            editStage.showAndWait();
            Collections.sort(schedule);
            displayPagination.setPageCount((int)Math.ceil((double) schedule.size()/maxPerPage));
            displayPagination.setPageFactory(this::createPage);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void exitProgram(ActionEvent event) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        inputOutput.close();
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
                        switchToEditPopUp(item);
                    });
                    
                    completedButton.setOnAction(event ->{
                        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
                        confirmationAlert.setHeaderText("Have you Completed: "+item.display());
                        if(confirmationAlert.showAndWait().get() == ButtonType.OK){
                            schedule.remove(schedule.indexOf(item));
                            inputOutput.outputSchedule(schedule);
                            displayPagination.setPageCount((int)Math.ceil((double) schedule.size()/maxPerPage));
                            displayPagination.setPageFactory(pageIndex -> createPage(pageIndex));
                        }
                    });
                    
                    Region spacer = new Region();
                    HBox.setHgrow(spacer,Priority.ALWAYS);
                    
                    HBox rowBox = new HBox(label,spacer,editButton,completedButton);
                    rowBox.setAlignment(Pos.BASELINE_LEFT);
                    rowBox.setSpacing(7);

                    setGraphic(rowBox);
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
