package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


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
	@FXML ScrollPane myscrollpane;
	@FXML TilePane mytilepane;
	
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
				try {
				System.out.println(newphoto.getAbsolutePath());
				String myphotopath = newphoto.getAbsolutePath();
				File photofile = new File(myphotopath);
				Image myphoto = new Image(new FileInputStream(photofile),150, 0, true, true);
				StackPane background = new StackPane();
				ImageView newimage = new ImageView(myphoto);
				newimage.setFitWidth(150);
				newimage.setFitHeight(mainStage.getHeight() - 10);
                newimage.setPreserveRatio(true);
                newimage.setSmooth(true);
                newimage.setCache(true);
                
				mytilepane.setPadding(new Insets(15, 15, 15, 15));
		        mytilepane.setHgap(15);
				mytilepane.getChildren().addAll(newimage);
				
				
				newimage.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent mouseEvent) {
						// TODO Auto-generated method stub
						if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
							
							newimage.setEffect(new DropShadow(15, Color.BLACK));
							//maybe need to check if other images are highlighted so that only one is highlighted at a time
						
						}
						else {
							newimage.setEffect(null);
						}
					
					}
					
				});
				}
				finally {
					System.out.println("error");
				}
			}
			
		}
		else if(b==displayphotosbutton) {
			//displayPhotoStage(mainStage);
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
	/*private void displayPhotoStage(Stage primaryStage) throws IOException{
		this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader();   
		System.out.println(getClass().getResource("/view/displayphoto.fxml"));
		loader.setLocation(
				getClass().getResource("/view/displayphoto.fxml"));
		Parent root = (Parent)loader.load();
		//load our controller into the primary stage
		PhotoController pController = 
				loader.getController();
		System.out.println(primaryStage);
		pController.start(primaryStage);
		//set the scene
		Scene scene = new Scene(root, 600, 420);
		//make scene primary stage - dont make resizable
		primaryStage.setScene(scene);
		primaryStage.setTitle("Display");
		primaryStage.show(); 
		primaryStage.setResizable(false);

	}*/
}
