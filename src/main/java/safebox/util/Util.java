package safebox.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;

import safebox.model.ItemData;
import safebox.model.ItemsList;
import safebox.model.LoginData;
import safebox.model.TokenData;

public class Util {

	private static final String PASSWORD= "safeboxShengTest";
	
	private static Gson gson = new Gson();

	/**
	 * Transform a json ItemsList into a list String items.
	 * @param json
	 * @return
	 */
	public static List<String> jsonToItemsList(String json) {
		
		ItemsList itemsList = gson.fromJson(json, ItemsList.class);
	
		return itemsList.getItems();
	}

	/**
	 * Transform a List String items into a json ItemsList.
	 * 
	 * @param items
	 * @return
	 */
	public static String ItemsListToJson (List<String> items) {
		
		ItemsList itemsList = new ItemsList();
		itemsList.setItems(items);
	
		String json = gson.toJson(itemsList, ItemsList.class);
		
		return json;
	}
	
	/**
	 * Generated a unique UUID. 
	 * 
	 * @return
	 */
	public static String getUUID() {
	    return UUID.randomUUID().toString();
	}
	
	
	public static LoginData jsonToLoginData(String json) {
		
		LoginData loginData = gson.fromJson(json, LoginData.class);
		
		return loginData;
	}
	
	public static ItemData jsonToItemData(String json) {
		
		ItemData itemData = gson.fromJson(json, ItemData.class);
		
		return itemData;
	}
	
	public static String createToken(String id, String user, long expiredTime) {
		
		try {
			Date date = new Date();
		    Algorithm algorithm = Algorithm.HMAC256(PASSWORD);
		    String token = JWT.create()
		        .withIssuer(user)
		        .withExpiresAt(new Date(date.getTime() + expiredTime))
		        .withClaim("id", id)
		        .sign(algorithm);
		    
		    return token;
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ""; 
	}
	
	public static TokenData decodeToken(String token) {		

		try {
			DecodedJWT jwt = JWT.decode(token);
			
		    Algorithm algorithm = Algorithm.HMAC256(PASSWORD);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer(jwt.getIssuer())
		        .build(); //Reusable verifier instance
		    
		    DecodedJWT jwtVerifier = verifier.verify(token);
		    if (jwtVerifier == null) {
		    	return null;
		    } else {
		    	TokenData tokenData = new TokenData();
		    	tokenData.setId(jwtVerifier.getClaim("id").asString());
		    	tokenData.setUser(jwtVerifier.getIssuer());	
		    	return tokenData;
		    }
		} catch (JWTVerificationException exception){
			
			/**
			 * Token is Expired.
			 */
			exception.printStackTrace();
		   return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	
}
