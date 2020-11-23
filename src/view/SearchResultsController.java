package view;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SearchResultsController {
	/**
	 *  @author Nicolas Gundersen neg62
	 *  @author Sai Nayan Malladi srm275
	 *  The SearchDisplayController serves as a separate display for the serach results
	 * 
	 */
@FXML ScrollPane myscrollpane;
@FXML TilePane mytilepane;
@FXML AnchorPane anchorpane;

public ObservableList<ImageView> addedImagesref;
	public void start(Stage searchResultsStage) {
		for(ImageView imgview : addedImagesref) {
		StackPane background = new StackPane();
		mytilepane.getChildren().add(imgview);
		}		
		
		searchResultsStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				anchorpane.getChildren().clear();
				addedImagesref.clear();
			}
			
		
		
		});
		
	}

}
