package safebox.resource;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.restlet.engine.header.Header;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class OpenSafeboxResource extends ServerResource {

	@Post("json")
	public String getMsg(String inputData) {
		
		Series<Header> responseHeaders = (Series<Header>) getRequest().getAttributes().get("org.restlet.http.headers");
		
		String password = responseHeaders.getFirst("Authorization").getValue().substring(7);
		

		return "{ \"id\": \"OK\"}";
	}
}
