package view;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
		String[] tag = photo.getTags();
		if(cap != null) {
			caption.setText(cap);
		}
		if(tag != null) {
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<tag.length; i++) {
				sb.append(tag[i]);
			}
			String sbtoString = sb.toString();
			tags.setText(sbtoString);
		}
		
		imview.setFitHeight(500);
		imview.setFitWidth(500);
		imview.setPreserveRatio(true);
		imview.setEffect(null);

		photopane.setHgap(5);
		photopane.setVgap(1);
		photopane.getChildren().addAll(imview, datelabel, caption, tags);
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				// TODO Auto-generated method stub
				
			}
	
		
		
		});

	}

}
