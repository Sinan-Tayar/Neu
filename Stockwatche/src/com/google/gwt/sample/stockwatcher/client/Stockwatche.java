package com.google.gwt.sample.stockwatcher.client;


import java.util.ArrayList;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Stockwatche implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private StockPriceServiceAsync stockPriceSvc=null;
	
	/**
	 * This is the entry point method.
	 */
	private static final int REFRESH_INTERVAL=5000;
	
	TextBox inhalt = new TextBox();
	FlexTable flex = new FlexTable();
	Button add = new Button("Add");
	HorizontalPanel hp = new HorizontalPanel();
	VerticalPanel vp = new VerticalPanel();
	public ArrayList<String> aktien = new ArrayList<String>();
	int row = flex.getRowCount();
	public String symbol;
	Button remove= new Button("X");
	private Label test = new Label ("Was geht");

	
	public void onModuleLoad() {

	test.setStyleName("test");

		Timer refreshTimer = new Timer() {
			public void run() {
				
				refreshWatchList();
			}
			private void refreshWatchList() {	
		
		
			}
			
			
		};
		
		refreshTimer.scheduleRepeating(5000);
			
		flex.setText(0, 0, "Symbol");
		flex.setText(0, 1, "Price");
		flex.setText(0, 2, "Change");
		flex.setText(0, 3, "Remove");	
		hp.add(flex);
		vp.add(inhalt);
		vp.add(add);
		vp.add(test);

		RootPanel.get("stylesheet").add(hp);
		RootPanel.get().add(vp);
		RootPanel.get("Name").add(test);
	
		add.addClickHandler(new ClickHandler() {
			
		public void onClick(ClickEvent event) {
				  addStock();	  
			}
		private void addStock() {
			
			final String symbol = inhalt.getText().toUpperCase().trim();
			final Button remove= new Button("X");
			int row = flex.getRowCount();
		    inhalt.setFocus(true);
			if(aktien.contains(symbol)) return;
		    aktien.add(symbol);
		    flex.setText(row, 0, symbol);
			flex.setWidget(row, 3, remove);	 
			
			remove.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					addButton();
				}
				private void addButton() {
				flex.removeRow(1);		
				}
			});	 
	 }	
	});
	}
}
	


