package safebox.resource;

import org.restlet.engine.header.Header;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import com.google.gson.JsonSyntaxException;

import safebox.model.ItemData;
import safebox.util.Util;

public class EditSafeboxResource extends ServerResource {
	
	 @Post("json")
	 public String acceptPostRequest(String json) {
		try {
			/**
			 * Transform the json string into a Item Data.
			 */
			ItemData itemData = Util.jsonToItemData(json);
			/**
			 * Get the safebox id.
			 */
			String id = (String) this.getRequest().getAttributes().get("id");

		} catch (JsonSyntaxException jsonEx) {
		}
		return "";
	 }
	 
	@Get
	public String acceptGetRequest() {
		/**
		 * Get the safebox id.
		 */
		String id = (String) this.getRequest().getAttributes().get("id");
		
		/**
		 * Get the token. Check the expiration time.
		 */
		Series<Header> responseHeaders = (Series<Header>) getRequest().getAttributes().get("org.restlet.http.headers");
		String token = responseHeaders.getFirst("Authorization").getValue().substring(7);
		
		
		/**
		 * 
		 */

		return "{\"token\": \"" + token + "\", \"id\": \"" + id + "\" }";
	}
	 
}
