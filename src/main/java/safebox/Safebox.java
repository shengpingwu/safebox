package safebox;

import org.restlet.Component;
import org.restlet.data.Protocol;


public class Safebox {
	
	public static void main( String[] args ) throws Exception
    {
        
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8182);
        component.getDefaultHost().attach(new SafeboxApplication(null));
        component.start();

        System.out.println("The restlet server started ...");
    }

}
