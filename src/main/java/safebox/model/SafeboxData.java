package safebox.model;

import java.util.List;

import com.google.gson.Gson;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import io.jsondb.annotation.Secret;
import safebox.util.Util;

@Document(collection = "safeboxData", schemaVersion = "1.0")
public class SafeboxData {

	/**
	 * Safebox ID.
	 */
	@Id
	private String id;
	/**
	 * User Name.
	 */
	private String name;
	
	/**
	 * User password.
	 */
	@Secret
	private String password;
	
	/**
	 * Items. (Save encrypted json string).
	 */
	@Secret 
	private String items; 
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the items
	 */
	public String getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(String items) {
		this.items = items;
	}

	
	public static SafeboxData makeSafeboxData (String id, String name, String password, String items) {
		
		SafeboxData safeboxData = new SafeboxData();		
		safeboxData.setId(id);
		safeboxData.setName(name);
		safeboxData.setPassword(password);
		safeboxData.setItems(items);
		
		return safeboxData; 
	}

}