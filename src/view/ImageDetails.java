package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

public class ImageDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String image_path;
	Calendar date_time;
	String caption;
	Hashtable<String,ArrayList<String>> tags;
	public ImageDetails(String image_path, Calendar date_time, String caption, Hashtable<String,ArrayList<String>> tags) {
		this.image_path = image_path;
		this.date_time = date_time;
		this.caption = caption;
		this.tags = tags;
	}
	public void resetImagePath(String image_path) {
		this.image_path = image_path;
	}
	public void resetDate_Time(Calendar date_time) {
		this.date_time = date_time;
	}
	public void resetCaption(String caption) {
		this.caption = caption;
	}
	public void resetTags(Hashtable<String,ArrayList<String>> tags) {
		this.tags = tags;
	}
	public String getPath() {
		return image_path;
		
	}
	public Calendar getCal() {
		return date_time;
	}
	public String getCaption() {
		return caption;
	}
	public Hashtable<String,ArrayList<String>> getTags() {
		return tags;
	}
}
