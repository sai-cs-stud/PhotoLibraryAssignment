package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchController {

@FXML TextField date1;
@FXML TextField date2;
@FXML TextField tag1;
@FXML TextField tag2;
@FXML Button datesearch;
@FXML Button tag1search;
@FXML Button andsearch;
@FXML Button orsearch;
@FXML RadioButton createbutton;
private Stage mainStage;

@FXML
protected void buttonPress(ActionEvent event) throws IOException{
	Button b = (Button)event.getSource();
}

public void start(Stage mainStage) {
	this.mainStage = mainStage;
	System.out.println("searchtest");
}

}
