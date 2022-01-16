import java.io.IOException;

import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;
import ca.uhn.fhir.util.StopWatch;

public class SearchRequestTimingInterceptor implements IClientInterceptor {

	private StopWatch requestStopWatch = new StopWatch();
	
	private long requestLengthMillis = 0L;
	
	public long getRequestLengthMillis() {
		return requestLengthMillis;
	}

	@Override
	public void interceptRequest(IHttpRequest theRequest) {
		requestStopWatch.restart();
		requestStopWatch.startTask("Find patients by family name task");
	}

	@Override
	public void interceptResponse(IHttpResponse theResponse) throws IOException {
		requestStopWatch.endCurrentTask();
		requestLengthMillis = requestStopWatch.getMillis();
	}

}
