import java.util.ArrayList;

public class Invoice {
	private ArrayList<Product> products;
	
	public Invoice()
	{
		products = new ArrayList<>();
	}
	
	//other methods
	public void addProduct(Product product)
	{
		products.add(product);
	}
	
	public double getSumOfTheInitialPricesIncludingQuantities()
	{
		double sum=0;
		for(Product p: products)
		{
			p.getandsetInitialPriceOfProduct(p.getFinalPrice(), p.getPercentageOfFPA());
			//sum+=p.getInitialPrice()*p.getQuantity();
			sum+=p.getInitialPriceIncludingQuantity();
		}
		sum = Math.round(sum * 100.0) / 100.0;
		return sum;
	}
	
	public int getSumOfTheQuantities()
	{
		int sum=0;
		for(Product p : products)
		{
			sum+=p.getQuantity();
		}
		return sum;
	}
}
