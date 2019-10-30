package com.google.gwt.sample.stockwatcher.shared;

import com.google.gwt.sample.stockwatcher.shared.bo.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")

public interface LoginService extends RemoteService{
	/**
	 * @param requestUri
	 * @return
	 */
	public User login(String requestUri);

}
