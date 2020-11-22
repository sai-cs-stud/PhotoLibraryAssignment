// Sai Nayan Malladi srm275
// Nicolas Gundersen neg62
// CS 213 Software Methodology 
// Sesh

// THe ListApp class serves as the main launcher of our GUI program. 
package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import view.PhotoController;
import view.SerializableData;

public class Photos extends Application implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String storeDir= "src/model";
	public static final String storeFile = "photolib.bin";
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		// TODO Auto-generated method stub
		//load our fxml file 
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
	
	public void changeScene(String fxml){
	    Parent pane;
		try {
			pane = FXMLLoader.load(
			       getClass().getResource(fxml));
			 Scene scene = new Scene(pane);
			 primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeApp(SerializableData sd,String user) throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + "photolib.bin"));
		//ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + user + ".bin"));
		
		//File file = new File(storeDir + File.separator + user + ".bin");
		//file.createNewFile();
		oos.writeObject(sd);
		oos.close();
	}
	public static SerializableData readApp(String user) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + "photolib.bin"));
		//ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + user + ".bin"));
		SerializableData sd = (SerializableData)ois.readObject();
		ois.close();
		return sd;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//LAUNCH THE PROGRAM
		launch(args);
	}

}
