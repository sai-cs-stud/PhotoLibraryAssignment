package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Set;

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
import javafx.scene.control.ButtonType;
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
	@FXML Button deletephotosbutton;
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
	
	public boolean isStock = false;
	private Stage mainStage;
	private Stage primaryStage;
	ObservableList<String> albobslist;
	ObservableList<String> albinfo_ObsList;
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
					boolean dupe = false;
					String selectedalbum = albumlistview.getSelectionModel().getSelectedItem();
					String myphotopath = newphoto.getAbsolutePath();

					try {
						ArrayList<ImageDetails> dupechecklist = detsDict.get(selectedalbum);
						for(ImageDetails imgdeetz : dupechecklist) {
							if(imgdeetz.getPath().equals(myphotopath)) {
								dupe = true;
								badinput.show();
							}
						}
						
					}catch(Exception io) {
						io.printStackTrace();
					}
					if(dupe == false) {
					Hashtable<String,ArrayList<String>> tags = new Hashtable<String,ArrayList<String>>();
					String caps = null;
					//getpath of photo I want to add
					//String img_date_time = sdf.format(newphoto.lastModified());
					Date lastmoddate = new Date(newphoto.lastModified());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(lastmoddate);
					calendar.set(Calendar.MILLISECOND, 0);
					System.out.println(calendar.getTime());
					System.out.println(newphoto.getAbsolutePath());
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

					albumlistview.setOnMouseClicked(new EventHandler<MouseEvent>() {
						
						@Override
						public void handle(MouseEvent mouseEvent) {
							mytilepane.getChildren().clear();
							String temp_selectedAlb = albumlistview.getSelectionModel().getSelectedItem();
							//int temp_selectedAlbindex = albumlistview.getSelectionModel().getSelectedIndex();
							System.out.println("clicked on album?");
							//albuminfo_listview.getSelectionModel().select(temp_selectedAlbindex);
							for(ImageDetails deetz: detsDict.get(temp_selectedAlb)) {
								int imgindex =0;
								for(ImageDetails master_dets : addedImageDetails) {
									if(master_dets==deetz) {
										mytilepane.getChildren().addAll(addedImages.get(imgindex));
									}
									imgindex++;
								}
							}
						}
					});
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
										Hashtable<String,ArrayList<String>> tagsfromimage = image.getTags();
										Set<String> tagkeys = tagsfromimage.keySet();
										ArrayList<String> tagarraylist = new ArrayList<String>();
										for(String key : tagkeys) {
											for(String keyspecs : tagsfromimage.get(key)) {
												String tagformat = key + ", " + keyspecs;
												tagarraylist.add(tagformat);
											}
										}
										temp_tags.addAll(tagarraylist);
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
			
	}
		else if(b==deletephotosbutton) {
			try {
				for (ImageView img: addedImages) {
					System.out.println(addedImages);
					if(img.getEffect() != null) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setContentText("Are you sure you want to delete this photo?");
						Optional<ButtonType> option = alert.showAndWait();
						if(option.get().equals(ButtonType.OK)) {
							System.out.println("Button = OK");
							String selectedalbum = albumlistview.getSelectionModel().getSelectedItem();
							int imindex = addedImages.indexOf(img);
							detsDict.get(selectedalbum).remove(imindex);
							addedImages.remove(img);
							addedImageDetails.remove(imindex);
							mytilepane.getChildren().remove(img);
							photocaption.setText("");
							taglistview.setItems(null);
							break;
							
						}
						else {
							break;
						}
					}
				}
			}
			catch(Exception io) {
				io.getStackTrace();
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
							// this runs before the image detail is updated in the controller for some reason
							for(ImageDetails newDets: detsDict.get(albumlistview.getSelectionModel().getSelectedItem())) {
								if(newDets.getPath().equals(deetz.getPath())) {
									System.out.println("check?");
									addedImageDetails.set(imindex, newDets);
									Calendar cal = newDets.getCal();
									Date capdatetime = cal.getTime();
									photocaption.setText(null);
									photocaption.setText("Last modified date: " + df.format(capdatetime) + "\n" + newDets.caption);
								}
							}
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
			
			albumlistview.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					String temp_selectedAlb = albumlistview.getSelectionModel().getSelectedItem();
					int temp_selectedAlbindex = albumlistview.getSelectionModel().getSelectedIndex();
					albuminfo_listview.getSelectionModel().select(temp_selectedAlbindex);
				}
			});
		}
		else if(b==addtagbutton) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Please make sure your tags are surrounded by spaces and have a space between the comma");
			
			if(newtaginput.getText().trim().isEmpty()) {
				badinput.show();
			}
			else {
				try {
				String newtag = newtaginput.getText().trim();
				String[] tagsegs = newtag.split(", ");
				String keytag = tagsegs[0];
				String spectag = tagsegs[1];
				//{person,sesh}
				int specchecker = spectag.length() - 1;
				if((keytag.charAt(0) != '{') || (spectag.charAt(specchecker) != '}')) {
					alert.show();

				}
				else {
				boolean checker = true;
				try {
					for(ImageView img : addedImages) {
						if(img.getEffect() != null) {
							int imindex = addedImages.indexOf(img);
							ImageDetails deetz = addedImageDetails.get(imindex);
							Hashtable<String,ArrayList<String>> mytags = deetz.getTags();
							ArrayList<String> emptyarray = new ArrayList<String>();
							if(mytags.isEmpty()) {
								mytags.put(keytag, emptyarray);
								mytags.get(keytag).add(spectag);
								checker = false;
							}
							else if(mytags.containsKey(keytag)) {
							for(String tag : mytags.get(keytag)) {
								if(spectag.equals(tag)) {
									checker = false;
									badinput.show();
								}
							}
						}
							if(checker != false) {
								if(mytags.containsKey(keytag)) {
								mytags.get(keytag).add(spectag);
								}
								else{
									mytags.put(keytag, emptyarray);
									mytags.get(keytag).add(spectag);
								}
							}
	
						
							ObservableList<String> tagobslist = FXCollections.observableArrayList();
							Hashtable<String,ArrayList<String>> updatetags = deetz.getTags();
							Set<String> tagkeys = updatetags.keySet();
							ArrayList<String> tagarraylist = new ArrayList<String>();
							for(String key : tagkeys) {
								for(String keyspecs : updatetags.get(key)) {
									String tagformat = key + ", " + keyspecs;
									tagarraylist.add(tagformat);
								}
							}
							tagobslist.addAll(tagarraylist);
							taglistview.setItems(tagobslist);
						}
						
					}
				}
				catch(Exception io) {
					io.printStackTrace();
				}
			}
			
		}catch(ArrayIndexOutOfBoundsException io) {
			alert.show();
		}
		}
	}
		else if(b == deletetagbutton) {
			try {
				for(ImageView img: addedImages) {
					if(img.getEffect() != null) {
						int imindex = addedImages.indexOf(img);
						ImageDetails deetz = addedImageDetails.get(imindex);
						Hashtable<String,ArrayList<String>> deletabletags = deetz.getTags();
						if(deletabletags != null) {
						String tagname = taglistview.getSelectionModel().getSelectedItem();
						String[] tagspliced = tagname.split(", ");
						String keytag = tagspliced[0];
						String spectag = tagspliced[1];
						//deletabletags.remove(tagname);
						deletabletags.get(keytag).remove(spectag);
						ArrayList<String> arraytaglist = deletabletags.get(keytag);
						if(arraytaglist.isEmpty()) {
							deletabletags.remove(keytag);
						}
						
						taglistview.getItems().remove(tagname);				
						
						}
					}
				}
								
				
			}
			catch(Exception io) {
				io.printStackTrace();
			}
		}
		else if(b == slideshowbutton) {
			String albumname = albumlistview.getSelectionModel().getSelectedItem();
			ArrayList<ImageDetails> photolist = detsDict.get(albumname);
			System.out.println(photolist);
			if(photolist.isEmpty()) {
				Alert nophotos = new Alert(AlertType.ERROR);
				nophotos.setContentText("No photos bruh!");
				nophotos.show();
			}else {
			slideshowDisplay(photolist);
			}
		}
		else if(b == copymovebutton) {
			String alname = albumlistview.getSelectionModel().getSelectedItem();
			for(ImageView img: addedImages) {
				if(img.getEffect() != null) {
				int imindex = addedImages.indexOf(img);
				ImageDetails deetz = addedImageDetails.get(imindex);
				copymoveDisplay(alname, img, deetz, detsDict);
				break;
				}
			}
			
		}
		else if(b == searchbutton) {
			searchDisplay(detsDict);
		}
	}

	private void add_stock(String path) throws FileNotFoundException {
		Alert badinput = new Alert (AlertType.ERROR);
		badinput.setContentText("bad input");
		File newphoto = new File(path);
		if(newphoto != null) {
				String selectedalbum = "Stock";
				String myphotopath = newphoto.getAbsolutePath();
				Hashtable<String,ArrayList<String>> tags = new Hashtable<String,ArrayList<String>>();
				String caps = null;

				Date lastmoddate = new Date(newphoto.lastModified());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(lastmoddate);
				calendar.set(Calendar.MILLISECOND, 0);
				System.out.println(calendar.getTime());
				System.out.println(newphoto.getAbsolutePath());
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
				albumlistview.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						mytilepane.getChildren().clear();
						String temp_selectedAlb = "Stock";
						for(ImageDetails deetz: detsDict.get(temp_selectedAlb)) {
							int imgindex =0;
							for(ImageDetails master_dets : addedImageDetails) {
								if(master_dets==deetz) {
									mytilepane.getChildren().addAll(addedImages.get(imgindex));
								}
								imgindex++;
							}
						}
						
					}
				});
			newimage.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
						selectedImage = newimage;
						newimage.setEffect(new DropShadow(15, Color.BLACK));
						String temp_selectedalbum = "Stock";
						for(ImageDetails image: detsDict.get(temp_selectedalbum)) {
							int imindex = addedImages.indexOf(newimage);
							ImageDetails deetz = addedImageDetails.get(imindex);
							if(deetz.getPath().equals(image.getPath())) {
								//System.out.println("works?");
								Calendar cal = deetz.getCal();
								Date capdatetime = cal.getTime();
								photocaption.setText(null);
								taglistview.setItems(null);
								photocaption.setText("Last modified date: " + df.format(capdatetime) + "\n" + image.caption);
								ObservableList<String> temp_tags = FXCollections.observableArrayList();
								if(image.getTags()!=null) {
									Hashtable<String,ArrayList<String>> tagsfromimage = image.getTags();
									Set<String> tagkeys = tagsfromimage.keySet();
									ArrayList<String> tagarraylist = new ArrayList<String>();
									for(String key : tagkeys) {
										for(String keyspecs : tagsfromimage.get(key)) {
											String tagformat = key + ", " + keyspecs;
											tagarraylist.add(tagformat);
										}
									}
									temp_tags.addAll(tagarraylist);
									taglistview.setItems(temp_tags);
								}
							}}
						for(ImageView image: addedImages) {
							if(image!=newimage) {
								if(image.getEffect()!=null) {
									image.setEffect(null);
								}
							}
						}
					}
					else {
						newimage.setEffect(null);
						photocaption.setText("");
						taglistview.setItems(null);
					}
				}
			});
			albumlistview.getSelectionModel().select(0);
			albuminfo_listview.getSelectionModel().select(0);
	}
}
public void start(Stage mainStage) {
	// TODO Auto-generated method stub
	this.mainStage = mainStage;
	albobslist = FXCollections.observableArrayList();
	albinfo_ObsList = FXCollections.observableArrayList();
	photocaption.setEditable(false);
	photocaption.setWrapText(true);
	if(isStock == true) {
		try {
		ArrayList <ImageDetails> stock = new ArrayList<ImageDetails>();
		detsDict.put("Stock", stock);
		albobslist.add("Stock");
		albinfo_ObsList.add("filler for now");
		albumlistview.setItems(albobslist);
		albuminfo_listview.setItems(albinfo_ObsList);
		add_stock("data/deer.jpg");
		add_stock("data/some_map.PNG");
		add_stock("data/street_cat.jpg");
		add_stock("data/Telugu.PNG");
		add_stock("data/very_handsome_guy.jpg");
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
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
		//System.out.println("editmenu?");
		//this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
		getClass().getResource("/view/editCaption.fxml"));
		Stage displayEditMenu = loader.load();
		EditCaptionController ecc = loader.getController();
		displayEditMenu.setTitle("Edit Image's Caption");
		ecc.photo = imagedetails;
		ecc.textarearef = photocaption;
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
		albumcontroller.albuminfo_list = albuminfo_listview;
		albumcontroller.albuminfo_ObsList = albinfo_ObsList;
		albumcontroller.start(albumstage);
		albumstage.show();
		
	}
	private void slideshowDisplay(ArrayList<ImageDetails> imgdeetz) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/slideshow.fxml"));
		Stage slideshowstage = (Stage)loader.load();
		slideshowController slideshowcon = loader.getController();
		slideshowstage.setTitle("Slideshow");
		slideshowcon.albumphotos = imgdeetz;
		slideshowcon.start(slideshowstage);
		slideshowstage.show();
	}
	private void copymoveDisplay(String albumname, ImageView imageview, ImageDetails photo,
			Hashtable<String,ArrayList<ImageDetails>> deetzDict) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/copymove.fxml"));
		Stage copymovestage = (Stage)loader.load();
		CopyMoveController copymovecon = loader.getController();
		copymovestage.setTitle("Copy/Move?");
		copymovecon.tilepaneref = mytilepane;
		copymovecon.albumref = albumname;
		copymovecon.imgviewref = imageview;
		copymovecon.imagedetailsref = photo;
		copymovecon.deetzdictref = deetzDict;
		copymovecon.start(copymovestage);
		copymovestage.show();
		
	}
	private void searchDisplay(Hashtable<String,ArrayList<ImageDetails>> allthedeetz) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/search.fxml"));
		Stage searchStage = (Stage)loader.load();
		SearchController searchcon = loader.getController();
		searchStage.setTitle("Search");
		searchcon.deetzdictref = allthedeetz;
		searchcon.start(searchStage);
		searchStage.show();
	}
	
	
	
}
