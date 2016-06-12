package sainsbury.stub;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;

import sainsbury.service.GroceryService;

public class GroceryFileFetchServiceImpl implements GroceryService {

	private static Logger LOGGER = LoggerFactory.getLogger(GroceryFileFetchServiceImpl.class);
	
	@Override
	public String getWebPageAsString(String fileName){
	Preconditions.checkNotNull(fileName, "Argument should not be null"); 	
	String response = null;
		try{
			URL res = getClass().getResource("/htmls/" + fileName);
			File file = new File(res.getFile());
			response = Files.toString(file, Charsets.UTF_8);
		}catch(IOException ex){
			LOGGER.error("Exception while fetching web page", ex);
		}
		return response;
	}
}
