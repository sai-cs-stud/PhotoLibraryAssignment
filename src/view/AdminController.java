package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class AdminController {
	
	@FXML private TextField userinputbar;
	@FXML ListView<String> listView;                
	@FXML Button createuserbutton;
	@FXML Button deleteuserbutton;
	@FXML Button logout;
	@FXML ScrollBar scrollbar;
	
	@FXML protected void buttonPress(ActionEvent event) {
	
	}
	
	
	
	public void start(Stage mainStage) {
		// TODO Auto-generated method stub
		
	}

}
