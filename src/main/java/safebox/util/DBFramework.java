package safebox.util;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import io.jsondb.JsonDBTemplate;
import io.jsondb.crypto.DefaultAESCBCCipher;
import io.jsondb.crypto.ICipher;
import safebox.model.SafeboxData;

public class DBFramework {

	/**
	 * Actual location on disk for database files.
	 */
	private String dbFilesLocation = null; 

	/**
	 * Java package name where POJO's are present.
	 */
	private String baseScanPackage = "safebox.model";
	
	/**
	 * Cipher object for DB Encryption.
	 */
	private ICipher cipher; 

	/**
	 * 
	 */
	private JsonDBTemplate jsonDBTemplate;
	
	private static DBFramework instance = null;
	
	private boolean frameworkInitialize = false;
	
	private DBFramework() {
		
	}
	
	public static DBFramework getInstance() {
		
		if (instance == null) {
			
			instance = new DBFramework();
		}
		return instance; 
	}
	public void setDBFilesLocation(String dbFilesLocation) {
		this.dbFilesLocation = dbFilesLocation;
	}
	
	public void ini() {
		
		if (frameworkInitialize) {
			return;
		}
		
		/**
		 * Get temporary folder to save the database files. 
		 */
		if (dbFilesLocation == null) {
			dbFilesLocation = System.getProperty("java.io.tmpdir") + File.separator + "DB";
		}
		
		File file = new File(dbFilesLocation);

		if (!file.exists()) {
			file.mkdir();
		}
		
		
		try {
			/**
			 * Encrypt the DB with AES Chiper with default password.
			 */
			cipher = new DefaultAESCBCCipher("1r8+24pibarAWgS85/Heeg==");

			jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage, cipher);
			/**
			 * Creating a collection if it does not exist
			 */
			jsonDBTemplate.createCollection(SafeboxData.class);
		} catch (Exception ex) {
			System.out.println ("DBFramework:: ini() :- Error creation Database");
			ex.printStackTrace();
		}
		frameworkInitialize = true;
	}

	/**
	 * Find the safebox with the id.
	 * 
	 * @param id
	 *            safebox id to find.
	 * @return safebox saved in DB.
	 */
	public SafeboxData findSafeboxById(String id) {
		
		SafeboxData safeboxData = jsonDBTemplate.findById (id, SafeboxData.class);
		
		return safeboxData;
	}
	
	/**
	 * Find the user safebox list.
	 *  
	 * @param user
	 * @return
	 */
	public List<SafeboxData> findSafeboxByUser(String user) {
		
		String jxQuery = String.format("/.[name='%s']", user);
		
		return jsonDBTemplate.find(jxQuery, SafeboxData.class);
	}
	
	public void insertOrUpdateSafebox(SafeboxData safeboxData) {
	
		jsonDBTemplate.upsert(safeboxData);
	}
}
