import java.sql.SQLException;

/**
 * This is the basic class in order to run the program
 */
public class Main {

	// ERP App
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		new Main_GUI();
		testing();
		
		
	}
	
	public static void testing() throws ClassNotFoundException, SQLException {
		// a method that uses methods from other classes in order to check their results 
		
		String path = "";
		path = "C:\\databases\\shop.db";
		ConnectionWithDatabase conn = new ConnectionWithDatabase("jdbc:sqlite:" + path);

		System.out.println(conn.getTheDoubleValueOfAFieldOfARow("price", "name", "'superPlastic'", "product"));
		hr();
	    for(String name: conn.getThisColumn("name", "product"))
	    	System.out.println(name);
		hr();
	    StockList list = new StockList();
	    //list.getInfoOfTheProductWithThisName("superPlastic");
	    for(String name: list.getTheNamesOfProducts())
	    	System.out.println(name);
		hr();
		System.out.println(list.getInfoOfTheProductWithThisName("'superPlastic'"));
		hr();

	    for(Product product: list.getProducts())
	    	System.out.println(product.getData3());
	}
	
	public static void hr() {
		// print dashed horizontal ruler
		
		System.out.println("-----------------------------");
	}

}