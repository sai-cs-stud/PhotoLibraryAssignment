package view;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Hashtable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class CopyMoveController {
	/**
	 * @author Nicolas Gundersen neg62
	 * @author Sai Nayan Malladi srm275
	 * 
	 * {@summary} The CopyMoveController serves as the menu for the Copy/Move Photo
	 * This menu allows you to copy and image from one album, to another. Or just move the photo,
	 * deleting it from the source
	 */
@FXML TextField albinput;
@FXML Button movbutton;
@FXML Button copybutton;

public TilePane tilepaneref;
public String albumref;
public ImageView imgviewref;
public ImageDetails imagedetailsref;
public Hashtable<String,ArrayList<ImageDetails>> deetzdictref;
private Stage mainStage;


@FXML
protected void buttonPress(ActionEvent event) throws IOException {
	Button b = (Button)event.getSource();
	String albumdest = albinput.getText().trim();
	
	if(deetzdictref.containsKey(albumdest)){
		
	if(dupechecker(imagedetailsref, albumdest, deetzdictref) == false){
		
	
	if(b == movbutton) {
		ImageDetails imgdetailscopy = imagedetailsref;
		deetzdictref.get(albumref).remove(imagedetailsref);
		deetzdictref.get(albumdest).add(imgdetailscopy);
		tilepaneref.getChildren().remove(imgviewref);
	}
	else if(b == copybutton) {
		ImageDetails imgdetailscopy = imagedetailsref;
		deetzdictref.get(albumdest).add(imgdetailscopy);
	}
		}
	
	else if(dupechecker(imagedetailsref, albumdest, deetzdictref)) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText("This photo already exists in your destination album");
		alert.show();
	}
}
	else {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText("This album does not exist!!");
		alert.show();
	}
}


public void start(Stage mainStage){
	this.mainStage = mainStage;
}

private boolean dupechecker(ImageDetails deet, String dest, Hashtable<String,ArrayList<ImageDetails>> dict) {
	String pathname = deet.getPath();
	for(ImageDetails photos : dict.get(dest)) {
		if (photos.getPath().equals(pathname)) {
			return true;
			
		}
	}
	return false;
	
}


}
