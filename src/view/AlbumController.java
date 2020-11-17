package view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

@SuppressWarnings("unused")
public class AlbumController {
	
	
	
	@FXML TextField createalbum_tf;
	@FXML TextField deletealbum_tf;
	@FXML TextField oldalb_tf;
	@FXML TextField newalb_tf;
	@FXML Button createalbum_but;
	@FXML Button deletealbum_but;
	@FXML Button renamealbum_but;
	
	private Stage mainStage;
	public ListView<String> albumlist;
	public ObservableList<String> albumobslist = FXCollections.observableArrayList();
	@FXML
	protected void buttonPress(ActionEvent event) throws IOException{
		Alert noalbumserr = new Alert(AlertType.ERROR);
		noalbumserr.setContentText("There are no albums, what were you thinking?!");
		
		Alert dupealbumerr = new Alert(AlertType.ERROR);
		dupealbumerr.setContentText("This album already exists!!");
		
		Alert emptynameerr = new Alert(AlertType.ERROR);
		emptynameerr.setContentText("Your album name cannot be empty");
		
		Button b = (Button)event.getSource();
		//CreateAlbum button
		if(b == createalbum_but) {
			boolean dupe = false;
			if(createalbum_tf.getText().trim().isEmpty()) {
				emptynameerr.show();
			}
			else {
			String newalbumname = createalbum_tf.getText();
			//Is the album obs list empty? if so just add the album
			if(albumobslist.isEmpty()) {
				System.out.println(newalbumname);
				albumobslist.add(newalbumname);
			}
			//Album not empty? search through it and check if theres duplicates 
			else {
				for(String album : albumobslist) {
					if(album.equals(newalbumname)) {
						dupe = true;
						dupealbumerr.show();
						break;
					
						}	
					}
				//no dupe? add the album 
					if(dupe == false) {
						albumobslist.add(newalbumname);
					}
				
			}
		}
	}
		else if(b == deletealbum_but) {
			if(albumobslist.isEmpty()) {
				noalbumserr.show();
			}
			else {
				String delalbumname = deletealbum_tf.getText();
				for (String album: albumobslist) {
					if(delalbumname.equals(album)) {
						albumobslist.remove(delalbumname);
						break;
					}
				}
			}
		}
		else if(b == renamealbum_but) {
			Alert badinput = new Alert(AlertType.ERROR);
			badinput.setContentText("You must enter two album names, the first must be a valid album name");
			
			if(albumobslist.isEmpty()) {
				noalbumserr.show();
			}
			else {
				
				if(oldalb_tf.getText().trim().isEmpty() || newalb_tf.getText().trim().isEmpty()) {
					badinput.show();
					
				}
				else {
				boolean checker = true;
				boolean dupe = false;
				String oldalbumname = oldalb_tf.getText();
				String newalbumname = newalb_tf.getText();
				//check if your making an album with a name that already exists
				for(String album : albumobslist) {
					if(album.equals(newalbumname)) {
						dupe = true;
						dupealbumerr.show();
						break;
					
						}
				}
				if(dupe == false) {
					for (String album: albumobslist) {
						if(album.equals(oldalbumname)) {
							int albumindex = albumobslist.indexOf(album);
							albumobslist.set(albumindex, newalbumname);
							checker = false;
							break;
						}
					}
					
					}
					if(checker == true && dupe == false) {
						badinput.show();
						}
				
				}
			}
		}
			
		albumlist.setItems(albumobslist);
		System.out.println(albumobslist);
	}
		

	public void start(Stage mainStage) {
		this.mainStage = mainStage;
		System.out.println(albumobslist);
		
		
		
		
		
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				// TODO Auto-generated method stub
				try {
				
			
				} catch (Exception e) {
			         e.printStackTrace();
			      }
			}
		
		
		});

	}
	

}
