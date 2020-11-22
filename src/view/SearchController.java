package view;

import java.io.FileInputStream;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
@FXML TextField optalbname;

private Stage mainStage;
public Hashtable<String,ArrayList<ImageDetails>> deetzdictref;
public ListView<String> albumlist;
public ListView<String> albuminfo_list;
public ObservableList<String> albumobslist = FXCollections.observableArrayList();
public ObservableList<String> albuminfo_ObsList = FXCollections.observableArrayList();

String pattern = "MM/dd/yyyy HH:mm:ss";
SimpleDateFormat df = new SimpleDateFormat(pattern);
ObservableList<ImageView> imageViews = FXCollections.observableArrayList();

@FXML
protected void buttonPress(ActionEvent event) throws IOException, ParseException{
	Alert alert = new Alert(AlertType.ERROR);
	alert.setContentText("Bad inputtt!! BURH!");
	Button b = (Button)event.getSource();
	Set<String> albumkeys = deetzdictref.keySet();
	ArrayList<ImageDetails> newalbumdeetz = new ArrayList<ImageDetails>();
	try {
	if(b == datesearch) {
		
		for (String album : albumkeys) {
			for(ImageDetails photo : deetzdictref.get(album)) {
				Calendar photocal = photo.getCal();
				Date photodate = photocal.getTime();
				Date date1date = df.parse(date1.getText().trim() + " 00:00:00");
				Date date2date = df.parse(date2.getText().trim() + " 00:00:00");
				//if(date1d)
				if((photodate.after(date1date) && photodate.before(date2date))) {
					//make an image out of the photo path - put it on a photoview and add it to the pane shown?
					String impath = photo.getPath();
					Image newimage = new Image(new FileInputStream(impath));
					ImageView imview = new ImageView(newimage);
					imview.setFitWidth(150);
					imview.setFitHeight(mainStage.getHeight() - 10);
					imview.setPreserveRatio(true);
					imview.setSmooth(true);
					imview.setCache(true);
					imageViews.add(imview);
					if(createbutton.isSelected()) {
						newalbumdeetz.add(photo);
					}
				
				}				
			}
			}
			if(createbutton.isSelected()) {
				makeAlbum(optalbname.getText().trim(), newalbumdeetz);
				searchResultsDisplay(imageViews);	
			
		}
	
		
	}
	else if(b == tag1search) {
		for (String album: albumkeys) {
			for(ImageDetails photo : deetzdictref.get(album)) {
				Hashtable<String, ArrayList<String>> tagdict = photo.getTags();
				//person=sesh
				String tag1text = tag1.getText().trim();
				String[] tag1spliced = tag1text.split("=");
				String tagkey = "{" + tag1spliced[0];
				String spectag = tag1spliced[1] + "}";
				if((tagdict.containsKey(tagkey) && tagdict.get(tagkey).contains(spectag))) {
					String impath = photo.getPath();
					Image newimage = new Image(new FileInputStream(impath));
					ImageView imview = new ImageView(newimage);
					imview.setFitWidth(150);
					imview.setFitHeight(mainStage.getHeight() - 10);
					imview.setPreserveRatio(true);
					imview.setSmooth(true);
					imview.setCache(true);
					imageViews.add(imview);
					if(createbutton.isSelected()) {
						newalbumdeetz.add(photo);
					}
				
				}
				
			}
		}
		if(createbutton.isSelected()) {
			makeAlbum(optalbname.getText().trim(), newalbumdeetz);
			searchResultsDisplay(imageViews);	
		
		}

	}
	else if(b == andsearch) {
		for (String album: albumkeys) {
			for(ImageDetails photo : deetzdictref.get(album)) {
				Hashtable<String, ArrayList<String>> tagdict = photo.getTags();
				String tag1text = tag1.getText().trim();
				String[] tag1spliced = tag1text.split("=");
				String tag1key = "{" + tag1spliced[0];
				String spec1tag = tag1spliced[1] + "}";
				
				String tag2text = tag2.getText().trim();
				String[] tag2spliced = tag2text.split("=");
				String tag2key = "{" + tag2spliced[0];
				String spec2tag = tag2spliced[1] + "}";
				
				if((tagdict.containsKey(tag1key) && tagdict.get(tag1key).contains(spec1tag)) &&
					(tagdict.containsKey(tag2key) && tagdict.get(tag2key).contains(spec2tag))){
					String impath = photo.getPath();
					Image newimage = new Image(new FileInputStream(impath));
					ImageView imview = new ImageView(newimage);
					imview.setFitWidth(150);
					imview.setFitHeight(mainStage.getHeight() - 10);
					imview.setPreserveRatio(true);
					imview.setSmooth(true);
					imview.setCache(true);
					imageViews.add(imview);
					if(createbutton.isSelected()) {
						newalbumdeetz.add(photo);
					}
					
				}	
			}
		}
		if(createbutton.isSelected()) {
			makeAlbum(optalbname.getText().trim(), newalbumdeetz);
			searchResultsDisplay(imageViews);	
	}
		
	}
	else if(b == orsearch) {
		for (String album: albumkeys) {
			for(ImageDetails photo : deetzdictref.get(album)) {
				Hashtable<String, ArrayList<String>> tagdict = photo.getTags();
				String tag1text = tag1.getText().trim();
				String[] tag1spliced = tag1text.split("=");
				String tag1key = "{" + tag1spliced[0];
				String spec1tag = tag1spliced[1] + "}";
				
				String tag2text = tag2.getText().trim();
				String[] tag2spliced = tag2text.split("=");
				String tag2key = "{" + tag2spliced[0];
				String spec2tag = tag2spliced[1] + "}";
			
				if((tagdict.containsKey(tag1key) && tagdict.get(tag1key).contains(spec1tag)) ||
						(tagdict.containsKey(tag2key) && tagdict.get(tag2key).contains(spec2tag))){
						String impath = photo.getPath();
						Image newimage = new Image(new FileInputStream(impath));
						ImageView imview = new ImageView(newimage);
						imview.setFitWidth(150);
						imview.setFitHeight(mainStage.getHeight() - 10);
						imview.setPreserveRatio(true);
						imview.setSmooth(true);
						imview.setCache(true);
						imageViews.add(imview);
						if(createbutton.isSelected()) {
							newalbumdeetz.add(photo);
						}
					
				}
			}
		}
		if(createbutton.isSelected()) {
			makeAlbum(optalbname.getText().trim(), newalbumdeetz);
			searchResultsDisplay(imageViews);	
		
	}

		
	}
}catch(Exception io) {
	alert.show();
}
	if(createbutton.isSelected() == false){
	searchResultsDisplay(imageViews);	
	}
}

public void start(Stage mainStage) {
	this.mainStage = mainStage;
	
}

protected void searchResultsDisplay(ObservableList<ImageView> imageviewobs) throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/searchresults.fxml"));
	Stage searchResultsStage = (Stage)loader.load();
	SearchResultsController searchrescon = loader.getController();
	searchResultsStage.setTitle("Results");
	searchrescon.addedImagesref = imageviewobs;
	searchrescon.start(searchResultsStage);
	searchResultsStage.show();
}

private void makeAlbum(String newalbumname, ArrayList<ImageDetails> imgdeetz) {
	if(dupeCheck(imgdeetz) == false) {
		albumobslist.add(newalbumname);
		albumlist.setItems(albumobslist);
		deetzdictref.put(newalbumname, imgdeetz);
	}
	else {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText("You got a dupe in this album, therefore it will not be created");
		alert.show();
	}
	
	
}

private boolean dupeCheck(ArrayList<ImageDetails> deeeetz) {
	Set<String> seendeetz = new HashSet();
	for(ImageDetails newdeetz : deeeetz) {
		if(seendeetz.contains(newdeetz.getPath())) {
			return true;
		}
		else {
			seendeetz.add(newdeetz.getPath());
		}
	}
	
	return false;
	
}

}
