package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


@SuppressWarnings("unused")
public class MainAppController {
	
	@FXML ListView<String> albumlistview;
	@FXML ListView<String> albuminfo_listview;
	@FXML ListView<String> taglistview;
	@FXML Button editcaptionbutton;
	@FXML Button deletetagbutton;
	@FXML Button addtagbutton;
	@FXML Button addphotobutton;
	@FXML Button deletephotobutton;
	@FXML Button editalbumbutton;
	@FXML Button slideshowbutton;
	@FXML Button searchbutton;
	@FXML Button logoutbutton;
	@FXML Button copymovebutton;
	@FXML Button displayphotosbutton;
	@FXML TextArea photocaption;
	@FXML TextField newtaginput;
	@FXML ScrollPane myscrollpane;
	@FXML TilePane mytilepane;
	
	
	private Stage mainStage;
	private Stage primaryStage;
	ObservableList<String> albobslist;
	ImageView selectedImage;
	ObservableList<ImageView> addedImages= FXCollections.observableArrayList();
	ObservableList<ImageDetails> addedImageDetails = FXCollections.observableArrayList();
	Hashtable<String,ArrayList<ImageDetails>> detsDict = new Hashtable<String,ArrayList<ImageDetails>>();
	
	FileChooser fil_chooser = new FileChooser();
	
	String pattern = "MM/dd/yyyy HH:mm:ss";
	SimpleDateFormat df = new SimpleDateFormat(pattern);
	
	
	
