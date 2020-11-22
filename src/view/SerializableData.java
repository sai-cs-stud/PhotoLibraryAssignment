package view;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Hashtable;


public class SerializableData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String login = "";
	ArrayList<String> albumlistview = new ArrayList<String>();
	ArrayList<String> albuminfo_listview = new ArrayList<String>();
	ArrayList<ImageDetails> addedImagesDetails = new ArrayList<ImageDetails>();
	Hashtable<String,ArrayList<ImageDetails>> dets_Dict = new Hashtable<String,ArrayList<ImageDetails>>();
	
	
	public SerializableData(String login, ArrayList<String> albumlistview, ArrayList<String> albuminfo_listview,
			 ArrayList<ImageDetails> addedImageDetails, Hashtable<String, ArrayList<ImageDetails>> dets_Dict) {
		this.albumlistview = albumlistview;
		this.albuminfo_listview = albuminfo_listview;
		this.addedImagesDetails = addedImageDetails;
		this.dets_Dict = dets_Dict;
	}
	
}
