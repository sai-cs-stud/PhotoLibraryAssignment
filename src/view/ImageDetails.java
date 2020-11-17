package view;

import java.util.Date;

public class ImageDetails {

	Date date_time;
	String Caption;
	String[] tags;
	public ImageDetails(Date date_time, String caption, String[] tags) {
		this.date_time = date_time;
		this.Caption = caption;
		this.tags = tags;
	}
	public void resetDate_Time(Date date_time) {
		this.date_time = date_time;
	}
	public void resetCaption(String caption) {
		this.Caption = caption;
	}
	public void resetTags(String[] tags) {
		this.tags = tags;
	}
}
