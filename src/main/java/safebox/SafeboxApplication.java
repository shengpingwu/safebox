package safebox;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import safebox.resource.CreateSafeboxResource;
import safebox.resource.EditSafeboxResource;
import safebox.resource.OpenSafeboxResource;

public class SafeboxApplication extends Application {
	
	/**
	 * versioning API.
	 */
	public static final String VERSION = "/api/v1";
	
	public SafeboxApplication(Context context) {
		super(context);
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(this.getContext());
		router.attach(VERSION + "/safebox", CreateSafeboxResource.class);
		router.attach(VERSION + "/safebox/{id}", EditSafeboxResource.class);
		router.attach(VERSION + "/safebox/{id}/open", OpenSafeboxResource.class);
		return router;
	}
}
