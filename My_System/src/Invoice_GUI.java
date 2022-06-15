import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.border.Border;

import MathOfLife.FinalPrices;
import MathOfLife.Percentages;

import java.util.ArrayList;

public class Invoice_GUI extends JFrame{
	
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
	private JLabel instructionsLabel, finalPriceLabel, FPALabel, quantityLabel;
	private JTextField finalPriceTxt, FPATxt, calculateInitialTxt, quantityTxt, calculateInitialQuantityTxt;
	private JButton calculateInitialbutton, calculateInitialQuantitybutton, addProductToTheListbutton;
	//rightPanel
	private JTextArea pricesList, quantitiesList;
	private JButton addListsbtn;
	private JTextField pricesTxt, quantitiesTxt, FPAallTxt, FPAallResultTxt, allResultTxt;
	
	private JPanel centralCentralPanel;
	
	private Color mycolor;
	
	private Main_GUI main_GUI;
	private Invoice invoice;
	private JButton openFormForInvoice;
	
	public Invoice_GUI(Main_GUI main_GUI) {
		super("Χρώματα Βαδραχάνης");
		this.main_GUI = main_GUI;
		main_GUI.setVisible(false);
		this.invoice = new Invoice();
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

		infoLabel1 = new JLabel("Επωνυμία επιχείρησης: Χρώματα Βαδραχάνης");
		infoLabel1.setFont(serifFont);
		infoLabel1.setForeground(Color.white);
		infoLabel2 = new JLabel("Τηλ. 24670 29159");
		infoLabel2.setFont(serifFont);
		infoLabel2.setForeground(Color.white);
		infoLabel3 = new JLabel("Διεύθυνση: Μεγάλου Αλεξάνδρου 39");
		infoLabel3.setFont(serifFont);
		infoLabel3.setForeground(Color.white);
		infoLabel4 = new JLabel("Περιοχή: Καστοριά, 52100");
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
		
		openFormForInvoice = new JButton("<html>Συμπλήρωσε τιμολόγιο</html>");
		openFormForInvoice.setFont(sansSerifFontSmall);
		openFormForInvoice.setBackground(Color.cyan);
		
		openFormForInvoiceListener openFormForInvoicelistener = new openFormForInvoiceListener();
		openFormForInvoice.addActionListener(openFormForInvoicelistener);
		 
		westPanel.add(infoLabel1);
		westPanel.add(infoLabel2);
		westPanel.add(infoLabel3);
		westPanel.add(infoLabel4);
		westPanel.add(new JLabel("\n"));
		westPanel.add(writeInfoInFile);
		westPanel.add(new JLabel("\n"));
		westPanel.add(openFormForInvoice);
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
				+ " πίεσε τα αντίστοιχα πλήκτρα για την εκτύπωση των αναλόγων");
		instructionsLabel.setFont(serifFont); 
		centralPanel.add(instructionsLabel,BorderLayout.NORTH);
		
		//central
		centralCentralPanel = new JPanel();
		centralCentralPanel.setLayout(new GridLayout(1,2,10,10));
		GUI.setPadding(centralCentralPanel);
		
		JPanel leftPanel = new JPanel(new GridLayout(6,2,10,10));
		JPanel rightPanel = new JPanel(new GridLayout(2,1,10,10));
		JPanel rightPanel1 = new JPanel(new GridLayout(1,3,10,10));
		JPanel rightPanel2 = new JPanel(new GridLayout(2,3,10,10));
		GUI.setPadding(leftPanel);
		GUI.setPadding(rightPanel);
		GUI.setPadding(rightPanel1);
		GUI.setPadding(rightPanel2);
		
		//leftPanel
		//finalPrice
		finalPriceLabel = new JLabel("Τελική τιμή:");
		finalPriceLabel.setFont(serifFont);
		finalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		finalPriceTxt = new JTextField();
		finalPriceTxt.setFont(serifFont);
		GUI.setPaddingAtJTextField(finalPriceTxt);
		focusListener focuslistener = new focusListener();
		finalPriceTxt.addFocusListener(focuslistener);
		
		//FPA
		FPALabel = new JLabel("Ποστοστό ΦΠΑ:");
		FPALabel.setFont(serifFont);
		FPALabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		FPATxt = new JTextField("24");
		FPATxt.setFont(serifFont);
		FPATxt.setEditable(false);
		GUI.setPaddingAtJTextField(FPATxt);
		
		//CalculateInitialbutton
		calculateInitialbutton = new JButton("<html>Υπολόγισε αρχική<br>τιμή χωρίς ΦΠΑ:</html>");
		calculateInitialbutton.setFont(serifFont);
		calculateInitialbuttonListener calculateInitialbuttonlistener = new calculateInitialbuttonListener();
		calculateInitialbutton.addActionListener(calculateInitialbuttonlistener);
		
