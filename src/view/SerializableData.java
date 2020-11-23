package view;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Hashtable;


public class SerializableData implements Serializable{

	/**
	 * @author Sai Nayan Malladi srm275
	 * @author Nicolas Gundersen neg62
	 * 
	 * {@summary} The serialzeddData class serves as a holder for all of our Serialized Objects
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
