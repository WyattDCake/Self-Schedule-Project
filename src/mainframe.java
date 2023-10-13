import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class mainframe {

    @FXML
    private Circle myCircle;
    private double x;
    private double y;

    public void one(ActionEvent e){
        myCircle.setCenterY(y-=1);
    }

    public void two(ActionEvent e){
        myCircle.setCenterY(y+=1);
    }
    public void three(ActionEvent e){
        myCircle.setCenterY(x-=1);
    }
    public void four(ActionEvent e){
       myCircle.setCenterY(x+=1);
    }
}
