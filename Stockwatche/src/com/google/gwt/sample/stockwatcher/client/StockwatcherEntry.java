package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.sample.stockwatcher.shared.bo.User;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


import com.google.gwt.sample.stockwatcher.client.gui.Header;
import com.google.gwt.sample.stockwatcher.client.gui.LandingPage;
import com.google.gwt.sample.stockwatcher.client.gui.Navigator;
import com.google.gwt.sample.stockwatcher.client.gui.Notification;
import com.google.gwt.sample.stockwatcher.client.gui.RegistrationForm;
import com.google.gwt.sample.stockwatcher.shared.LoginServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockwatcherEntry implements EntryPoint {
	
	// Objekt, das die Anmeldeinformation des Benutzerdienstes enthält //privat

	private LoginServiceAsync loginService = null;
	
	/**
	 * This is the entry point method.
	 */
	
	Header header = new Header();
	RootPanel rootPanelHeader = RootPanel.get("header");

	Navigator navigator = new Navigator();
	RootPanel rootPanelNavigator = RootPanel.get("navigator");

	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label ("Melden Sie sich in Ihrem Google-Konto an");
	private Button loginButton = new Button ("Einloggen");
	private Anchor signInLink = new Anchor("Einlogen");

	
	public void onModuleLoad() {

		loginLabel.addStyleName("profilTitle");	


		rootPanelHeader.add(header);
		rootPanelNavigator.add(navigator);
		
		loginService = ClientsideSettings.getLoginService();
		loginService.login(GWT.getHostPageBaseURL() + "Stockwatche.html", new loginServiceCallback());


	}	
	
		private class loginServiceCallback implements AsyncCallback<User> {

			@Override
			public void onFailure(Throwable caught) {
			//Notification.show(caught.toString());
				Window.alert("Test: " + caught.toString());			
			}

			@Override
			public void onSuccess(User u) {
			CurrentUser.setUser(u);
			
			if (u.isLoggedIn()) {
				if (u.getName() == null) {
					Anchor stockwatcheEditorLink = new Anchor();
					stockwatcheEditorLink.setHref(GWT.getHostPageBaseURL() + "Stockwatche.html");
			//		RootPanel.get("navigator").setVisible(false);
			//		RootPanel.get("header").setVisible(false);
			//		RootPanel.get("footer").setVisible(false);
					RootPanel.get("details").add(new RegistrationForm(stockwatcheEditorLink, u));
			}else {
				
				Header header = new Header();
				RootPanel rootPanelHeader = RootPanel.get("header");

				Navigator navigator = new Navigator();
				RootPanel rootPanelNavigator = RootPanel.get("navigator");
				
				LandingPage landingPage = new LandingPage();
				RootPanel rootPanelLandingPage = RootPanel.get("details");

				rootPanelHeader.add(header);
				rootPanelNavigator.add(navigator);
				rootPanelLandingPage.add(landingPage);
			}
			}else {
				loadLogin();
			}	
		}
		}	 
		 /**
			 * Diese Methode wird aufgerufen, falls der User nicht am System eingeloggt ist
			 * In dieser wird die Google LoginMaske über den Button
			 * <code>loginButton </code> aufgerufen
			 */
		 private void loadLogin() {
			 loginPanel.setSpacing(10);
			//	loginPanel.setWidth("100vw");
				loginPanel.add(loginLabel);
				loginPanel.add(loginButton);
				loginPanel.setCellHorizontalAlignment(loginLabel, HasHorizontalAlignment.ALIGN_CENTER);
				loginPanel.setCellHorizontalAlignment(loginButton, HasHorizontalAlignment.ALIGN_CENTER);
				signInLink.setHref(CurrentUser.getUser().getLoginUrl());

				RootPanel.get("header").setVisible(false);
				RootPanel.get("navigation").setVisible(false);
				RootPanel.get("footer").setVisible(false);
				RootPanel.get("details").add(loginPanel);

				loginLabel.setStylePrimaryName("loginLabel");
				loginButton.setStylePrimaryName("speicherProfilButton");

				/**
				 * Durch einen Klick auf den <code>loginButton</code> wird der User auf die
				 * Google LoginMaske weitergeleitet
				 */
				loginButton.addClickHandler(new LoginClickHandler());	 
		 }		 
		private class LoginClickHandler implements ClickHandler {

				@Override
				public void onClick(ClickEvent event) {

					Window.open(signInLink.getHref(), "_self", "");
				}
			}		
		
		public static class CurrentUser {

			private static User u = null;

			public static User getUser() {
				return u;
			}
			public static void setUser(User u) {
				CurrentUser.u = u;
			}
	}		
}		
		


