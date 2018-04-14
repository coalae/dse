package configuration;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * In der Klasse JerseyConf wird das Package services angegeben, 
 * in dem die services fuer die User-bezogenen Aktivitaeten liegen.
 * @author Cordula Eggerth
 */
public class JerseyConf extends ResourceConfig {
	/**
	 * Konstruktor der Klasse JerseyConf 
	 */
    public JerseyConf() {
        // Define the package which contains the service classes.
        packages("services");
    }
}