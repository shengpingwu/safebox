package safebox.exception;

public class InvalidPasswordException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3326871857513637333L;

	public InvalidPasswordException(String user) {
		super("User["+user+"] not found in db.");
	}
}
