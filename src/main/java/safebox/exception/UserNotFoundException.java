package safebox.exception;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -436672294841136325L;

	public UserNotFoundException() {
		super("User not found in db.");
	}
}
