package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.sample.stockwatcher.shared.bo.User;
import com.google.gwt.user.client.rpc.RemoteService;



public interface StockPriceService extends RemoteService {

	public void save(User user) throws IllegalArgumentException;
}
