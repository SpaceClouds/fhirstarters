package test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import javax.swing.*;

public class TestApplication {

   /**
    * This is the Java main method, which gets executed
    */
   public static void main(String[] args) {

      JFrame frame = new JFrame();
      frame.setSize(500, 500);




      frame.setVisible(true);
      // Create a context
      FhirContext ctx = FhirContext.forR4();

      System.out.println("point 1");

      // Create a client
      IGenericClient client = ctx.newRestfulGenericClient("https://fhir.monash.edu/hapi-fhir-jpaserver/fhir/");

      System.out.println("point 2");

      // Read a patient with the given ID
      Patient patient = client.read().resource(Patient.class).withId("8883594").execute();

      System.out.println("point 3");

      // Print the output
      String string = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);

      System.out.println(patient.getName().toString());

      System.out.println(string);
/*
     Bundle bundle = (Bundle) client
        .search().forResource(Patient.class)
        .where(new StringClientParam("family").matchesExactly().value("Wisoky380"))
        .execute();

      System.out.println(ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(bundle));


 */

		/*
       * Some more things to try:
		 * 
		 * Search for Patient resources with the name “Test” and print the results
		 *   Bonus: Load the second page
		 * 
		 * Create a new Patient resource and upload it to the server
		 *   Bonus: Log the ID that the server assigns to your resource
		 *
 		 * Read a resource from the server
		 *   Bonus: Display an error if it has been deleted
		 *   Hint for Bonus- See this page: http://hapifhir.io/apidocs/ca/uhn/fhir/rest/server/exceptions/package-summary.html
		 */

   }

}
