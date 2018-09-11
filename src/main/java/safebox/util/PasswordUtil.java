package safebox.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

public class PasswordUtil {

	/**
	 * Class manager common password.
	 */
	private SecList secList; 

	/**
	 * Class for check if a password have consecutive pattern.
	 */
	private AdjacentPassword adjacentPassword; 
	
	
	private static PasswordUtil instance = null;
	
	public static PasswordUtil getInstance() {
		
		if (instance == null) {
			
			instance = new PasswordUtil();
		}
		return instance; 
	}
	
	public SecList getSecList() {
		return secList;
	}


	public void setSecList(SecList secList) {
		this.secList = secList;
	}


	public AdjacentPassword getAdjacentPassword() {
		return adjacentPassword;
	}


	public void setAdjacentPassword(AdjacentPassword adjacentPassword) {
		this.adjacentPassword = adjacentPassword;
	}


	private PasswordUtil() {
		secList = new SecList();
		adjacentPassword = new AdjacentPassword();
		
		initialize();
	}
	
	
	private void initialize() {
		secList.loadCommonPassword();
		adjacentPassword.initialize();
	}
	/**
	 * Check the password is valid.
	 * 
	 * @param password
	 *            password to check.
	 * @return true if is valid.
	 */
	public boolean passwordIsValid(String password) {
		
		if (password.length() < 6) {
			return false;
		}
		
		if (secList.passwordIsContainsInTheList(password)) {
			return false;
		}
		
		if (adjacentPassword.isAdjacentPassword(password)) {
			return false;
		}
		return true;
		
	}
	
	public static String encodePassword (String password) {
		
		String sha1 = null;
		try {
			MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
			msdDigest.update(password.getBytes("UTF-8"), 0, password.length());
			sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			System.out.println(" PasswordUtil.java:: encodePassword () :- Error encoding the password");
			e.printStackTrace();
		}
		
		return sha1;
	}
}
