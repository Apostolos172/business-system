import java.sql.SQLException;
import java.util.ArrayList;

public class StockList {
	private ConnectionWithDatabase conn;
	private ArrayList<Product> products;
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public StockList() throws ClassNotFoundException, SQLException
	{
		String path = "";
		path = "C:\\databases\\shop.db";
		conn = new ConnectionWithDatabase("jdbc:sqlite:" + path);
		this.products = new ArrayList<>();
		this.createTheStockList();
	}
	
	public void createTheStockList() throws SQLException
	{
		Product currentProduct;
		
		this.getThePricesOfProducts();
		this.getTheDescriptionOfProducts();
		
		for(String product: this.getTheNamesOfProducts())
		{
			double price = conn.getTheDoubleValueOfAFieldOfARow("price", "name", "'" + product + "'", "product");
			String description = conn.getTheStringValueOfAFieldOfARow("description", "name", "'" + product + "'", "product");
			currentProduct = new Product(product, price, description);
			products.add(currentProduct);
		}
		
	}
	
	public void addProductToStockList(String name, double price, String description) throws SQLException
	{
	    ArrayList<String> columns,fields;
	    columns = new ArrayList<>();
	    columns.add("name");
	    columns.add("price");
	    columns.add("description");
	    fields = new ArrayList<>();
	    fields.add("'" + name + "'");
	    fields.add("'" + price + "'");
	    fields.add("'" + description + "'");
	    
	    conn.insertARow("product", columns, fields);
	}
	
	public void deleteProductFromStockList(String name) throws SQLException
	{
		conn.deleteARow("product", "name", "'" + name + "'");
	}
	
	public ArrayList<String> getTheNamesOfProducts() throws SQLException
	{
		return conn.getThisColumn("name", "product");
	}
	
	public ArrayList<String> getThePricesOfProducts() throws SQLException
	{
		return conn.getThisColumn("price", "product");
	}
	
	public ArrayList<String> getTheDescriptionOfProducts() throws SQLException
	{
		return conn.getThisColumn("description", "product");
	}
	
	public String getInfoOfTheProductWithThisName(String name) throws SQLException
	{
		ArrayList<String> columnsName = new ArrayList<>();
		columnsName.add("name");
		columnsName.add("price");
		columnsName.add("description");
		return conn.getInfoOfARow(columnsName, "name", name, "product");
	}
}