	@FXML
	protected void buttonPress(ActionEvent event) throws IOException {
		Alert badinput = new Alert (AlertType.ERROR);
		badinput.setContentText("bad input");
		
		Button b = (Button)event.getSource();
		if(b==logoutbutton) {
			// code to maintain current state here
			LoginStage(mainStage);
		}
		else if(b==addphotobutton) {
			Alert albumissue = new Alert(AlertType.ERROR);
			albumissue.setContentText("You need to have an album selected");
			File newphoto = fil_chooser.showOpenDialog(mainStage);
			
			if(newphoto != null) {
		
				if(albobslist.isEmpty()) {
					albumissue.show();
				}
				else if(albumlistview.getSelectionModel().isEmpty()) {
					albumissue.show();
				}
				else {	
					String selectedalbum = albumlistview.getSelectionModel().getSelectedItem();
					ArrayList<String> tags = new ArrayList<String>();
					String caps = null;
					//getpath of photo I want to add
					//String img_date_time = sdf.format(newphoto.lastModified());
					Date lastmoddate = new Date(newphoto.lastModified());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(lastmoddate);
					calendar.set(Calendar.MILLISECOND, 0);
					System.out.println(calendar.getTime());
					System.out.println(newphoto.getAbsolutePath());
					String myphotopath = newphoto.getAbsolutePath();
					//create an imagedetail with all the properties
					ImageDetails newimagedetails = new ImageDetails(myphotopath, calendar, caps, tags);
					addedImageDetails.add(newimagedetails);
					
					File photofile = new File(myphotopath);
					Image myphoto = new Image(new FileInputStream(photofile),150, 0, true, true);
					StackPane background = new StackPane();
					ImageView newimage = new ImageView(myphoto);
					newimage.setFitWidth(150);
					newimage.setFitHeight(mainStage.getHeight() - 10);
					newimage.setPreserveRatio(true);
					newimage.setSmooth(true);
					newimage.setCache(true);
					
					mytilepane.setPadding(new Insets(15, 15, 15, 15));
					mytilepane.setHgap(15);
					mytilepane.getChildren().addAll(newimage);
					addedImages.add(newimage);
					
					detsDict.get(selectedalbum).add(newimagedetails);
					System.out.println("Album contents:" + Arrays.toString(detsDict.get(selectedalbum).toArray()));
				
				newimage.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent mouseEvent) {
						// TODO Auto-generated method stub
						if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
							selectedImage = newimage;
							newimage.setEffect(new DropShadow(15, Color.BLACK));
							// find image in hashtable by traversing, and matching to corresponding imgdetsobslist 
							// then extract date, caption, and tags
							String temp_selectedalbum = albumlistview.getSelectionModel().getSelectedItem();
							for(ImageDetails image: detsDict.get(temp_selectedalbum)) {
								int imindex = addedImages.indexOf(newimage);
								//System.out.println("imindex:"+ imindex);
								ImageDetails deetz = addedImageDetails.get(imindex);
								//System.out.println(deetz.getPath());
								//System.out.println(image.getPath());
								if(deetz.getPath().equals(image.getPath())) {
									//System.out.println("works?");
									Calendar cal = deetz.getCal();
									Date capdatetime = cal.getTime();
									photocaption.setText(null);
									taglistview.setItems(null);
									photocaption.setText("Last modified date: " + df.format(capdatetime) + "\n" + image.caption);
									ObservableList<String> temp_tags = FXCollections.observableArrayList();
									if(image.getTags()!=null) {
										temp_tags.addAll(image.getTags());
										taglistview.setItems(temp_tags);
									}
								}
							}
							//int i =0;
							for(ImageView image: addedImages) {
								//i++;
								if(image!=newimage) {
									if(image.getEffect()!=null) {
										image.setEffect(null);
									}
								}
								/*if(image==newimage){
									ImageDetails imgDets = addedImageDetails.get(i);
								}*/
								}
						}
						else {
							newimage.setEffect(null);
							photocaption.setText("");
							taglistview.setItems(null);
						}
					
					}
					
				});
				}
			
				
					
			}
			
		}
		else if(b==editcaptionbutton) {
			if(selectedImage==null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.show();
				return;
			}
			else {
				try {
					for(ImageView img : addedImages) {
						if(img.getEffect() != null) {
							int imindex = addedImages.indexOf(img);
							ImageDetails deetz = addedImageDetails.get(imindex);
							displayEditCaptionMenu(deetz);
							
						}
					}
				}
				catch(Exception io) {
					io.printStackTrace();
				}
			}
		}
		else if(b==displayphotosbutton) {
			try {
				for(ImageView img : addedImages) {
					if(img.getEffect() != null) {
						int imindex = addedImages.indexOf(img);
						ImageDetails deetz = addedImageDetails.get(imindex);
						displayPhotoStage(deetz);
					}
				}
			}
			catch(Exception io) {
				io.printStackTrace();
			}
			
			
		}
		else if(b==editalbumbutton) {
			displayAlbumMenu();
			
			albobslist.addListener(new ListChangeListener<String>() {
				@Override
				 public void onChanged(
					        javafx.collections.ListChangeListener.Change<? extends String> c) {
					        while(c.next()) {
					        	if(c.wasReplaced()) {
					        		System.out.println("Removed: " + c.getRemoved());
					        		System.out.println("Added: " + c.getAddedSubList());
					        		ArrayList<ImageDetails> value = detsDict.get(c.getRemoved().get(0));
					        		detsDict.remove(c.getRemoved().get(0));
					        		detsDict.put(c.getAddedSubList().get(0), value);
					        		
					        	}
					        	else if(c.wasAdded()) {
					        		System.out.println("Added: " + c.getAddedSubList());
					        		ArrayList<ImageDetails> value = new ArrayList<ImageDetails>();
					        		detsDict.put(c.getAddedSubList().get(0), value);
					        	}
					        	else if(c.wasRemoved()) {
					        		System.out.println("Removed: " + c.getRemoved());
					        		detsDict.remove(c.getRemoved().get(0));
					        	}
					        	
					        }
					        System.out.println("Dictionary Keys:" + detsDict.keySet());
					    }
				
				
			});
		}
		else if(b==addtagbutton) {
			
			if(newtaginput.getText().trim().isEmpty()) {
				badinput.show();
			}
			else {
				String newtag = newtaginput.getText();
				boolean checker = true;
				try {
					for(ImageView img : addedImages) {
						if(img.getEffect() != null) {
							int imindex = addedImages.indexOf(img);
							ImageDetails deetz = addedImageDetails.get(imindex);
							ArrayList<String> mytags = deetz.getTags();
							if(mytags.isEmpty()) {
								mytags.add(newtag);
							}
							else {
							for(String tag : mytags) {
								if(newtag.equals(tag)) {
									checker = false;
									badinput.show();
								}
							}
							if(checker != false) {
								mytags.add(newtag);
								
							}
	
						}
							ObservableList<String> tagobslist = FXCollections.observableArrayList();
							ArrayList<String> updatetags = deetz.getTags();
							tagobslist.addAll(updatetags);
							taglistview.setItems(tagobslist);
						}
						
					}
				}
				catch(Exception io) {
					io.printStackTrace();
				}
			}
			
		}
		else if(b == deletetagbutton) {
			try {
				for(ImageView img: addedImages) {
					if(img.getEffect() != null) {
						int imindex = addedImages.indexOf(img);
						ImageDetails deetz = addedImageDetails.get(imindex);
						ArrayList<String> deletabletags = deetz.getTags();
						String tagname = taglistview.getSelectionModel().getSelectedItem();
						deletabletags.remove(tagname);
						taglistview.getItems().remove(tagname);				
					}
				}
								
				
			}
			catch(Exception io) {
				io.printStackTrace();
			}
		}
	}

	
	public void start(Stage mainStage) {
		// TODO Auto-generated method stub
		this.mainStage = mainStage;
		albobslist = FXCollections.observableArrayList();
		photocaption.setEditable(false);

		
	}
	private void LoginStage(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
				getClass().getResource("/view/login.fxml"));
		VBox root = (VBox)loader.load();
		//load our controller into the primary stage
		PhotoController pController = 
				loader.getController();
		pController.start(primaryStage);
		//set the scene
		Scene scene = new Scene(root, 600, 420);
		//make scene primary stage - dont make resizable
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show(); 
		primaryStage.setResizable(false);

	}
	private void displayPhotoStage(ImageDetails imagedetails) throws IOException{
		//this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
		getClass().getResource("/view/displayphoto.fxml"));
		Stage displayStage = (Stage)loader.load();
		DisplayPhotoController dpc = loader.getController();
		displayStage.setTitle("Display");
		dpc.photo = imagedetails;
		//primaryStage.setResizable(false);
		dpc.start(displayStage);
		displayStage.show();

	}
	private void displayEditCaptionMenu(ImageDetails imagedetails) throws IOException{
		//this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
		getClass().getResource("/view/editCaption.fxml"));
		Stage displayEditMenu = loader.load();
		EditCaptionController ecc = loader.getController();
		displayEditMenu.setTitle("Edit Image's Caption");
		ecc.photo = imagedetails;
		displayEditMenu.setResizable(false);
		ecc.ecc_detsDict = detsDict;
		ecc.curr_alb = albumlistview.getSelectionModel().getSelectedItem();
		ecc.start(displayEditMenu);
		displayEditMenu.show();
		detsDict = ecc.ecc_detsDict;
	}
	private void displayAlbumMenu() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/albummenu.fxml"));
		Stage albumstage = (Stage)loader.load();
		AlbumController albumcontroller = loader.getController();
		albumstage.setTitle("Album menu");
		albumcontroller.albumlist = albumlistview;
		albumcontroller.albumobslist = albobslist;
		albumcontroller.start(albumstage);
		albumstage.show();
		
	}
	
	
	
}
