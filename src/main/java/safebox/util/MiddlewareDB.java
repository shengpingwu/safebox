package safebox.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import safebox.exception.InvalidPasswordException;
import safebox.exception.UserNotFoundException;

public class MiddlewareDB {

	private static Map<String, String> users = new HashMap<>();

	public static Boolean existsUser(String user) throws UserNotFoundException {
		return users.containsKey(user);
	}
	
	public static Boolean userHasPassword(String user, String password) throws UserNotFoundException, InvalidPasswordException {
		if (!users.containsKey(user)) throw new UserNotFoundException();
		if (users.get(user) != password) throw new InvalidPasswordException(user);
		return true;
	}
	
	public static String getUUID() {
	    return UUID.randomUUID().toString();
	}
}
