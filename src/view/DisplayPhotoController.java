package view;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
	String pattern = "MM/dd/yyyy HH:mm:ss";
	SimpleDateFormat df = new SimpleDateFormat(pattern);
	
	public void start(Stage mainStage) throws FileNotFoundException {
		this.mainStage = mainStage;
		
		String photopath = photo.getPath();
		File photofile = new File(photopath);
		Image photoim = new Image(new FileInputStream(photofile));
		ImageView imview = new ImageView(photoim);
		Calendar cal = photo.getCal();
		Date datetime = cal.getTime();
		Label datelabel = new Label();
		datelabel.setText("Last modified date: " +  df.format(datetime));
		String cap = photo.getCaption();
		ArrayList<String> taglist = photo.getTags();
		if(cap != null) {
			caption.setText(cap);
		}
		if(tags != null) {
			StringBuffer sb = new StringBuffer();
			for(String tag : taglist ) {
				sb.append(tag);
			}
			String sbtoString = sb.toString();
			tags.setText(sbtoString);
		}
		
		imview.setFitHeight(500);
		imview.setFitWidth(500);
		imview.setPreserveRatio(true);
		imview.setEffect(null);

		photopane.setHgap(1);
		photopane.setVgap(1);
		mainStage.setHeight(700);
		mainStage.setWidth(700);
		photopane.getChildren().addAll(imview, datelabel, caption, tags);
	

		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				// TODO Auto-generated method stub
				
			}
			
		
		
		});

	}

}
