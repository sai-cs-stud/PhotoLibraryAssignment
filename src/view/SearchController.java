package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class SearchController {

@FXML TextField date1;
@FXML TextField date2;
@FXML TextField tag1;
@FXML TextField tag2;
@FXML Button datesearch;
@FXML Button tag1search;
@FXML Button andsearch;
@FXML Button orsearch;
@FXML RadioButton createbutton;
private Stage mainStage;
public Hashtable<String,ArrayList<ImageDetails>> deetzdictref;

String pattern = "MM/dd/yyyy HH:mm:ss";
SimpleDateFormat df = new SimpleDateFormat(pattern);
ObservableList<ImageView> imageViews = FXCollections.observableArrayList();

@FXML
protected void buttonPress(ActionEvent event) throws IOException, ParseException{
	Button b = (Button)event.getSource();
	
	
	if(b == datesearch) {
		Set<String> albumkeys = deetzdictref.keySet();
		for (String album : albumkeys) {
			for(ImageDetails photo : deetzdictref.get(album)) {
				Calendar photocal = photo.getCal();
				Date photodate = photocal.getTime();
				System.out.println(photodate);
				System.out.println(df.format(photodate));
				Date date1date = df.parse(date1.getText().trim() + " 00:00:00");
				Date date2date = df.parse(date2.getText().trim() + " 00:00:00");
				//if(date1d)
				System.out.println(date1date);
				if((photodate.after(date1date) && photodate.before(date2date))) {
					System.out.println(date1date + " is after " + photodate + " and before " + photodate);
					//make an image out of the photo path - put it on a photoview and add it to the pane shown?
					String impath = photo.getPath();
					Image newimage = new Image(new FileInputStream(impath));
					ImageView imview = new ImageView(newimage);
					imview.setFitWidth(250);
					imview.setFitHeight(mainStage.getHeight() - 10);
					imview.setPreserveRatio(true);
					imview.setSmooth(true);
					imview.setCache(true);
					imageViews.add(imview);
				
				}				
			}}
		searchResultsDisplay(imageViews);
		
		
	}
}

public void start(Stage mainStage) {
	this.mainStage = mainStage;
	System.out.println("searchtest");
}

protected void searchResultsDisplay(ObservableList<ImageView> imageviewobs) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/searchresults.fxml"));
	Stage searchResultsStage = (Stage)loader.load();
	SearchResultsController searchrescon = loader.getController();
	searchResultsStage.setTitle("Results");
	System.out.println(imageviewobs);
	searchrescon.addedImagesref = imageviewobs;
	searchrescon.start(searchResultsStage);
	searchResultsStage.show();
}

}
