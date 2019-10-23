package com.google.gwt.sample.stockwatcher.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class LandingPage extends FlowPanel{

	
Image image;
	
	public void onLoad() {
		image = new Image();
		image.setUrl("/image/a.png");
		
		image.setWidth("35%");
		image.setHeight("35%");
		this.add(image);
		
	}
	
}
