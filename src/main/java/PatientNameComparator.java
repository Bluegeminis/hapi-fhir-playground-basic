import java.util.Comparator;

import org.hl7.fhir.r4.model.Patient;


public class PatientNameComparator implements Comparator<Patient>{

	@Override
	public int compare(Patient p1, Patient p2) {
		// Patients can have multiple names but for the sake of this exercise we'll assume only the first is to be considered
		
		String patient1FirstName = p1.getNameFirstRep().getGivenAsSingleString();
		String patient2FirstName = p2.getNameFirstRep().getGivenAsSingleString();

		return patient1FirstName.compareTo(patient2FirstName);
	}

}
