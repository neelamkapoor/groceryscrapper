package sainsbury;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sainsbury.bean.ProductBean;
import sainsbury.bean.Result;
import sainsbury.service.GroceryService;
import sainsbury.service.GroceryUrlFetchServiceImpl;


public class GroceryApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(GroceryApplication.class);
	private GroceryService service;

	/**
	 * Intialize with the service (Web based implementation or File based implementation)
	 * @param service
	 */
	public GroceryApplication(GroceryService service){
		this.service = service;
	}
	
	/**
	 * Main method to run the service
	 * @param args
	 */
	public static void main(String[] args) {
		if (args != null && args.length ==1){
			new GroceryApplication(new GroceryUrlFetchServiceImpl()).generateJson(args[0]);
		}else{
			LOGGER.error("Please provide index url as a parameter");
		}
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public String generateJson(String url){
		
		ProductBean bean = parseResponse(url);
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		String productJson = gson.toJson(bean);
		LOGGER.info("Json String \n{}", productJson);
		
		return productJson;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	protected ProductBean parseResponse(String url){
		ProductBean bean = new ProductBean();
		List<Result> resultList = new ArrayList<>();
		Result tmpResult = null;
		double total = 0.0;
		String html = "";
		String response = "";
		
		// Invoke the service to retrieve the search index page with product list
		html = service.getWebPageAsString(url);
		
		Document doc = Jsoup.parse(html);
		Iterator<Element> ele = doc.select("div.product").iterator();
		while (ele.hasNext()){
			tmpResult = new Result();
			Element element = ele.next();

			// Capture the price of the product and retrieve the unit price
			double unitPrice = new Double(element.select("p.pricePerUnit").first().text().replace("£", "").replace("/unit", ""));
			total += unitPrice;
			
			// Capture the link of the product to retrieve description 
			String link  = element.select("div.productInfo").select("a").first().attr("href");
			
			// Invoke the service to retrieve the product details page
			response = service.getWebPageAsString(link);
			Document elementDoc = Jsoup.parse(response);
			
			// Set the values derived above in the bean
			tmpResult.setTitle(element.select("div.productInfo").text());
			tmpResult.setUnitPrice(unitPrice);
			tmpResult.setSize(byteToKbConvertor(response.length()));
			tmpResult.setDescription(elementDoc.select("div.productText").first().text());				
			resultList.add(tmpResult);
		}
		
		// Add the product list & total to the main object for JSON generation
		bean.setResults(resultList);
		bean.setTotal(total);
		return bean;
	}
	
	/**
	 *  Utility method to convert the page size from bytes to KB
	 * @param bytes
	 * @return
	 */
	private String byteToKbConvertor(long bytes) {
	    int unit = 1024;
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    return String.format("%.1f kb", bytes / Math.pow(unit, exp));
	}
}
