package sainsbury.service;

/**
 * 
 * This service interface would be used to read HTML source from the web or HTMLs from the file for unit testing
 * @author Neelam Kapoor
 *
 */
public interface GroceryService {

	public String getWebPageAsString(String inputUrl); 
}
