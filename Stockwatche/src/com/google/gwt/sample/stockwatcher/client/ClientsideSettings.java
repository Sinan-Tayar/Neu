package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.sample.stockwatcher.shared.CommonSettings;
import com.google.gwt.sample.stockwatcher.shared.LoginService;
import com.google.gwt.sample.stockwatcher.shared.LoginServiceAsync;



public class ClientsideSettings extends CommonSettings {

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
	
	
	public static LoginServiceAsync getLoginService() {
		if (loginService == null) {
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}

}
