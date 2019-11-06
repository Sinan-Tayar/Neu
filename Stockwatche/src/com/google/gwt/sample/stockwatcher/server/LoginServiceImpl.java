package com.google.gwt.sample.stockwatcher.server;

import com.google.gwt.sample.stockwatcher.shared.LoginService;
import com.google.gwt.sample.stockwatcher.shared.bo.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	@Override
	public User login(String requestUri) {
		UserService userService= UserServiceFactory.getUserService();
		com.google.appengine.api.users.User googleUser = userService.getCurrentUser(); 
		User user = new User();
		
		/**
         * Wenn der User<code> mit seinem Google Account eingeloggt ist, wird überprüft, 
         * ob dieser unserem Kekbuy System bekannt ist.
         * 
         */
		if (googleUser != null) {

			 User existU = UserMapper.userMapper().findByGmail(googleUser.getEmail());
			
			/*
			 * Falls der User dem System bekannt ist, wird dieser eingeloggt.
			 */
			if(existU!=null) {
				
				existU.setLoggedIn(true);
				existU.setLogoutUrl(userService.createLogoutURL(requestUri));
	
				return existU; 
			}
			
			 /**
			  * Falls der <code>User<code> sich zum ersten Mal am System anmeldet, 
			  * wird ein neuer Datensatz in die Datenbank geschrieben.
			  */
		
			user.setLoggedIn(true);
			user.setLogoutUrl(userService.createLogoutURL(requestUri));
			user.setGmail(googleUser.getEmail());
			UserMapper.userMapper().insert(user);
			
			}	

		user.setLoginUrl(userService.createLoginURL(requestUri));
		
		return user;
	}
	
}
