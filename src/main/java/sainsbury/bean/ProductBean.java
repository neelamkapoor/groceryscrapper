package sainsbury.bean;

import java.util.List;

public class ProductBean {

	public List<Result> results;
	public double total;
	
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "ProductBean [results=" + results + ", total=" + total + "]";
	}
}
