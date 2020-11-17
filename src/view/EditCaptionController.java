package view;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EditCaptionController {
	@FXML TilePane photopane;
	@FXML Button confirmEdit;
	@FXML TextField input;
	@FXML Label caption;
	
	private Stage mainstage;
	
private Stage mainStage;
	
	ImageView photo;
	public void start(Stage mainStage, ImageView photo) {
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
