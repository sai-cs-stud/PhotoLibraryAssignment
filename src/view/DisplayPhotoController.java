package view;


import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DisplayPhotoController {
	@FXML TilePane photopane;
	@FXML Label date_time;
	@FXML Label caption;
	@FXML Label tags;
	
	private Stage mainStage;
	
	ImageView photo;
	public void start(Stage mainStage, ImageView photo) {
		if(photo==null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
			return;
		}
		this.mainStage = mainStage;
		this.photo = photo;
		
		double h = photopane.getWidth();
		double w = photopane.getHeight();
		photo.setFitHeight(h);
		photo.setFitWidth(w);
		photo.setEffect(null);
		photopane.getChildren().addAll(photo);
		
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				// TODO Auto-generated method stub
				
			}
	
		
		
		});

	}

}
