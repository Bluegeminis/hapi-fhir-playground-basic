import java.text.SimpleDateFormat;

import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;

public class ClientDetailsPrinter {
	
	private final static SimpleDateFormat DOB_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	public static void print(Patient patient) {
		HumanName humanName = patient.getNameFirstRep();

		System.out.println("Patient Details");
		System.out.println("***********************************************************");
		System.out.println("First Name : " + humanName.getGivenAsSingleString());
		System.out.println("Last Name : " + humanName.getFamily());
		String dob = patient.getBirthDate() != null?DOB_FORMAT.format(patient.getBirthDate()):"N/A";
		System.out.println("DOB : " +  dob);
		System.out.println("***********************************************************\n");
	}
	
}
