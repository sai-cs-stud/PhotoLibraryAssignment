package view;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
		Boolean newuser = true;
		if (b == logout) {
			writeToTextFile("userData.txt", obsList);
			LoginStage(mainStage);
			
		}
		else if(b == createuserbutton) {
			String userinput = userinputbar.getText();
			if (userinput.trim().isEmpty()) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("error!");
				alert.setContentText("You cannot create a user with no name!");
				alert.showAndWait();
			}
			else {
				for(int i=0; i<obsList.size(); i++)
					if(obsList.get(i).equals(userinput)) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("error!");
						alert.setContentText("This username already exists");
						alert.showAndWait();
						newuser = false;
						break;
					}
				}
				if(newuser == true) {
					obsList.add(userinput);
				}
				
			}
		else if(b == deleteuserbutton) {
			int index = listview.getSelectionModel().getSelectedIndex();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("delete user?");
			String message = ("Are you sure you want to remove " + obsList.get(index) + " from the system?");
			alert.setContentText(message);
			Optional<ButtonType> result = alert.showAndWait();
			if((result.isPresent()) && (result.get() == ButtonType.OK)) {
				obsList.remove(index);
			
			}
		}
	
	
	
	}

	public void start(Stage mainStage) {
		this.mainStage = mainStage;
		obsList = FXCollections.observableArrayList();
	
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
			String line = reader.readLine();
			while(line != null) {
				obsList.add(line);
				line = reader.readLine();
			}
			listview.setItems(obsList);
			listview.getSelectionModel().select(0);
			reader.close();
			
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			public void handle(WindowEvent we) {
				try {
					writeToTextFile("userData.txt", obsList);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});


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
	
	private static void writeToTextFile(String filename, ObservableList<String> users)
            throws IOException {

        FileWriter writer = new FileWriter(filename);
        for (String user : users) {
            writer.write(user + "\n");
        }
        writer.close();
    }
	
	

}
