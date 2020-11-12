package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class AdminController {

	@FXML
	private TextField userinputbar;
	@FXML
	ListView<String> listview; // list of users
	@FXML
	Button createuserbutton;
	@FXML
	Button deleteuserbutton;
	@FXML
	Button logout;
	@FXML
	ScrollBar scrollbar;
	private Stage mainStage;
	private ObservableList<String> obsList;
	private Stage primaryStage;    

	@FXML
	protected void buttonPress(ActionEvent event) throws IOException {
		Button b = (Button)event.getSource();
		if (b == logout) {
			LoginStage(mainStage);
		}
	
	
	
	}

	public void start(Stage mainStage) {
		this.mainStage = mainStage;
		obsList = FXCollections.observableArrayList();
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
			String line = reader.readLine();
			while(line != null) {
				System.out.println(line);
				obsList.add(line);
				line = reader.readLine();
			}
			System.out.println(obsList);
			listview.setItems(obsList);
			listview.getSelectionModel().select(0);

			
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			


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
