package view;


import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EditCaptionController {
	@FXML TilePane photopane;
	@FXML Button confirmEdit;
	@FXML TextField input;
	@FXML Label caption;
	
	private Stage mainstage;
	public ImageDetails photo;
	public Hashtable<String,ArrayList<ImageDetails>> ecc_detsDict = null;
	public String curr_alb;
	public TextArea textarearef;
	
	String pattern = "MM/dd/yyyy HH:mm:ss";
	SimpleDateFormat df = new SimpleDateFormat(pattern);
	
	
	public void start(Stage mainStage) {
		this.mainstage = mainStage;
		//this.photo = photo;
		String photopath = photo.getPath();
		
		File photofile = new File(photopath);
		Image photoim = null;
		try {
			photoim = new Image(new FileInputStream(photofile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView imview = new ImageView(photoim);
		String cap = photo.getCaption();
		if(cap != null) {
			caption.setText(cap);
		}
		imview.setFitWidth(150);
		imview.setFitHeight(mainStage.getHeight() - 10);
		imview.setPreserveRatio(true);
		imview.setSmooth(true);
		imview.setCache(true);
		
		photopane.setPadding(new Insets(15, 15, 15, 15));
		photopane.setHgap(15);
		photopane.getChildren().addAll(imview);
		
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				// TODO Auto-generated method stub
				
			}
	
		
		
		});

	}
	@FXML
	protected void buttonPress(ActionEvent event) throws IOException{
		Button b = (Button)event.getSource();
		if(b==confirmEdit) {
			Alert inputNull = new Alert(AlertType.ERROR);
			inputNull.setContentText("No Input found");
			
			if(input.getText().trim().isEmpty()) {
				inputNull.show();
			}
			else {
				Calendar cal = photo.getCal();
				Date photodatetime = cal.getTime();
				String stringdate = df.format(photodatetime);
				caption.setText(input.getText().trim());
				textarearef.setText("Last modified date: " + stringdate + " " + input.getText().trim());
				input.setText("");
				for(ImageDetails deetz: ecc_detsDict.get(curr_alb)) {
					if(photo.getPath().equals(deetz.getPath())) {
						deetz.resetCaption(caption.getText());
					}
				}
			}
		}
		
	}
}
