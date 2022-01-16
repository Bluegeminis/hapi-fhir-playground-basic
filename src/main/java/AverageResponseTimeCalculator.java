import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AverageResponseTimeCalculator {

	private List<Long> searchResponseTimesMills = new ArrayList<>();
	
	public void addResponseTime(Long respTime) {
		searchResponseTimesMills.add(respTime);
	}

	public Double computeAverageAndReset() {
		
		Double averageRespTimeMillis = searchResponseTimesMills.stream().collect(Collectors.averagingLong(Long::longValue));
		searchResponseTimesMills.clear();
		return averageRespTimeMillis;
	}
}
