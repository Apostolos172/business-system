import java.util.ArrayList;

import MathOfLife.FinalPrices;
import MathOfLife.InitialPrices;

public class Product {
	private String name,description;
	private double initialPrice;
	private ArrayList<Double> percentagesOfDiscounts;
	private double percentageOfFare;
	private double percentageOfProfit;
	private double percentageOfFPA;
	private double finalPrice;
	private int quantity;
	
	//constructors
	public Product(String name, double finalPrice, String description)
	{
		this.name = name;
		this.finalPrice = finalPrice;
		this.description = description;
	}
	
	public String getData3()
	{
		return "" + name + " " + finalPrice + "ευρώ " + description;
	}
	
	public Product(double finalPrice, int quantity)
	{
		this.initialPrice = 0;
		this.percentagesOfDiscounts = new ArrayList<>();
		this.percentagesOfDiscounts.add(0.0);
		this.percentageOfFare = 0;
		this.percentageOfProfit = 0;
		this.percentageOfFPA = 24;
		this.finalPrice = finalPrice;
		this.quantity = quantity;
	}
	
	public Product(double initialPrice, ArrayList<Double> percentagesOfDiscounts,
			double percentageOfFare, double percentageOfProfit, double percentageOfFPA)
	{
		this.initialPrice = initialPrice;
		this.percentagesOfDiscounts = new ArrayList<>();
		this.percentagesOfDiscounts = percentagesOfDiscounts;
		this.percentageOfFare = percentageOfFare;
		this.percentageOfProfit = percentageOfProfit;
		this.percentageOfFPA = percentageOfFPA;
		this.finalPrice = 0;
		this.quantity = 0;
	}
	
	//getters
	public double getInitialPrice() {
		return initialPrice;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public double getFinalPrice() {
		return finalPrice;
	}

	public double getPercentageOfFPA() {
		return percentageOfFPA;
	}

	//setters
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public void setPercentageOfFPA(double percentageOfFPA) {
		this.percentageOfFPA = percentageOfFPA;
	}

	//other methods
	public double getFinalPriceOfProduct()
	{
		
		double finalPrice = FinalPrices.getFinalPriceHavingFare(this.initialPrice, this.percentagesOfDiscounts, this.percentageOfFare, this.percentageOfProfit, this.percentageOfFPA);
		finalPrice = Math.round(finalPrice * 100.0) / 100.0;
		this.setFinalPrice(finalPrice);
		return finalPrice;
	}
	
	public double getInitialPriceIncludingQuantity()
	{
		return this.initialPrice*this.quantity;
	}
	
	public double getandsetInitialPriceOfProduct(double finalPrice, double percentageOfFPA)
	{
		double initialPrice = InitialPrices.getInitialPriceRemovingFPA(finalPrice, percentageOfFPA);
		initialPrice = Math.round(initialPrice * 100.0) / 100.0;
		this.setPercentageOfFPA(percentageOfFPA);
		this.setInitialPrice(initialPrice);
		return initialPrice;
	}
}
