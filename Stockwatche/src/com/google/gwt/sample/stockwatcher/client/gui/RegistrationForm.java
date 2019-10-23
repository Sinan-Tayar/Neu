package com.google.gwt.sample.stockwatcher.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.sample.stockwatcher.client.ClientsideSettings;
import com.google.gwt.sample.stockwatcher.client.StockPriceServiceAsync;
import com.google.gwt.sample.stockwatcher.shared.bo.User;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;




public class RegistrationForm extends FlowPanel {
	
	private StockPriceServiceAsync sps = ClientsideSettings.getStockprice();
	
	private User user;
	
	private FlowPanel registrationsBox = new FlowPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	private Label welcomeLabel = new Label("Wilkommen bei Cinemaxx");
	private Label infoLabel = new Label("Bitte registrieren Sie sich: ");
	private Label nameLabel = new Label("Name: ");
	private Label usernameLabel = new Label("Username: ");
	
	private DynamicTextBox nameTextbox = new DynamicTextBox();
	private DynamicTextBox usernameTextbox = new DynamicTextBox();

	private Button registrationButton = new Button("Registrieren");
	private Button cancelButton = new Button("Abbrechen");

	private Grid registrationGrid = new Grid(2, 2);

	// dient der Weiterleitung
	private Anchor destinationUrl = new Anchor();
	
	public RegistrationForm(Anchor destinationUrl, User user) {
		this.destinationUrl = destinationUrl;
		this.user = user;

		registrationButton.addClickHandler(new RegistrationClickHandler());
		cancelButton.addClickHandler(new CancelClickHandler());
	}

	private class RegistrationClickHandler implements ClickHandler{
	public void onClick(ClickEvent event) {
		
		String name = nameTextbox.getText();
		String uName = usernameTextbox.getText();
		user.setName(name);
		user.setUsername(uName);
		Window.alert(user.getName()+ user.getUsername());
		
		sps.save(user, new SaveUserCallback());	
	}
	}
	
	
	private class CancelClickHandler implements ClickHandler{
	public void onClick(ClickEvent event) {
		RootPanel.get("details").clear();
		Window.open(user.getLogoutUrl(), "_self", "");
	}
	}
	
	public void onLoad() {
		
		registrationsBox.addStyleName("profilBox");
		welcomeLabel.addStyleName("profilTitle");
		infoLabel.addStyleName("profilLabel");
		registrationGrid.addStyleName("profilTextBox");
		buttonPanel.addStyleName("profilLabel");
		
		buttonPanel.setSpacing(20);
		registrationGrid.setCellSpacing(15);

		nameTextbox.getElement().setPropertyString("placeholder", "Dein Name...");
		usernameTextbox.getElement().setPropertyString("placeholder", "Dein Username...");
		
		registrationGrid.setWidget(0, 0, nameLabel);
		registrationGrid.setWidget(0, 1, nameTextbox);
		registrationGrid.setWidget(1, 0, usernameLabel);
		registrationGrid.setWidget(1, 1, usernameTextbox);
		
		buttonPanel.add(cancelButton);
		buttonPanel.add(registrationButton);
		
		registrationsBox.add(welcomeLabel);
		registrationsBox.add(infoLabel);
		registrationsBox.add(registrationGrid);
		registrationsBox.add(buttonPanel);

		this.add(registrationsBox);
	}
	
	private class DynamicTextBox extends TextBox{
		
	}
	
	private class SaveUserCallback implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			Notification.show(caught.toString());
		}

		@Override
		public void onSuccess(Void result) {
			Window.Location.reload();
			
		}
		
	}
}
