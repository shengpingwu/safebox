package safebox.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public class SecList {

	/**
	 * File name with the common password.
	 */
	private static final String FILENAME = "10_million_password_list_top_10000.txt";

	/**
	 * Set of 10000 commons passwords.
	 */
	private Set<String> passwordList = new HashSet<>();

	public SecList() {

	}

	/**
	 * Load from the file the 10000 commons passwords.
	 */
	public void loadCommonPassword() {

		passwordList.clear();

		ClassLoader classLoader = getClass().getClassLoader();

		try {
			List<String> listResult = IOUtils
					.readLines(classLoader.getResourceAsStream("files" + File.separator  + FILENAME));

			passwordList.addAll(listResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check the password is one of 10000 commons passwords.
	 * 
	 * @param passwordToCheck
	 *            password to check.
	 * @return true if the password is one of 10000 commons passwords. In other case
	 *         is false.
	 */
	public boolean passwordIsContainsInTheList(String passwordToCheck) {

		return passwordList.contains(passwordToCheck) ? true : false;
	}

}
