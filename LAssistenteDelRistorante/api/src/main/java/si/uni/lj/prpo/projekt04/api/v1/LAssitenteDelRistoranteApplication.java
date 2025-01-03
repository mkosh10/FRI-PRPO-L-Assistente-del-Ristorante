package si.uni.lj.prpo.projekt04.api.v1;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "LAssitenteDelRistoranteAPI", version = "v1", description = "Designed to help restaurants efficiently manage their operations, including handling customer reservations, menu items, seating arrangements, and employees"))
@ApplicationPath("v1")
@CrossOrigin(supportedMethods = "GET")
public class LAssitenteDelRistoranteApplication extends Application {
}
