import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneController {

    @FXML
    private TextField tfSample;

    @FXML
    void btnClicked(ActionEvent event) {
        Stage mainWindow = (Stage) tfSample.getScene().getWindow();
        String title = tfSample.getText();
        mainWindow.setTitle(title);

    }

}
