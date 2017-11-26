
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.discovery.annotations.RegisterService;

@RegisterService("apartments")
@ApplicationPath("v1")
public class ApartmentApplication extends Application {
}
