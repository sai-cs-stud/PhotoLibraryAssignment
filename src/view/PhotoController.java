package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import app.Photos;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

@SuppressWarnings("unused")
public class PhotoController {

	@FXML private AnchorPane anchor;
	@FXML private TextField userinputbar;
	private Stage mainStage;
	private boolean isStock = false;
	String logininput;
	@FXML protected void login(ActionEvent event) throws ClassNotFoundException {
		logininput = userinputbar.getText();
		System.out.println(logininput);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
				"userData.txt"));
			String line = reader.readLine();
			while(line != null) {
				System.out.println(line);
				if(line.equals(logininput)) {
					System.out.println("does this even work?");
					if(line.equals("admin")) {
						System.out.println("i am admin");
							AdminStage();
							break;
					
					}
					else{
						if(logininput.equals("stock")) {
							isStock = true;
						}
						File file = new File(Photos.storeDir + File.separator + logininput + ".bin");
						file.createNewFile();
						mainAppStage();
						break;
						
					}
						
                     	
				}
			line = reader.readLine();
			}
			reader.close();
			if(reader==null) {
				System.out.println("Error: no userData.txt file found");
				System.out.println("userData.txt must be in the project folder");
			}
		}
		
			
			catch (IOException e) {
				e.printStackTrace();
			}
		
	
}
	
	public void start(Stage mainStage) {  
	    this.mainStage = mainStage;
	    
	
	
	}
	
	
	private void AdminStage() throws IOException {
		mainStage.hide();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin.fxml"));
	    Stage adminStage = (Stage)loader.load();
	    AdminController adminController = 
				loader.getController();
	    adminStage.setTitle("Admin menu");
		adminController.start(adminStage);
	    adminStage.show();
	   
	    
	}  
	
	private void mainAppStage() throws IOException, ClassNotFoundException {
		System.out.println("Does this work?");
		mainStage.hide();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainapp.fxml"));
	    Stage appStage = (Stage)loader.load();
	    MainAppController mainAppController = 
				loader.getController();
	    appStage.setTitle("App menu");
	    //mainAppController.isStock = isStock;
	    mainAppController.login = logininput;
		mainAppController.start(appStage);
	    appStage.show();
		
		
	}
	
}




