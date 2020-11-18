package view;

import java.util.ArrayList;
import java.util.Calendar;

public class ImageDetails {
	String image_path;
	Calendar date_time;
	String caption;
	ArrayList<String> tags;
	public ImageDetails(String image_path, Calendar date_time, String caption, ArrayList<String> tags) {
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
	public void resetTags(ArrayList<String> tags) {
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
	public ArrayList<String> getTags() {
		return tags;
	}
}
