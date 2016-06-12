package sainsbury.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;

/**
 * 
 * This service implementation would be used to read HTML source from the web
 * @author Neelam Kapoor
 *
 */
public class GroceryUrlFetchServiceImpl implements GroceryService {

	private static Logger LOGGER = LoggerFactory.getLogger(GroceryUrlFetchServiceImpl.class);
	
	@Override
	public String getWebPageAsString(String inputUrl){
		
		Preconditions.checkNotNull(inputUrl, "URL should not be null"); 	
		String response = null;
			try{
				HttpClient client = HttpClients.custom().build();
				HttpUriRequest request = RequestBuilder.get().setUri(inputUrl).build();
				HttpResponse httpResponse = client.execute(request);
				
				// Read the response stream
				InputStream steam = httpResponse.getEntity().getContent();
				response = new String(ByteStreams.toByteArray(steam), Charsets.UTF_8);
			}catch(IOException ex){
				LOGGER.error("Exception while fetching web page", ex);
			}
			return response;
	 }
}
