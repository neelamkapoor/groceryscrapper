package sainsbury;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sainsbury.service.GroceryService;
import sainsbury.stub.GroceryFileFetchServiceImpl;


public class GroceryApplicationTest {

/**
 * generate html, parse html, parse description, return json, return total 
 */
	
	// STUB to use local HTML files from resources/html/ folder
	GroceryService service = new GroceryFileFetchServiceImpl();
	GroceryApplication groceryApp = new GroceryApplication(service);
	String input = "";
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Before
	public void setUp(){
		input = "list";
	}

	@Test
	public void ThrowExceptionWhenPassNullurl(){
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("Argument should not be null");
		
		service.getWebPageAsString(null);
	}
	
	@Test
	public void testWhenEnterRipeAndReadyUrlShouldGenerateHTMLString(){
		Assert.assertNotNull(service.getWebPageAsString(input));
	}
	
	@Test
	public void whenEnterWebsiteLinkShouldReturnProductTotal(){
		Assert.assertEquals(24.3, groceryApp.parseResponse(input).getTotal(), 0);
	}

	@Test
	public void whenEnterWebsiteLinkShouldReturnJSONStringContainingTitleOfProduct(){
		String expectedProductTitle = "Sainsbury's Apricot Ripe & Ready x5";
		Assert.assertEquals(expectedProductTitle, groceryApp.parseResponse(input).getResults().get(0).getTitle());
	}
	
	@Test
	public void whenEnterWebsiteLinkShouldReturnJSONStringContainingSizeOfFile(){
		String expectedSize = "39.9 kb";
		Assert.assertEquals(expectedSize, groceryApp.parseResponse(input).getResults().get(0).getSize());
	}
	
	@Test
	public void whenEnterWebsiteLinkShouldReturnJSONStringContainingUnitPriceOfProduct(){
		double expectedProductUnitPrice = 3.0;
		Assert.assertEquals(expectedProductUnitPrice, groceryApp.parseResponse(input).getResults().get(0).getUnitPrice(), 0);
	}
	
	@Test
	public void whenEnterWebsiteLinkShouldReturnJSONStringContainingDescriptioneOfProduct(){
		String expectedProductDescriptions = "Apricots";
		Assert.assertEquals(expectedProductDescriptions, groceryApp.parseResponse(input).getResults().get(0).getDescription());
	}

	@Test
	public void whenEnterUrlShouldReturnJsonString(){
		String expectedJson = "{\n  \"results\": [\n    {\n      \"title\": \"Sainsbury's Apricot Ripe & Ready x5\",\n      \"size\": \"39.9 kb\",\n      \"unitPrice\": 3.0,\n      \"description\": \"Apricots\"\n    },\n    {\n      \"title\": \"Sainsbury's Avocado Ripe & Ready XL Loose 300g\",\n      \"size\": \"40.9 kb\",\n      \"unitPrice\": 1.5,\n      \"description\": \"Avocados\"\n    },\n    {\n      \"title\": \"Sainsbury's Avocado, Ripe & Ready x2\",\n      \"size\": \"45.7 kb\",\n      \"unitPrice\": 1.8,\n      \"description\": \"Avocados\"\n    },\n    {\n      \"title\": \"Sainsbury's Avocados, Ripe & Ready x4\",\n      \"size\": \"40.3 kb\",\n      \"unitPrice\": 3.2,\n      \"description\": \"Avocados\"\n    },\n    {\n      \"title\": \"Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)\",\n      \"size\": \"40.2 kb\",\n      \"unitPrice\": 1.75,\n      \"description\": \"Conference\"\n    },\n    {\n      \"title\": \"Sainsbury's Golden Kiwi x4\",\n      \"size\": \"40.2 kb\",\n      \"unitPrice\": 1.65,\n      \"description\": \"Gold Kiwi\"\n    },\n    {\n      \"title\": \"Sainsbury's Kiwi Fruit, Ripe & Ready x4\",\n      \"size\": \"40.6 kb\",\n      \"unitPrice\": 1.65,\n      \"description\": \"Kiwi\"\n    },\n    {\n      \"title\": \"Sainsbury's Kiwi Fruit, SO Organic x4\",\n      \"size\": \"40.5 kb\",\n      \"unitPrice\": 1.0,\n      \"description\": \"Kiwi\"\n    },\n    {\n      \"title\": \"Sainsbury's Mango, Ripe & Ready x2 Only £1.75: Save 25p\",\n      \"size\": \"41.3 kb\",\n      \"unitPrice\": 1.75,\n      \"description\": \"by Sainsbury's Ripe and Ready Mango\"\n    },\n    {\n      \"title\": \"Sainsbury's Nectarines, Ripe & Ready x4\",\n      \"size\": \"36.8 kb\",\n      \"unitPrice\": 2.0,\n      \"description\": \"Description\"\n    },\n    {\n      \"title\": \"Sainsbury's Papaya, Ripe (each)\",\n      \"size\": \"40.4 kb\",\n      \"unitPrice\": 1.5,\n      \"description\": \"Papaya\"\n    },\n    {\n      \"title\": \"Sainsbury's Pears, Ripe & Ready x4 (minimum)\",\n      \"size\": \"40.2 kb\",\n      \"unitPrice\": 1.75,\n      \"description\": \"Pear\"\n    },\n    {\n      \"title\": \"Sainsbury's Ripe & Ready Red Pears x4\",\n      \"size\": \"40.2 kb\",\n      \"unitPrice\": 1.75,\n      \"description\": \"Description Sweet & Juicy Blush Pears Ripe & ready Sweet & Juicy One pear counts as 1 of your 5 a-day One pear counts as 1 of your 5 a-day Aim for at least 5 different portions of fruit or veg a day. Fresh, frozen, dried, canned and juice all count. We value the quality of fresh fruit and vegetables We are proud to work in partnership with our suppliers, who participate in an independent audit programme that ensures responsible pesticide usage and encourages environmental protection and wildlife conservation.\"\n    }\n  ],\n  \"total\": 24.3\n}";
		Assert.assertEquals(expectedJson, groceryApp.generateJson(input));
		
	}
	
}