		calculateInitialTxt = new JTextField();
		calculateInitialTxt.setFont(serifFont);
		calculateInitialTxt.setEditable(false);
		GUI.setPaddingAtJTextField(calculateInitialTxt);
		
		//Quantity
		quantityLabel = new JLabel("Ποσότητα προϊόντος:");
		quantityLabel.setFont(serifFont);
		quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		quantityTxt = new JTextField();
		quantityTxt.setFont(serifFont);
		GUI.setPaddingAtJTextField(quantityTxt);
		
		focusQuantityListener focusQuantitylistener = new focusQuantityListener();
		quantityTxt.addFocusListener(focusQuantitylistener);
		
		//CalculateInitialQuantitybutton
		calculateInitialQuantitybutton = new JButton("Υποσύνολο:");
		calculateInitialQuantitybutton.setFont(serifFont);
		calculateInitialQuantitybuttonListener calculateInitialQuantitybuttonlistener = new calculateInitialQuantitybuttonListener();
		calculateInitialQuantitybutton.addActionListener(calculateInitialQuantitybuttonlistener);
		
		calculateInitialQuantityTxt = new JTextField();
		calculateInitialQuantityTxt.setEditable(false);
		calculateInitialQuantityTxt.setFont(serifFont);
		GUI.setPaddingAtJTextField(calculateInitialQuantityTxt);
		
		//AddProductToTheListbutton
		addProductToTheListbutton = new JButton("<html>Το προϊόν<br> στη λίστα</html>");
		addProductToTheListbutton.setFont(serifFont);
		addProductToTheListbutton.setBackground(getMyColor());
		addProductToTheListbutton.setBorder(new RoundedBorder(30));
		addProductToTheListbuttonListener addProductToTheListbuttonlistener = new addProductToTheListbuttonListener();
		addProductToTheListbutton.addActionListener(addProductToTheListbuttonlistener);
		addProductToTheListbuttonfocusListener addProductToTheListbuttonfocuslistener = new addProductToTheListbuttonfocusListener();
		addProductToTheListbutton.addFocusListener(addProductToTheListbuttonfocuslistener);
		
		leftPanel.add(finalPriceLabel);
		leftPanel.add(finalPriceTxt);
		leftPanel.add(FPALabel);
		leftPanel.add(FPATxt);
		leftPanel.add(calculateInitialbutton);
		leftPanel.add(calculateInitialTxt);
		leftPanel.add(quantityLabel);
		leftPanel.add(quantityTxt);
		leftPanel.add(calculateInitialQuantitybutton);
		leftPanel.add(calculateInitialQuantityTxt);
		leftPanel.add(new JLabel());
		leftPanel.add(addProductToTheListbutton);
		
		//rightPanel
		//lists
		pricesList = new JTextArea();
		pricesList.append("Οι τιμές ");
		pricesList.setFont(serifFont);
		GUI.setPaddingAtJTextArea(pricesList);
		JScrollPane pricesScrollPanel = new JScrollPane(pricesList); 
		
		quantitiesList = new JTextArea();
		quantitiesList.append("Ποσότητες");
		quantitiesList.setFont(serifFont); 
		GUI.setPaddingAtJTextArea(quantitiesList);
		JScrollPane quantitiesScrollPanel = new JScrollPane(quantitiesList); 
		
		//addition
		addListsbtn = new JButton("Πρόσθεσε");
		addListsbtn.setFont(serifFont);
		
		addListsbtnListener addListsbtnlistener = new addListsbtnListener();
		addListsbtn.addActionListener(addListsbtnlistener);
		
		pricesTxt = new JTextField();
		pricesTxt.setFont(serifFont);
		pricesTxt.setEditable(false);
		GUI.setPaddingAtJTextField(pricesTxt);
		
		quantitiesTxt = new JTextField();
		quantitiesTxt.setFont(serifFont);
		quantitiesTxt.setEditable(false);
		GUI.setPaddingAtJTextField(quantitiesTxt);
		
		//finally
		FPAallTxt = new JTextField("*24% +=");
		FPAallTxt.setFont(serifFont);
		FPAallTxt.setEditable(false);
		GUI.setPaddingAtJTextField(FPAallTxt);
		
		FPAallResultTxt = new JTextField("");
		FPAallResultTxt.setFont(serifFont);
		FPAallResultTxt.setEditable(false);
		GUI.setPaddingAtJTextField(FPAallResultTxt);
		
		allResultTxt = new JTextField("");
		allResultTxt.setFont(serifFont);
		allResultTxt.setEditable(false);
		GUI.setPaddingAtJTextField(allResultTxt);
		
