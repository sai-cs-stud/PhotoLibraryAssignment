package view;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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
	private Stage primaryStage;

	FileChooser fil_chooser = new FileChooser();
	
	@FXML
	protected void buttonPress(ActionEvent event) throws IOException {
		Button b = (Button)event.getSource();
		if(b==logoutbutton) {
			// code to maintain current state here
			LoginStage(mainStage);
		}
		else if(b==addphotobutton) {
			
			File newphoto = fil_chooser.showOpenDialog(mainStage);
			
			if(newphoto != null) {
				
				System.out.println(newphoto.getAbsolutePath());
			}
			
		}
		
	}

	
	public void start(Stage mainStage) {
		// TODO Auto-generated method stub
		this.mainStage = mainStage;
		
	}
	private void LoginStage(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
				getClass().getResource("/view/login.fxml"));
		VBox root = (VBox)loader.load();
		//load our controller into the primary stage
		PhotoController pController = 
				loader.getController();
		pController.start(primaryStage);
		//set the scene
		Scene scene = new Scene(root, 600, 420);
		//make scene primary stage - dont make resizable
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show(); 
		primaryStage.setResizable(false);

	}

}
