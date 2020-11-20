package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SearchResultsController {
@FXML Pane mypane;
@FXML AnchorPane anchorpane;

public ObservableList<ImageView> addedImagesref;
	public void start(Stage searchResultsStage) {
		mypane.getChildren().addAll(addedImagesref);
		
		
		searchResultsStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent we) {
				anchorpane.getChildren().clear();
				System.out.println("does this worksssss");
			}
			
		
		
		});
		
	}

}
