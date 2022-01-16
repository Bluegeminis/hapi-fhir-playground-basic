import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;
import org.junit.Assert;
import org.junit.Test;

public class SampleClientTests {

	@Test
	public void testSorting() {
		
		String patient1FirstName = "Zwan";
		String patient2FirstName = "Abigail";
		
		HumanName patient1Name = new HumanName();
		patient1Name.setGiven(Arrays.asList(new StringType(patient1FirstName)));
		patient1Name.setFamily("Abercrombie");
		
		HumanName patient2Name = new HumanName();
		patient2Name.setGiven(Arrays.asList(new StringType(patient2FirstName)));
		patient2Name.setFamily("Zukovich");		
		
		Patient patient1 = new Patient();
		patient1.setName(Arrays.asList(patient1Name));
		
		Patient patient2 = new Patient();
		patient2.setName(Arrays.asList(patient2Name));
		
		List<Patient> patients = new ArrayList<>();
		patients.add(patient1);
		patients.add(patient2);
		
		Collections.sort(patients, new PatientNameComparator());
		
		Assert.assertEquals(patient2FirstName, patients.get(0).getName().get(0).getGivenAsSingleString());
		Assert.assertEquals(patient1FirstName, patients.get(1).getName().get(0).getGivenAsSingleString());
	}
	
}
