package safebox.resource;

import java.util.ArrayList;
import java.util.List;

import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.JsonSyntaxException;

import safebox.exception.InvalidPasswordException;
import safebox.exception.UserNotFoundException;
import safebox.model.LoginData;
import safebox.model.SafeboxData;
import safebox.util.DBFramework;
import safebox.util.MiddlewareDB;
import safebox.util.PasswordUtil;
import safebox.util.Util;


public class CreateSafeboxResource extends ServerResource {

    @Post("json")
    public String acceptPostRequest(String json) {
    	
    	try {
    		
    		LoginData loginData = Util.jsonToLoginData (json);    		
    		DBFramework.getInstance().ini();
    		List<SafeboxData> safeboxDataList = DBFramework.getInstance().findSafeboxByUser(loginData.getName());
    		if (safeboxDataList.size() != 0) {
    			/**
    			 * Check the user and password have a already created safebox.
    			 */
    			for (SafeboxData safaboxData: safeboxDataList) {
    				/**
    				 * Encrypt the passowrd with SHA1.
    				 */
    				String password = PasswordUtil.encodePassword(loginData.getPassword());
    				/**
    				 * Compare the passwords.
    				 */
    				if (safaboxData.getPassword().equalsIgnoreCase(password)) {
    					/**
    					 * The user and password have a safebox created. Return 409 HTTP CODE.
    					 */
    					setStatus(Status.CLIENT_ERROR_CONFLICT);
    					return "{ \"Error\": \"Safebox already exists\"}";
    				}
    			}
    		}
    		
    		/**
    		 * User and password not have safebox. Check the password is valid.
    		 */
    		if (PasswordUtil.getInstance().passwordIsValid(loginData.getPassword())) {
    			/**
    			 * Is a valid password. Generate a unique UUID and return to user.
    			 */
    			String UUID = Util.getUUID();
    			/**
    			 * Save the user, password, uuid to db.
    			 */
    			List<String> items = new ArrayList<>();
    			SafeboxData safeboxData = SafeboxData.makeSafeboxData(UUID, 
    					loginData.getName(), 
    					PasswordUtil.encodePassword(loginData.getPassword()), 
    					Util.ItemsListToJson(items));
    			
    			DBFramework.getInstance().insertOrUpdateSafebox(safeboxData);
    			setStatus(Status.SUCCESS_OK);
    			return "{ \"id\": \""+UUID+"\"}";
    		} else {
    			/**
    			 * Password is invalid. Return 422.
    			 */
        		setStatus(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY);
        		return "{ \"Error\": \"Malformed expected data\"}";
    		}
    		
    	} catch (JsonSyntaxException jsonEx) {
    		/**
			 * Malformed expected data. Return 422 HTTP CODE.
			 */
    		setStatus(Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY);
    		return "{ \"Error\": \"Malformed expected data\"}";
    	} catch (Exception ex) {
        	setStatus(Status.SERVER_ERROR_INTERNAL);
        	return "{ \"Error\": \"Unexpected API error\"}";
    	}        
    }
	/*
    @Post("json")
    public String getMsg(String json) {
    	
    	try {
    		LoginData loginData = new Gson().fromJson(json, LoginData.class);
    		if (MiddlewareDB.existsUser(loginData.getName())) 
    		MiddlewareDB.userHasPassword(loginData.getName(), loginData.getPassword());
    		
    	} catch (UserNotFoundException e) {
    		/**
    		 * Safebox already exists.
    		 *
        	setStatus(Status.CLIENT_ERROR_CONFLICT);
    		
    	} catch (InvalidPasswordException ex) {
    		
    	} catch (Throwable ex) {
    		
    	}
    	
    	setStatus(Status.SUCCESS_OK);
        return "{ \"id\": \""+MiddlewareDB.getUUID()+"\"}";
    }
    */
}
