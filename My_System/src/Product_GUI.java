import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Product_GUI extends JFrame {
	private JPanel mainPanel, centralPanel,centralCentralPanel;
	//fonts
	Font serifFontBig,serifFont,serifFontSmall,sansSerifFontSmall,sansserifFontMedium;
	//centralPanel
	private JTextArea areaProducts, areaPrices, areaInfo;
	private JScrollPane paneProducts, panePrices, paneInfo;
	//centralNorthPanel
	private JButton search;
	private JTextField product;
	//centralSouthPanel
	private JLabel productFoundLabel;
	private JTextField productFoundField;
	
	private double price;
	private StockList menu;
	private JLabel instructionsLabel;
	private JLabel nameLabel;
	private JTextField nameTxt;
	private JLabel priceLabel;
	private JTextField priceTxt;
	private JLabel descriptionLabel;
	private JTextField descriptionTxt;
	private JLabel categoryLabel;
	private JTextField categoryTxt;
	private JLabel countableLabel;
	private JTextField countableTxt;
	private JButton addToStockListbtn;
	
	public Product_GUI(double price) throws ClassNotFoundException, SQLException  {
		super("Χρώματα Βαδραχάνης");
		this.price = price;
		this.createTheStockList();
		makeFrame();
	}
	
	public void createTheStockList() throws ClassNotFoundException, SQLException
	{
		 this.menu = new StockList();
	}
	
	public void makeFrame() throws SQLException {
		
		makeMyFonts();
		
		mainPanel = new JPanel(new BorderLayout(20,20));

		makeCentralPanel();
		
		mainPanel.setBackground(Color.green);
		mainPanel.setBackground(Color.black);
		
        this.setContentPane(mainPanel);
		
        GUI.setPadding(mainPanel);
		GUI.setSizeOfTheWindow(this);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void makeCentralPanel() throws SQLException 
	{
		centralPanel = new JPanel(new BorderLayout(10,10));
		GUI.setPadding(centralPanel);
		
		JPanel centralNorthPanel = new JPanel();
		GUI.setPadding(centralNorthPanel);
		JPanel centralSouthPanel = new JPanel();
		GUI.setPadding(centralSouthPanel);

		centralCentralPanel = new JPanel();
		GUI.setPadding(centralCentralPanel);
		centralCentralPanel.setLayout(new GridLayout(5,2,100,10));
        
		//north
		instructionsLabel = new JLabel("Συμπλήρωσε αναλόγως τα ακόλουθα πεδία και έπειτα"
				+ " πίεσε το πλήκτρο για την προσθήκη του προϊόντος στον τιμοκατάλογο.");
		instructionsLabel.setFont(serifFont); 
		centralNorthPanel.add(instructionsLabel,BorderLayout.NORTH);
		
		//central
		nameLabel = new JLabel("Δώσε το όνομα του προϊόντος:");
		nameLabel.setFont(serifFont);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		nameTxt = new JTextField();
		nameTxt.setFont(serifFontBig);
		GUI.setPaddingAtJTextField(nameTxt);
		
		priceLabel = new JLabel("Τελική τιμή:");
		priceLabel.setFont(serifFont);
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		priceTxt = new JTextField();
		priceTxt.setFont(serifFontBig);
		GUI.setPaddingAtJTextField(priceTxt);
		priceTxt.setText("" + this.price);
		
		descriptionLabel = new JLabel("Περιγραφή προϊόντος:");
		descriptionLabel.setFont(serifFont);
		descriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		descriptionTxt = new JTextField();
		descriptionTxt.setFont(serifFontBig);
		GUI.setPaddingAtJTextField(descriptionTxt);
		
		countableLabel = new JLabel("Είδος συσκευασίας:"); 
		countableLabel.setFont(serifFont);
		countableLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		countableTxt = new JTextField();
		countableTxt.setFont(serifFontBig);
		GUI.setPaddingAtJTextField(countableTxt);
		
		categoryLabel = new JLabel("Σε ποια κατηγορία ανήκει:");
		categoryLabel.setFont(serifFont);
		categoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	 
		categoryTxt = new JTextField();
		categoryTxt.setFont(serifFontBig);
		GUI.setPaddingAtJTextField(categoryTxt);
		
		centralCentralPanel.add(nameLabel);
		centralCentralPanel.add(nameTxt);
		centralCentralPanel.add(priceLabel);
		centralCentralPanel.add(priceTxt);
		centralCentralPanel.add(descriptionLabel);
		centralCentralPanel.add(descriptionTxt);
		centralCentralPanel.add(countableLabel);
		centralCentralPanel.add(countableTxt);
		centralCentralPanel.add(categoryLabel);
		centralCentralPanel.add(categoryTxt);
		
		centralPanel.add(centralCentralPanel,BorderLayout.CENTER);
        
        //centralSouthPanel
        
		addToStockListbtn = new JButton("Προσθήκη");
		addToStockListbtn.setFont(serifFont);
		
		addToStockListbtnListener addToStockListbtnlistener = new addToStockListbtnListener();
		addToStockListbtn.addActionListener(addToStockListbtnlistener);
		
		centralSouthPanel.add(addToStockListbtn);
		
		//additions to centralPanel
        
        centralPanel.add(centralNorthPanel,BorderLayout.NORTH);
        centralPanel.add(centralCentralPanel,BorderLayout.CENTER);
        centralPanel.add(centralSouthPanel,BorderLayout.SOUTH);
        
        centralPanel.setBackground(Color.LIGHT_GRAY);
        
        //additions to mainPanel
        mainPanel.add(centralPanel,BorderLayout.CENTER);
	}
	
	class addToStockListbtnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				menu.addProductToStockList(nameTxt.getText(), Double.parseDouble(priceTxt.getText()), descriptionTxt.getText());
				} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
				GUI.showConfirmationWindow("Το προϊόν προστέθηκε στον κατάλογο.", 400);
		}
	}
	
	public void makeMyFonts()
	{
		serifFontBig = new Font("serif", Font.BOLD, 30);
		sansserifFontMedium = new Font("Sans Serif", Font.PLAIN, 30);
		serifFont = new Font("serif", Font.BOLD, 22);
		serifFontSmall = new Font("serif", Font.ITALIC|Font.BOLD, 17);
		sansSerifFontSmall = new Font("Sans Serif", Font.ITALIC|Font.BOLD, 17);
	}

}
