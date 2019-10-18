package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.sample.stockwatcher.shared.CommonSettings;

public class ClientsideSettings extends CommonSettings {

	/** 
	*Remote Service Proxy zur Verbindungsaufnahme mit dem Server
	*/
	private static StockPriceServiceAsync stockprice = null;
	
	
	public static StockPriceServiceAsync getStockprice() {
		
		if(stockprice==null) {
			stockprice= GWT.create(StockPriceService.class);
		}
		return stockprice;
		
	}
	
}