		rightPanel1.add(new JLabel(""));
		rightPanel1.add(pricesScrollPanel);
		rightPanel1.add(quantitiesScrollPanel);
		rightPanel2.add(addListsbtn);
		rightPanel2.add(pricesTxt);
		rightPanel2.add(quantitiesTxt);
		rightPanel2.add(FPAallTxt);
		rightPanel2.add(FPAallResultTxt);
		rightPanel2.add(allResultTxt);
		
		rightPanel.add(rightPanel1);
		rightPanel.add(rightPanel2);
		
		centralCentralPanel.add(leftPanel);
		centralCentralPanel.add(rightPanel);
		
		centralPanel.add(centralCentralPanel,BorderLayout.CENTER);
        
        centralPanel.setBackground(Color.white);
        
        mainPanel.add(centralPanel,BorderLayout.CENTER);
	}
	
	class openFormForInvoiceListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Desktop.isDesktopSupported()) 
			{
				try {
					Desktop.getDesktop().browse(new URI("file:///C:/Users/user/Desktop/applications/My_System/invoice.php"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
			{
				
			}
		}
		
		
	}
	
	class RoundedBorder implements Border {
	    private int radius;

	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
		}

		@Override
		public boolean isBorderOpaque() {
			return true;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width-1, height-1, radius, radius);
		}
	}
	
	class addListsbtnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			double prices = invoice.getSumOfTheInitialPricesIncludingQuantities();
			pricesTxt.setText(Double.toString(prices));
			//change the following function in library
			double fpa = Math.round(( Percentages.getTheAmountOfThePercentage(prices, 24)* 100.0) / 100.0); 
			FPAallResultTxt.setText(Double.toString(fpa));
			double finalprices = Math.round(( Percentages.addFromPercentage(prices, 24)* 100.0) / 100.0); 
			allResultTxt.setText(Double.toString(finalprices));
			
			int quantities = invoice.getSumOfTheQuantities();
			quantitiesTxt.setText(Integer.toString(quantities));
		}
	}
	
	class addProductToTheListbuttonfocusListener implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent e) {
			pricesTxt.setText("");
			quantitiesTxt.setText("");
			FPAallResultTxt.setText("");
			allResultTxt.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {

		}
	}
	
	class focusListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e) {
			calculateInitialTxt.setText("");
			quantityTxt.setText("");
			calculateInitialQuantityTxt.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {
			
		}
		
	}
	
	class focusQuantityListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent e) {
			calculateInitialQuantityTxt.setText("");
		}

		@Override
		public void focusLost(FocusEvent e) {
			
		}
		
	}
	
	class calculateInitialbuttonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			double finalPrice = Double.parseDouble(finalPriceTxt.getText());
			Product product = new Product(finalPrice, 1);
			double initialPrice = product.getandsetInitialPriceOfProduct(finalPrice, 24);
			initialPrice = Math.round(initialPrice * 100.0) / 100.0;
			calculateInitialTxt.setText(Double.toString(initialPrice));
		}
		
	}
	
	class calculateInitialQuantitybuttonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			int quantity = Integer.parseInt(quantityTxt.getText());
			double finalPrice = Double.parseDouble(finalPriceTxt.getText());
			Product product = new Product(finalPrice, quantity);
			product.getandsetInitialPriceOfProduct(finalPrice, 24);
			double initialPriceQuantity = product.getInitialPriceIncludingQuantity();
			initialPriceQuantity = Math.round(initialPriceQuantity * 100.0) / 100.0;
			calculateInitialQuantityTxt.setText(Double.toString(initialPriceQuantity));
		}
		
	}
	
	class addProductToTheListbuttonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			int quantity = Integer.parseInt(quantityTxt.getText());
			double finalPrice = Double.parseDouble(finalPriceTxt.getText());
			Product product = new Product(finalPrice, quantity);
			product.getandsetInitialPriceOfProduct(finalPrice, 24);
			invoice.addProduct(product);
			pricesList.append("\n" + Double.toString(Math.round(product.getInitialPriceIncludingQuantity() * 100.0) / 100.0));
			quantitiesList.append("\n" + Integer.toString(product.getQuantity()));
		}
		
	}
	
	class writeInfoInFilebtnListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			FileWriterLocal.writeInfo();
			GUI.showConfirmationWindow("Το αρχείο βρίσκεται στην επιφάνεια εργασίας, δείτε το.", 500);
		}
		
	}
	
	public void makeMyFonts()
	{
		serifFontBig = new Font("serif", Font.BOLD, 30);
		serifFont = new Font("serif", Font.BOLD, 22);
		serifFontSmall = new Font("serif", Font.ITALIC|Font.BOLD, 17);
		sansSerifFontSmall = new Font("Sans Serif", Font.ITALIC|Font.BOLD, 17);
	}
	
	public Color getMyColor()
	{
		mycolor = new Color(0,0,0);
		mycolor = new Color(223, 225, 228);
		
		return mycolor;
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
