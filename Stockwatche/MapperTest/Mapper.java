import com.google.gwt.sample.stockwatcher.server.db.UserMapper;
import com.google.gwt.sample.stockwatcher.shared.bo.User;

public class Mapper {
	
public static void main (String[]args) {
	
	
	UserMapper eins = new UserMapper();

	User sinan = new User();
	
	sinan.setGmail("halloo");
	sinan.setName("snoop");
	sinan.setUsername("Zidane");
}
}
