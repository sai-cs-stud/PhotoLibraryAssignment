package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


@SuppressWarnings("unused")
public class MainAppController {
	
	@FXML ListView<String> albumlistview;
	@FXML ListView<String> albuminfo_listview;
	@FXML ListView<String> taglistview;
	@FXML Button editcaptionbutton;
	@FXML Button deletetagbutton;
	@FXML Button addtagbutton;
	@FXML Button addphotobutton;
	@FXML Button deletephotobutton;
	@FXML Button editalbumbutton;
	@FXML Button slideshowbutton;
	@FXML Button searchbutton;
	@FXML Button logoutbutton;
	@FXML Button copymovebutton;
	@FXML Button displayphotosbutton;
	@FXML TextArea photocaption;
	@FXML TextField newtaginput;
	
	private Stage mainStage;


	@FXML
	protected void buttonPress(ActionEvent event) throws IOException {
	
		
	}

	
	public void start(Stage mainStage) {
		// TODO Auto-generated method stub
		this.mainStage = mainStage;
		
	}

}
