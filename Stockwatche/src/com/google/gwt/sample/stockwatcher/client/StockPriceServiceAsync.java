package com.google.gwt.sample.stockwatcher.client;
import com.google.gwt.sample.stockwatcher.shared.bo.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StockPriceServiceAsync {
	
	void save(User user, AsyncCallback<Void> callback);

}
