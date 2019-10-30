package com.google.gwt.sample.stockwatcher.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.sample.stockwatcher.shared.CommonSettings;
import com.google.gwt.sample.stockwatcher.shared.LoginService;
import com.google.gwt.sample.stockwatcher.shared.LoginServiceAsync;



public class ClientsideSettings extends CommonSettings {

	
	/**
	 * Name des Client-seitigen Loggers.
	 */
	private static final String LOGGER_NAME = "CineMates Web Client";

	/**
	 * Instanz des Client-seitigen Loggers.
	 */

	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	/** 
	*Remote Service Proxy zur Verbindungsaufnahme mit dem Server
	*/
	private static StockPriceServiceAsync stockprice = null;
	
	private static LoginServiceAsync loginService = null;
	
	
	public static StockPriceServiceAsync getStockprice() {
		
		if(stockprice==null) {
			stockprice= GWT.create(StockPriceService.class);
		}
		return stockprice;
			
	}
	
	/**
	 * Auslesen des applikationsweiten client-seitig zentralen Loggers
	 * 
	 * @return Logger-Instanz
	 */
	public static Logger getLogger() {
		return log;
	}
	
	public static LoginServiceAsync getLoginService() {
		if (loginService == null) {
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}

}
