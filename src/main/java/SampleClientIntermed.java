import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.CacheControlDirective;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class SampleClientIntermed {

    public static void main(String[] theArgs) {

    	SearchRequestTimingInterceptor searchRequestTimingInterceptor = new SearchRequestTimingInterceptor();
    	
        // Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        // disable to not pollute console output client.registerInterceptor(new LoggingInterceptor(false));
        client.registerInterceptor(searchRequestTimingInterceptor);
        
        try {
			AverageResponseTimeCalculator avgResponseTimeCalculator = new AverageResponseTimeCalculator();
			
        	List<String> familyNames = Files.readAllLines(Paths.get(SampleClientIntermed.class.getResource("family_names.txt").toURI()));
			
        	for(int i=1; i < 4; i++) {
            	for(String familyName: familyNames) {

            		CacheControlDirective cacheControl = new CacheControlDirective();
            		if(i < 3 ) {
            			cacheControl.setNoCache(false);
            		} else {
            			cacheControl.setNoCache(true);
            		}
    				
    		        // Search for Patient resources
    		        client.search()
		                .forResource("Patient")
		                .cacheControl(cacheControl)
		                .where(Patient.FAMILY.matches().value(familyName.toUpperCase()))
		                .returnBundle(Bundle.class)
		                .execute();				
    				
    				long responseTimeMillis = searchRequestTimingInterceptor.getRequestLengthMillis();
    				avgResponseTimeCalculator.addResponseTime(responseTimeMillis);
    			}
    			Double averageRespTimeMillis = avgResponseTimeCalculator.computeAverageAndReset();
    			System.out.println("Average response time for iteration " + i + " was " + averageRespTimeMillis + "ms");
        	}
			
		} catch (IOException | URISyntaxException e) {
			System.out.println("Could not read-in patient file : "+ e.getMessage());
		}
    }

}
