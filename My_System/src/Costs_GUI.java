import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import java.util.ArrayList;

/**
 * Η κλάση της οποίας τα αντικείμενα που θα δημιουργηθούν θα αποτελούν μια φόρμα προκειμένου
 * ο χρήστης να μπορεί να υπολογίσει την τελική τιμή για κάποιο προϊόν και έπειτα να το κρατήσει 
 * και στη βάση με τα στοιχεία της επιχείρησης αν θελήσει
 */
public class Costs_GUI extends JFrame{
	
	private JPanel mainPanel,northPanel,southPanel,westPanel,centralPanel;
	//fonts
	Font serifFontBig,serifFont,serifFontSmall,sansSerifFontSmall;
	//northPanel
	private ImageIcon image;
	private JLabel imageLabel;
	//southPanel
	private JLabel footerLabel1,footerLabel2;
	//westPanel
	private JLabel infoLabel1,infoLabel2,infoLabel3,infoLabel4;
	private JButton writeInfoInFile,btn1;
	//centralPanel
	private JLabel instructionsLabel, initialPriceLabel, discountsLabel, fareLabel, profitLabel, FPALabel;
	private JTextField initialPriceTxt, discountsTxt1, discountsTxt2, discountsTxt3, discountsTxt4, fareTxt, profitTxt, FPATxt, finalPriceTxt;
	private JButton finalPricebtn,toStockListbtn;
	private JPanel centralCentralPanel;
	
	private Main_GUI main_GUI;
	
	public Costs_GUI(Main_GUI main_GUI) {
		super("Χρώματα");
		this.main_GUI = main_GUI;
		main_GUI.setVisible(false);
		makeFrame();
	}
	
	public void makeFrame() {
		
		makeMyFonts();
		
		mainPanel = new JPanel(new BorderLayout(20,20));
		
		makeNorthPanel();
		makeSouthPanel();
		makeWestPanel();
		makeCentralPanel();
		
		mainPanel.setBackground(Color.green);
		mainPanel.setBackground(Color.black);
		
        this.setContentPane(mainPanel);
		
        GUI.setPadding(mainPanel);
		GUI.setSizeOfTheWindow(this);
		
		this.setResizable(false);
		this.setVisible(true);
	}

	public void makeNorthPanel()
	{
		northPanel = new JPanel();
		GUI.setPadding(northPanel);
		
		image = new ImageIcon("logo.png");
        imageLabel = new JLabel(image);
        northPanel.add(imageLabel);
        
        northPanel.setBackground(Color.cyan);
        
        mainPanel.add(northPanel,BorderLayout.NORTH);
	}
	
