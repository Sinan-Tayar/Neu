package com.google.gwt.sample.stockwatcher.client.gui;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class Notification {

	private static Label notificationLabel = new Label();
	private static boolean isActive = false;
	
	public static void show(String message) {
		isActive = true;
		notificationLabel.setText(message);
		notificationLabel.setStyleName("errorLabel");
		RootPanel.get("header").add(notificationLabel);

		// timer definiert die Anzeigezeit des Fehlers.
		final Timer timer = new Timer() {
			public void run() {
				RootPanel.get("header").remove(RootPanel.get("header").getWidgetIndex(notificationLabel));
			}
		};
		// Definert die Sekunden, nachdem die Methode aufgerufen wird.
		timer.schedule(5000);
	}
}
