package view;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
	
	public ImageDetails photo;
	
	
	public void start(Stage mainStage) throws FileNotFoundException {
		this.mainStage = mainStage;
		
		String photopath = photo.getPath();
		File photofile = new File(photopath);
		Image photoim = new Image(new FileInputStream(photofile));
		ImageView imview = new ImageView(photoim);
		
		double h = photopane.getWidth();
		double w = photopane.getHeight();
		imview.setFitHeight(h);
		imview.setFitWidth(w);
		imview.setEffect(null);
		photopane.getChildren().addAll(imview);
		
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				// TODO Auto-generated method stub
				
			}
	
		
		
		});

	}

}