	public void makeSouthPanel()
	{
		southPanel = new JPanel();
		GUI.setPadding(southPanel);
		southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.Y_AXIS));
		southPanel.setAlignmentY(CENTER_ALIGNMENT);
		southPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		footerLabel1 = new JLabel("Designed by Tolis' s group");
		footerLabel1.setFont(serifFontSmall);
		footerLabel2 = new JLabel("Copyright 2020");
		footerLabel2.setFont(serifFontSmall);
		 
        southPanel.add(footerLabel1);
        southPanel.add(footerLabel2);
		
        southPanel.setBackground(Color.cyan);
        
        mainPanel.add(southPanel,BorderLayout.SOUTH);
	}
	
	public void makeWestPanel()
	{
		westPanel = new JPanel();
		GUI.setPadding(westPanel);
		westPanel.setLayout(new BoxLayout(westPanel,BoxLayout.Y_AXIS));
		//westPanel.setLayout(new FlowLayout());

		infoLabel1 = new JLabel("Επωνυμία επιχείρησης: Χρώματα");
		infoLabel1.setFont(serifFont);
		infoLabel1.setForeground(Color.white);
		infoLabel2 = new JLabel("Τηλ. 24670 24670");
		infoLabel2.setFont(serifFont);
		infoLabel2.setForeground(Color.white);
		infoLabel3 = new JLabel("Διεύθυνση: τάδε");
		infoLabel3.setFont(serifFont);
		infoLabel3.setForeground(Color.white);
		infoLabel4 = new JLabel("Περιοχή: Θεσσαλονίκη, 11111");
		infoLabel4.setFont(serifFont);
		infoLabel4.setForeground(Color.white);
		
		writeInfoInFile = new JButton("<html>Αποθήκευσε τις πληροφορίες σε <br>αρχείο στην επιφάνεια εργασίας</html>");
		writeInfoInFile.setFont(sansSerifFontSmall);
		writeInfoInFile.setBackground(Color.green);
		
		writeInfoInFilebtnListener writeInfoInFilebtnlistener = new writeInfoInFilebtnListener();
		writeInfoInFile.addActionListener(writeInfoInFilebtnlistener);
		
		btn1 = new JButton("Επιστροφή στην αρχική καρτέλα");
		btn1.setFont(sansSerifFontSmall);
		btnReturnListener btn1listener = new btnReturnListener();
		btn1.addActionListener(btn1listener);
		 
		westPanel.add(infoLabel1);
		westPanel.add(infoLabel2);
		westPanel.add(infoLabel3);
		westPanel.add(infoLabel4);
		westPanel.add(new JLabel("\n"));
		westPanel.add(writeInfoInFile);
		westPanel.add(new JLabel("\n"));
		westPanel.add(btn1);
		
		westPanel.setBackground(Color.gray);
        
        mainPanel.add(westPanel,BorderLayout.WEST);
	}
	
	public void makeCentralPanel() 
	{
		centralPanel = new JPanel();
		GUI.setPadding(centralPanel);
		centralPanel.setLayout(new BorderLayout(10,10));
		
		//north
		instructionsLabel = new JLabel("Συμπλήρωσε αναλόγως τα ακόλουθα πεδία και έπειτα"
				+ " πίεσε το πλήκτρο για την εμφάνιση της τελικής τιμής στο διπλανό του πλαίσιο");
		instructionsLabel.setFont(serifFont); 
		centralPanel.add(instructionsLabel,BorderLayout.NORTH);
		
		//central
		centralCentralPanel = new JPanel();
		centralCentralPanel.setLayout(new GridLayout(5,2,100,10));
		GUI.setPadding(centralCentralPanel);
		
		initialPriceLabel = new JLabel("Δώσε την αρχική τιμή του προϊόντος:");
		initialPriceLabel.setFont(serifFont);
		initialPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		initialPriceTxt = new JTextField();
		initialPriceTxt.setFont(serifFontBig);
		GUI.setPaddingAtJTextField(initialPriceTxt);
		initialPriceTxtFocus initialPriceTxtfocus = new initialPriceTxtFocus();
		initialPriceTxt.addFocusListener(initialPriceTxtfocus);
		
		discountsLabel = new JLabel("Ποσοστά εκπτώσεων προμηθευτή:");
		discountsLabel.setFont(serifFont);
		discountsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel helpfulDiscountsPanel = new JPanel();
		helpfulDiscountsPanel.setBackground(Color.blue);
		GUI.setPadding(helpfulDiscountsPanel);
		
		discountsTxt1 = new JTextField("0",2);
		discountsTxt1.setFont(serifFontBig);
		discountsTxt2 = new JTextField("0",2);
		discountsTxt2.setFont(serifFontBig);
		discountsTxt3 = new JTextField("0",2);
		discountsTxt3.setFont(serifFontBig);
		discountsTxt4 = new JTextField("0",2);
		discountsTxt4.setFont(serifFontBig);
		
		fareLabel = new JLabel("Ποσοστό ναύλου:");
		fareLabel.setFont(serifFont);
		fareLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		fareTxt = new JTextField("0");
		fareTxt.setFont(serifFontBig);
		GUI.setPaddingAtJTextField(fareTxt);
		
		profitLabel = new JLabel("Ποσοστό κέρδους:"); 
		profitLabel.setFont(serifFont);
		profitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		profitTxt = new JTextField("30");
		profitTxt.setFont(serifFontBig);
		GUI.setPaddingAtJTextField(profitTxt);
		
		FPALabel = new JLabel("Ποσοστό ΦΠΑ:");
		FPALabel.setFont(serifFont);
		FPALabel.setHorizontalAlignment(SwingConstants.RIGHT);
	 
		FPATxt = new JTextField("24");
		FPATxt.setFont(serifFontBig);
		GUI.setPaddingAtJTextField(FPATxt);
		
		helpfulDiscountsPanel.add(discountsTxt1);
		helpfulDiscountsPanel.add(discountsTxt2);
		helpfulDiscountsPanel.add(discountsTxt3);
		helpfulDiscountsPanel.add(discountsTxt4);
		
		centralCentralPanel.add(initialPriceLabel);
		centralCentralPanel.add(initialPriceTxt);
		centralCentralPanel.add(discountsLabel);
		centralCentralPanel.add(helpfulDiscountsPanel);
		centralCentralPanel.add(fareLabel);
		centralCentralPanel.add(fareTxt);
		centralCentralPanel.add(profitLabel);
		centralCentralPanel.add(profitTxt);
		centralCentralPanel.add(FPALabel);
		centralCentralPanel.add(FPATxt);
		
		centralPanel.add(centralCentralPanel,BorderLayout.CENTER);
		
        //south
		JPanel helpful = new JPanel();
		
		finalPricebtn = new JButton("Υπολόγισε τιμή προς πώληση");
        finalPricebtn.setFont(serifFontBig);		
        helpful.add(finalPricebtn);
        
        finalPricebtnListener finalPricebtnlistener = new finalPricebtnListener();
        finalPricebtn.addActionListener(finalPricebtnlistener);
        
        finalPriceTxt = new JTextField("τελική τιμή");
        finalPriceTxt.setFont(serifFontBig);
        finalPriceTxt.setEditable(false);
        finalPriceTxt.setBackground(Color.yellow);
        finalPriceTxt.setBorder(BorderFactory.createCompoundBorder(
        		finalPriceTxt.getBorder(), 
		        BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        helpful.add(finalPriceTxt);
        
        toStockListbtn = new JButton("Στον κατάλογο");
        toStockListbtn.setFont(serifFontBig);
        helpful.add(toStockListbtn);
        
        toStockListbtnListener toStockListbtnlistener = new toStockListbtnListener();
        toStockListbtn.addActionListener(toStockListbtnlistener);
        
        centralPanel.add(helpful,BorderLayout.SOUTH);
        
        centralPanel.setBackground(Color.white);
        
        mainPanel.add(centralPanel,BorderLayout.CENTER);
	}
	
	class writeInfoInFilebtnListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			FileWriterLocal.writeInfo();
			GUI.showConfirmationWindow("Το αρχείο βρίσκεται στην επιφάνεια εργασίας, δείτε το.", 500);
		}
		
	}
	
	class initialPriceTxtFocus implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e) {
			finalPriceTxt.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {
			
		}
		
	}
	
	class toStockListbtnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new Product_GUI(Double.parseDouble(finalPriceTxt.getText()));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class finalPricebtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			double initialPrice = Double.parseDouble(initialPriceTxt.getText());
			double discount1 = Double.parseDouble(discountsTxt1.getText());
			double discount2 = Double.parseDouble(discountsTxt2.getText());
			double discount3 = Double.parseDouble(discountsTxt3.getText());
			double discount4 = Double.parseDouble(discountsTxt4.getText());
			ArrayList<Double> discounts = new ArrayList<>(); 
			discounts.add(discount1);
			discounts.add(discount2);
			discounts.add(discount3);
			discounts.add(discount4);

			double fare = Double.parseDouble(fareTxt.getText());
			double profit = Double.parseDouble(profitTxt.getText());
			double FPA = Double.parseDouble(FPATxt.getText());
			
			Product product = new Product(initialPrice, discounts, fare, profit, FPA);
			
			double finalPrice = product.getFinalPriceOfProduct();
			finalPriceTxt.setText(Double.toString(finalPrice));
}
		
	}
	
	public void makeMyFonts()
	{
		serifFontBig = new Font("serif", Font.BOLD, 30);
		serifFont = new Font("serif", Font.BOLD, 22);
		serifFontSmall = new Font("serif", Font.ITALIC|Font.BOLD, 17);
		sansSerifFontSmall = new Font("Sans Serif", Font.ITALIC|Font.BOLD, 17);
	}

	class btnReturnListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose(); 
			main_GUI.setVisible(true);
			
		}
		
	}
	
}
