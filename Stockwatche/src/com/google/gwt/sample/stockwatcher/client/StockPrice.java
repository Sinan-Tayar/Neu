package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StockPrice implements Serializable{
	
	
	private StockPriceServiceAsync stockPriceSvc= null;
	private String symbol;
	private String price;
	private String change;
	
	public StockPrice() {
			  this.symbol = symbol;
			  this.price = price;
			  this.change = change;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}
	
	public double getChangePercent() {
		return 100.0;
	}
	
	private void refreshWatchList() {
		if(stockPriceSvc==null) {
			stockPriceSvc = GWT.create(StockPriceService.class);
		}
	}
	
	AsyncCallback<StockPrice[]>callback= new AsyncCallback<StockPrice[]>(){
		public void onFailure(Throwable caught) {
			
		}
	public void onSuccess(StockPrice[] prices) {
		
	}
	};
	
}
