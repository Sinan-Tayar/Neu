package com.google.gwt.sample.stockwatcher.shared;

import com.google.gwt.sample.stockwatcher.shared.bo.User;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface LoginServiceAsync {
	/**
	 * @param requestUri
	 * @param asyncCallback
	 */
	void login(String requestUri, AsyncCallback<User> asyncCallback); 
	
}
