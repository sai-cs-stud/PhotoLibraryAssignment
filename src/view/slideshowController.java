package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class slideshowController {
	@FXML Button backbutton;
	@FXML Button forwardbutton;
	@FXML Pane mypane;
	
	public ArrayList<ImageDetails> albumphotos;
	private ArrayList<ImageView> imagearray; 
	private Stage mainStage;
	private int counter;
	
	@FXML
	protected void buttonPress(ActionEvent event) throws IOException{
		Button b = (Button)event.getSource();
		if (b == backbutton) {
			counter = counter - 1;
			if(counter >= 0 && counter < imagearray.size() ){
				mypane.getChildren().remove(imagearray.get(counter + 1));
				mypane.getChildren().add(imagearray.get(counter));
			}else {
				counter = counter + 1;
			}
			
			
			
		}
		else if(b == forwardbutton) {
			counter = counter + 1;
			if(counter >= 0 && counter < imagearray.size()) {
				mypane.getChildren().remove(imagearray.get(counter - 1));
				mypane.getChildren().add(imagearray.get(counter));
			}else {
				counter = counter - 1;
			}
			
			}
		}
		
	
	public void start(Stage mainStage) throws FileNotFoundException {
		this.mainStage = mainStage;
		imagearray = new ArrayList<ImageView>();
		counter = 0;
		for(ImageDetails photo : albumphotos){
			System.out.println("test");
			String photopath = photo.getPath();
			File photofile = new File(photopath);
			Image myimg = new Image(new FileInputStream(photofile));
			ImageView myimgview = new ImageView(myimg);
			myimgview.setFitHeight(600);
			myimgview.setFitWidth(600);
			myimgview.setPreserveRatio(true);
			myimgview.setEffect(null);
			System.out.print(myimgview);
			imagearray.add(myimgview);
		}		
		mypane.getChildren().add(imagearray.get(0));
		
		
	}
}
