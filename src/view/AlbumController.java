package view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
	public ObservableList<String> albumobslist;
	
	@FXML
	protected ObservableList<String> buttonPress(ActionEvent event) throws IOException{
		Button b = (Button)event.getSource();
		if(b == createalbum_but) {
			
			String newalbumname = createalbum_tf.getText();
			System.out.println(newalbumname);
			albumobslist.add(newalbumname);
			return albumobslist;
		}
		return albumobslist;
		
		
	}

	public void start(Stage mainStage) {
		this.mainStage = mainStage;
		albumobslist = FXCollections.observableArrayList();
		System.out.println(albumobslist);
		System.out.println(albumlist);
		
		
		
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				// TODO Auto-generated method stub
				
			}
	
		
		
		});

	}
	
	
	
}
