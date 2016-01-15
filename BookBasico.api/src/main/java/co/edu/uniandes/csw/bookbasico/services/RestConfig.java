package co.edu.uniandes.csw.bookbasico.services;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class RestConfig extends ResourceConfig {

    public RestConfig() {
        packages("co.edu.uniandes.csw.bookbasico.services");
        packages("co.edu.uniandes.csw.auth.service");
        packages("co.edu.uniandes.csw.auth.provider");
        packages("co.edu.uniandes.csw.auth.filter");
    }
}
