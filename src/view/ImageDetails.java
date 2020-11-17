package view;

import java.util.Calendar;

public class ImageDetails {
	String image_path;
	Calendar date_time;
	String Caption;
	String[] tags;
	public ImageDetails(String image_path, Calendar date_time, String caption, String[] tags) {
		this.image_path = image_path;
		this.date_time = date_time;
		this.Caption = caption;
		this.tags = tags;
	}
	public void resetImagePath(String image_path) {
		this.image_path = image_path;
	}
	public void resetDate_Time(Calendar date_time) {
		this.date_time = date_time;
	}
	public void resetCaption(String caption) {
		this.Caption = caption;
	}
	public void resetTags(String[] tags) {
		this.tags = tags;
	}
}
